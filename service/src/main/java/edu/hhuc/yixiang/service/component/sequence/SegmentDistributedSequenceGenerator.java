package edu.hhuc.yixiang.service.component.sequence;

import com.google.common.collect.Lists;
import edu.hhuc.yixiang.common.base.BaseResultCode;
import edu.hhuc.yixiang.common.entity.DistributedSequence;
import edu.hhuc.yixiang.common.exception.DistributedSequenceException;
import edu.hhuc.yixiang.repository.dao.DistributedSequenceDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/3 21:26:53
 */
@Component
@Slf4j
public class SegmentDistributedSequenceGenerator implements DistributedSequenceGenerator {
    private final DistributedSequenceDao distributedSequenceDao;

    private final Map<String, SegmentBuffer> cache = new ConcurrentHashMap<>();

    private final ExecutorService executorService = new ThreadPoolExecutor(5, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());


    @Autowired
    public SegmentDistributedSequenceGenerator(DistributedSequenceDao distributedSequenceDao) {
        this.distributedSequenceDao = distributedSequenceDao;
        init();
    }

    private void init() {
        loadAllBusinessType();
        scanBusinessType();
    }

    @Override
    public Long generateSequence(String businessType) {
        if (cache.containsKey(businessType)) {
            SegmentBuffer buffer = cache.get(businessType);
            // double check
            if (!buffer.isInitialized()) {
                // 保证同一个businessType只能有一个线程去初始化号段
                synchronized (buffer) {
                    if (!buffer.isInitialized()) {
                        updateSegment(businessType, buffer.currentSegment());
                        buffer.setInitialized(true);
                    }
                }
            }
            return getSequenceFromBuffer(buffer);
        }
        throw new DistributedSequenceException(BaseResultCode.DISTRIBUTED_SEQUENCE_EXCEPTION);
    }

    private Long getSequenceFromBuffer(SegmentBuffer buffer) {
        Segment segment = buffer.currentSegment();
        // 如果号段已使用过半，且下一个号段没有加载完成，也不在加载中，则启动异步线程加载下一个号段
        if (!buffer.isNextSegmentReady() && (segment.getIdleSequenceCount() < 0.5 * segment.getStep()) && buffer.getNextSegmentLoading().compareAndSet(false, true)) {
            executorService.submit(() -> {
                Segment next = buffer.getSegments()[buffer.getNextIndex()];
                updateSegment(buffer.getBusinessType(), next);
                buffer.setNextSegmentReady(true);
                buffer.getNextSegmentLoading().set(false);
            });
        }
        // 如果当前号段可用，则直接返回
        long sequence = buffer.getNextSequence();
        if (sequence < buffer.currentSegment().getMaxId()) {
            return sequence;
        }
        // 下一个号段没有准备好的话，等一会
        if (!buffer.isNextSegmentReady()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // 下一个号段已经准备好，切换到下一个号段
        if (buffer.isNextSegmentReady()) {
            buffer.switchSegment();
            buffer.setNextSegmentReady(false);
            sequence = buffer.getNextSequence();
            if (sequence < buffer.currentSegment().getMaxId()) {
                return sequence;
            }
        }
        throw new DistributedSequenceException(BaseResultCode.DISTRIBUTED_SEQUENCE_EXCEPTION);
    }

    /**
     * 加载所有分布式id的key到本地内存
     */
    private void loadAllBusinessType() {
        StopWatch stopWatch = StopWatch.createStarted();

        log.info("DistributedSequenceGenerator Start initialization");
        List<String> businessTypes = distributedSequenceDao.queryAllBusinessType();
        if (CollectionUtils.isEmpty(businessTypes)) {
            cache.clear();
            return;
        }

        List<String> cachedTypes = Lists.newArrayList(cache.keySet());
        List<String> appendedTypes = new ArrayList<>(businessTypes);
        List<String> removedTypes = new ArrayList<>(cachedTypes);
        appendedTypes.removeAll(cachedTypes);
        removedTypes.removeAll(businessTypes);

        for (String type : appendedTypes) {
            cache.put(type, new SegmentBuffer(type));
            log.info("load business type：{} to cache", type);
        }
        for (String type : removedTypes) {
            cache.remove(type);
            log.info("remove business type：{} from cache", type);
        }
        log.info("load all business type finish in {} millSeconds", stopWatch.getTime());
    }

    private void scanBusinessType() {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("scan-businessType-thread");
                t.setDaemon(true);
                return t;
            }
        });

        // 每隔60秒从数据库加载一次
        scheduledExecutorService.scheduleWithFixedDelay(this::loadAllBusinessType, 60, 60, TimeUnit.SECONDS);
    }

    private void updateSegment(String businessType, Segment segment) {
        // 该方法的调用有加锁，所以step，maxId，value等值的修改没有并发问题
        DistributedSequence sequence = distributedSequenceDao.updateAndGetSequence(businessType);
        segment.setStep(sequence.getStep());
        segment.setMaxId(sequence.getMaxId());
        segment.getValue().set(sequence.getMaxId() - sequence.getStep());
    }
}

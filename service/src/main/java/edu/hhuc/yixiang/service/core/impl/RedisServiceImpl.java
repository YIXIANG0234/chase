package edu.hhuc.yixiang.service.core.impl;

import edu.hhuc.yixiang.common.annotation.DistributedLock;
import edu.hhuc.yixiang.common.utils.DateUtil;
import edu.hhuc.yixiang.service.core.RedisService;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/27 16:20:34
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Override
    @DistributedLock(key = "test")
    public void distributedLock() {
        process();
    }

    @Override
    @DistributedLock(key = "test", retry = true)
    public void tryLock() {
        process();
    }

    private void process() {
        System.out.println("线程：" + Thread.currentThread().getName() + "获取到锁，正在执行任务，开始时间：" + DateUtil.formatNowPrecise());
        Random random = new Random();
        try {
            // Thread.sleep(random.nextInt(1000));
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("线程：" + Thread.currentThread().getName() + "任务执行完毕，完成时间：" + DateUtil.formatNowPrecise());
    }
}

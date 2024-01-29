package edu.hhuc.yixiang.service.component.sequence;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Segment对象代表一个一个号段的id
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description 获取
 * @date 2024/1/3 21:30:46
 */
@Getter
public class Segment {
    /**
     * 下一个可以获取的id
     */
    private final AtomicLong value = new AtomicLong(0);

    /**
     * 表示号段的长度
     */
    private volatile int step;

    /**
     * 表示最多可获取的id（不含）
     */
    private volatile long maxId;

    /**
     * 持有号段缓冲区的引用
     */
    private final SegmentBuffer buffer;

    public Segment(SegmentBuffer buffer) {
        this.buffer = buffer;
    }

    /**
     * @return 获取下一个可用的id
     */
    public long getNextSequence() {
        return value.getAndAdd(1);
    }

    /**
     * @return 获取剩余可用的id数
     */
    public long getIdleSequenceCount() {
        return maxId - value.get();
    }

    public void setStep(int step) {
        this.step = step;
    }

    public void setMaxId(long maxId) {
        this.maxId = maxId;
    }
}

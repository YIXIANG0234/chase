package edu.hhuc.yixiang.service.component.sequence;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/3 21:30:36
 */
@Getter
public class SegmentBuffer {
    /**
     * 业务类型
     */
    private String businessType;
    /**
     * 两个号段，一个是正在使用的号段，一个是预加载的号段
     */
    private Segment[] segments;

    /**
     * 当前在用号段的索引
     */
    private int index;

    /**
     * 初始化是否完成的标识
     */
    private volatile boolean initialized;

    /**
     * 下一个号段是否预加载好
     */
    private volatile boolean nextSegmentReady;

    /**
     * 下一个号段是否在加载中
     */
    private final AtomicBoolean nextSegmentLoading = new AtomicBoolean(false);


    public SegmentBuffer(String businessType) {
        this.businessType = businessType;
        this.segments = new Segment[]{new Segment(this), new Segment(this)};
        index = 0;
        initialized = false;
    }

    public long getNextSequence() {
        return segments[index].getNextSequence();
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }

    public Segment currentSegment() {
        return segments[index];
    }

    public void setNextSegmentReady(boolean nextSegmentReady) {
        this.nextSegmentReady = nextSegmentReady;
    }

    public void switchSegment() {
        index = getNextIndex();
    }

    public int getNextIndex() {
        // 等同于return (index + 1) % 1;
        return (index + 1) & 1;
    }
}

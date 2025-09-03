package edu.hhuc.yixiang.service.component.limit;

import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @project chase
 * @description
 * @date 2025/8/25 17:40:19
 */
public class LocalFixedLimitExecutor {
    // 限流的时间窗口，单位微秒
    private long windowInMicro;
    // 限流阈值
    private int threshold;
    // 当前窗口请求次数
    private int count;
    // 窗口开始时间
    private long windowStartTime;

    public LocalFixedLimitExecutor(long windowInMicro, int threshold) {
        this.windowInMicro = windowInMicro;
        this.threshold = threshold;
        this.windowStartTime = TimeUnit.NANOSECONDS.toMicros(System.nanoTime());
    }

    public synchronized boolean acquire() {
        long currentTime = TimeUnit.NANOSECONDS.toMicros(System.nanoTime());
        if (currentTime - windowStartTime >= windowInMicro) {
            windowStartTime = currentTime;
            count = 0;
        }
        if (count < threshold) {
            count++;
            return true;
        }
        return false;
    }
}

package edu.hhuc.yixiang.service.component.limit;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @project chase
 * @description
 * @date 2025/8/26 10:55:30
 */
public class LocalSlideLimitExecutor {

    // 限流的时间窗口，单位微秒
    private long windowInMicro;
    // 限流阈值
    private long threshold;
    // 请求
    LinkedList<Long> requests;

    public LocalSlideLimitExecutor(long windowInMicro, long threshold) {
        this.windowInMicro = windowInMicro;
        this.threshold = threshold;
        requests = new LinkedList<>();
    }

    public synchronized boolean acquire() {
        long currentTime = TimeUnit.NANOSECONDS.toMicros(System.nanoTime());
        long windowStartTime = currentTime - windowInMicro;
        while (!requests.isEmpty() && requests.peek() < windowStartTime) {
            requests.pollFirst();
        }
        if (requests.size() >= threshold) {
            return false;
        }
        requests.addLast(currentTime);
        return true;
    }
}

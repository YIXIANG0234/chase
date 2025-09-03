package edu.hhuc.yixiang.service.component.limit;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 1.0
 * @project chase
 * @description
 * @date 2025/8/26 19:41:27
 */
public class LocalLeakyBucketLimitExecutor {
    // 桶的容量
    private int capacity;
    // 当前水位，即桶里面已装的水的容量
    private int waterLevel;
    // 漏水速率，即处理请求的速率
    private int leakRate;
    // 最近一次处理请求的时间, 纳秒
    private long lastLeakTimeInNano;
    // 锁
    private Lock lock;

    public LocalLeakyBucketLimitExecutor(int capacity, int leakRate) {
        this.capacity = capacity;
        this.leakRate = leakRate;
        this.lock = new ReentrantLock();
        this.lastLeakTimeInNano = System.nanoTime();
    }

    public boolean acquire() {
        lock.lock();
        try {
            long currentTime = System.nanoTime();
            double elapsedTime = (currentTime - lastLeakTimeInNano) / 1_000_000_000.0;
            if (elapsedTime > 0) {
                int leaked = (int) (elapsedTime * leakRate);
                if (leaked > 0) {
                    waterLevel = Math.max(0, waterLevel - leaked);
                    lastLeakTimeInNano = currentTime;
                }
            }
            if (waterLevel < capacity) {
                waterLevel++;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }
}

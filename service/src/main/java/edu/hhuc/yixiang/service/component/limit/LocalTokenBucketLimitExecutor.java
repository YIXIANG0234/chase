package edu.hhuc.yixiang.service.component.limit;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 1.0
 * @project chase
 * @description
 * @date 2025/8/26 14:22:57
 */
public class LocalTokenBucketLimitExecutor {
    // 令牌桶剩余令牌数
    private int storedTokens;
    // 令牌桶存储令牌上限
    private final int capacity;
    // 上一次生成令牌的时间
    private long lastGenerateTimeInNano;
    // 生成令牌的速率（每秒生成令牌的数量）
    private double rate;
    private Lock lock;

    public LocalTokenBucketLimitExecutor(double rate, int capacity) {
        this.storedTokens = capacity;
        this.capacity = capacity;
        this.lastGenerateTimeInNano = System.nanoTime();
        this.rate = rate;
        this.lock = new ReentrantLock();
    }

    public boolean acquire() {
        lock.lock();
        try {
            long currentTime = System.nanoTime();
            double elapsedTime = (currentTime - lastGenerateTimeInNano) / 1_000_000_000.0;

            int generatedTokens = (int) (elapsedTime * rate);
            if (generatedTokens > 0) {
                lastGenerateTimeInNano = currentTime;
                this.storedTokens = Math.min(capacity, storedTokens + generatedTokens);
            }
            if (storedTokens <= 0) {
                return false;
            }
            storedTokens--;
            return true;
        } finally {
            lock.unlock();
        }
    }
}

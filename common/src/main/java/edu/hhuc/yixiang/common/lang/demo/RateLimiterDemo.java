package edu.hhuc.yixiang.common.lang.demo;

import com.google.common.util.concurrent.RateLimiter;
import edu.hhuc.yixiang.common.utils.DateUtil;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @project chase
 * @description
 * @date 2025/8/20 20:05:37
 */
public class RateLimiterDemo {
    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(3, 5, 60, TimeUnit.MINUTES, new ArrayBlockingQueue<>(5));

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        RateLimiter rateLimiter = RateLimiter.create(0.5);
        for (int i = 0; i < 30; i++) {
            double time = rateLimiter.acquire(1);
            System.out.println("RateLimiter: " + DateUtil.formatNow() + ": " + time);
        }
    }

    private static void test2() {
        RateLimiter rateLimiter = RateLimiter.create(0.5);
        for (int i = 0; i < 30; i++) {
            if (rateLimiter.tryAcquire(1)) {
                System.out.println("RateLimiter: " + DateUtil.formatNow());
            } else {
                System.out.println("RateLimiter failed: " + DateUtil.formatNow());
            }
        }
    }
}

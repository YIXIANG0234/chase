package edu.hhuc.yixiang.service.component.limit;

import edu.hhuc.yixiang.common.utils.DateUtil;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version 1.0
 * @project chase
 * @description
 * @date 2025/8/26 13:59:41
 */
public class LimitExecutorDemo {
    public static void main(String[] args) {
        long start = System.nanoTime();
        int sum = 0;
        for (int i = 0; i < 10000; i++) {
            sum = sum + i;
        }
        long gap = System.nanoTime() - start;
        double duration = gap / 1_000_000.0;
        System.out.println(duration + ":" + sum);
        //        LocalFixedLimitExecutor limitExecutor = new LocalFixedLimitExecutor(TimeUnit.SECONDS.toMicros(1), 5);
        //        LocalSlideLimitExecutor limitExecutor = new LocalSlideLimitExecutor(TimeUnit.SECONDS.toMicros(1), 5);
        //        LocalTokenBucketLimitExecutor limitExecutor = new LocalTokenBucketLimitExecutor(5, 5);
        LocalLeakyBucketLimitExecutor limitExecutor = new LocalLeakyBucketLimitExecutor(5, 5);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Task(limitExecutor));
        }
    }

    private static class Task implements Runnable {
        private LocalLeakyBucketLimitExecutor limitExecutor;

        public Task(LocalLeakyBucketLimitExecutor limitExecutor) {
            this.limitExecutor = limitExecutor;
        }

        @Override
        public void run() {
            Random random = new Random();
            while (true) {
                if (limitExecutor.acquire()) {
                    System.out.println(Thread.currentThread().getName() + ":" + DateUtil.formatNowPrecise());
                } else {
                    int sleep = random.nextInt(100);
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}

package edu.hhuc.yixiang.common.lang.demo;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @version 1.0
 * @project chase
 * @description
 * @date 2025/7/10 14:52:22
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Thread t1 = new Thread(new Task(3, countDownLatch));
        Thread t2 = new Thread(new Task(4, countDownLatch));
        Thread t3 = new Thread(new Task(5, countDownLatch));

        Thread w1 = new Thread(new Waiter(countDownLatch));
        Thread w2 = new Thread(new Waiter(countDownLatch));
        w1.start();
        w2.start();

        t1.start();
        t2.start();
        t3.start();
    }

    private static class Task implements Runnable {
        private int count;
        private CountDownLatch countDownLatch;

        public Task(int count, CountDownLatch countDownLatch) {
            this.count = count;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            for (int i = 0; i < count; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
                try {
                    Random random = new Random();
                    Thread.sleep(random.nextInt(50));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            countDownLatch.countDown();
        }
    }

    private static class Waiter implements Runnable {
        private CountDownLatch countDownLatch;

        public Waiter(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": 开始等待子任务执行");
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + ": 所有子任务执行完毕");
        }
    }
}

package edu.hhuc.yixiang.common.lang;

import java.util.Random;

/**
 * @version 1.0
 * @project chase
 * @description
 * @date 2025/7/9 17:11:11
 */
public class CounterTask implements Runnable {
    private int times;
    private Random random;

    private Counter counter;

    public CounterTask(Counter counter, int times) {
        this.counter = counter;
        this.times = times;
        random = new Random();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < times; i++) {
                counter.increment();
                Thread.sleep(random.nextInt(10));
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

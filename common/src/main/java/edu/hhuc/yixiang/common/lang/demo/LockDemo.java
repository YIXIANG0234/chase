package edu.hhuc.yixiang.common.lang.demo;

import edu.hhuc.yixiang.common.lang.Counter;
import edu.hhuc.yixiang.common.lang.CounterTask;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @project chase
 * @description
 * @date 2025/7/9 17:10:25
 */
public class LockDemo {
    public static void main(String[] args) throws Exception {
        test1();
    }

    public static void test1() throws Exception {
        Counter counter = new Counter();
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            threadList.add(new Thread(new CounterTask(counter, (i + 1) * 100)));
        }

        threadList.forEach(Thread::start);
        threadList.forEach(x -> {
            try {
                x.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("最终结果: " + counter.getCount());
    }
}

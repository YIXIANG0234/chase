package edu.hhuc.yixiang.common.lang.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 1.0
 * @project chase
 * @description
 * @date 2025/6/30 15:05:05
 */
public class ThreadDemo {

    private static final Object RESOURCE = new Object();
    private static final Lock LOCK = new ReentrantLock();

    public static void main(String[] args) throws Exception {
        test4();
    }

    public static void test1() throws Exception {
        Thread t1 = new Thread(() -> {
            int i = 0;
            while (i <= Integer.MAX_VALUE - 1) {
                i++;
                if (i % 10000 == 0) {
                    System.out.println("i=" + i);
                }
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("i am interrupted");
                    break;
                }
            }
        });
        t1.start();
        Thread.sleep(10);
        System.out.println("start to interrupt t1");
        t1.interrupt();
    }

    public static void test2() throws Exception {
        Thread thread = new Thread(() -> {
            System.out.println("线程开始执行");
            int i = 0;
            while (true) {
                if (Thread.interrupted()) {
                    System.out.println("线程被中断：" + Thread.currentThread().isInterrupted());
                    i++;
                    if (i == 2) {
                        break;
                    }
                }
            }
        });
        thread.start();
        thread.interrupt();
        Thread.sleep(50);
        thread.interrupt();
    }

    public static void test3() throws Exception {
        Thread t1 = new Thread(() -> {
            System.out.println("线程t1正在获取锁");
            synchronized (RESOURCE) {
                System.out.println("线程t1已获取锁");
                try {
                    Thread.sleep(60 * 60 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            System.out.println("线程t2正在获取锁");
            synchronized (RESOURCE) {
                System.out.println("线程t2已获取锁");
                try {
                    Thread.sleep(60 * 60);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            // synchronized无法响应中断，interrupt之后也会继续等待t1释放锁
            System.out.println("线程t2放弃获取锁");
        });

        t1.start();
        Thread.sleep(5);
        t2.start();
        Thread.sleep(100);
        t2.interrupt();
    }

    public static void test4() throws Exception {
        Thread t1 = new Thread(() -> {
            System.out.println("线程t1正在获取锁");
            try {
                LOCK.lockInterruptibly();
                try {
                    System.out.println("线程t1已获取锁");
                    Thread.sleep(60 * 60 * 1000);
                } finally {
                    LOCK.unlock();
                }
            } catch (InterruptedException e) {
                System.out.println("线程t1获取锁失败");
                Thread.currentThread().interrupt();
            }
        });

        Thread t2 = new Thread(() -> {
            System.out.println("线程t2正在获取锁");
            try {
                LOCK.lockInterruptibly();
                try {
                    System.out.println("线程t2已获取锁");
                    Thread.sleep(60 * 60 * 1000);
                } finally {
                    LOCK.unlock();
                }
            } catch (InterruptedException e) {
                System.out.println("线程t2获取锁失败");
                Thread.currentThread().interrupt();
            }
        });

        t1.start();
        Thread.sleep(5);
        t2.start();
        Thread.sleep(100);
        t2.interrupt();
    }
}

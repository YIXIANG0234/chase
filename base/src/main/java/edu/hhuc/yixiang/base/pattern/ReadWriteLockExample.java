package edu.hhuc.yixiang.base.pattern;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/1/5 16:05:36
 */
public class ReadWriteLockExample {
    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) throws Exception{
        System.out.println("主线程开始执行");
        readTask();
        // 保证读锁先被获取
        Thread.sleep(5);
        writeTask();
        // 保证写锁被获取
        Thread.sleep(5);
        readTask();
    }

    public static void readTask() {
        Thread thread = new Thread(() -> {
            readWriteLock.readLock().lock();
            try {
                Thread.sleep(3000);
                System.out.println("正在执行读任务");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                readWriteLock.readLock().unlock();
            }
        });
        thread.start();
    }

    public static void writeTask() {
        Thread thread = new Thread(() -> {
            readWriteLock.writeLock().lock();
            try {
                Thread.sleep(2000);
                System.out.println("正在执行写任务");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                readWriteLock.writeLock().unlock();
            }
        });
        thread.start();
    }
}

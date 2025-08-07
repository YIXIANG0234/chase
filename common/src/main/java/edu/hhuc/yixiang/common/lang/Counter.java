package edu.hhuc.yixiang.common.lang;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 1.0
 * @project chase
 * @description
 * @date 2025/7/9 20:37:26
 */
public class Counter {
    private int count;
    private Lock lock;

    public Counter() {
        this.lock = new ReentrantLock();
    }

    public void increment() {
        lock.lock();
        try {
            this.count = this.count + 1;
        } finally {
            lock.unlock();
        }
    }

    public synchronized void synchronizedIncrement() {
        lock.lock();
        try {
            this.count = this.count + 1;
        } finally {
            lock.unlock();
        }
    }

    public void unsafeIncerement() {
        this.count = this.count + 1;
    }

    public int getCount() {
        return count;
    }
}

package edu.hhuc.yixiang.common.lang;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @version 1.0
 * @project chase
 * @description
 * @date 2025/7/10 22:26:20
 */
public class MySemaphore extends Semaphore {
    private Sync sync;

    private static final class Sync extends AbstractQueuedSynchronizer {
        public Sync(int count) {
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            for (; ; ) {
                int c = getState();
                int rest = c - arg;
                if (rest < 0 || compareAndSetState(c, rest)) {
                    return rest;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for (; ; ) {
                int c = getState();
                int rest = c + arg;
                if (compareAndSetState(c, rest)) {
                    return true;
                }
            }
        }
    }

    public MySemaphore(int permits) {
        super(permits);
        this.sync = new Sync(permits);
    }

    @Override
    public void acquire() throws InterruptedException {
        sync.acquireShared(1);
    }

    @Override
    public void release() {
        sync.releaseShared(1);
    }
}

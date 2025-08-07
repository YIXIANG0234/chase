package edu.hhuc.yixiang.common.lang;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 排它模式实现的CountDownLatch，不推荐，更加推荐使用共享模式实现，
 * 排它模式实现的CountDownLatch，只能唤醒一个等待的线程
 */
public class MyCountDownLatch extends CountDownLatch {
    private Sync sync;

    private static final class Sync extends AbstractQueuedSynchronizer {
        public Sync(int count) {
            setState(count);
        }

        @Override
        protected boolean tryAcquire(int arg) {
            return getState() == 0;
        }

        @Override
        protected boolean tryRelease(int arg) {
            for (; ; ) {
                int c = getState();
                int rest = c - 1;
                if (rest < 0) {
                    return false;
                }
                if (compareAndSetState(c, rest)) {
                    return rest == 0;
                }
            }
        }
    }

    public MyCountDownLatch(int count) {
        super(count);
        sync = new Sync(count);
    }

    @Override
    public void await() {
        sync.acquire(1);
    }

    @Override
    public void countDown() {
        sync.release(1);
    }
}



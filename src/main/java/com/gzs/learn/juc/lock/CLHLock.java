package com.gzs.learn.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class CLHLock implements Lock {
    private class CLHNode {
        private volatile boolean isLock = true;
    }

    @SuppressWarnings("unused")
    private volatile CLHNode tail;
    private static final ThreadLocal<CLHNode> local = new ThreadLocal<>();
    private static final AtomicReferenceFieldUpdater<CLHLock, CLHNode> updater =
            AtomicReferenceFieldUpdater.newUpdater(CLHLock.class, CLHNode.class, "tail");

    @Override
    public void lock() {
        CLHNode node = new CLHNode();
        local.set(node);
        CLHNode preNode = updater.getAndSet(this, node);
        if (preNode != null) {
            while (preNode.isLock) {
                // spin
            }
            preNode = null;
            local.set(node);
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        CLHNode node = local.get();
        if (!updater.compareAndSet(this, node, null)) {
            node.isLock = false;
        }
        node = null;
    }

    @Override
    public Condition newCondition() {
        return null;
    }

}

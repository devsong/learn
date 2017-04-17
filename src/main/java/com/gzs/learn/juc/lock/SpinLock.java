package com.gzs.learn.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * spin lock impl
 * 
 * @author guanzhisong
 * @date 2017年4月17日
 */
public class SpinLock implements Lock {
    private AtomicReference<Thread> lock = new AtomicReference<Thread>();

    @Override
    public void lock() {
        Thread currentThread = Thread.currentThread();
        while (!lock.compareAndSet(null, currentThread)) {

        }
    }

    @Override
    public void unlock() {
        Thread currentThread = Thread.currentThread();
        lock.compareAndSet(currentThread, null);
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
    public Condition newCondition() {
        return null;
    }
}

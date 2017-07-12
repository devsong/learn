package com.gzs.learn.juc.notify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockNotify {
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    private int[] buffers = new int[10];
    private int writeIndex = 0;
    private int readIndex = 0;
    private int limit = 0;
    private int capcity = buffers.length;


    public void write(int msg) throws InterruptedException {
        try {
            lock.lock();
            if (limit >= capcity) {
                notFull.await();
            }
            if (writeIndex < 0) {
                writeIndex = 0;
            }
            buffers[writeIndex++ % capcity] = msg;
            System.out.println(Thread.currentThread().getName() + " write msg:" + msg);
            // Thread.sleep(1000);
            limit++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public void read() throws InterruptedException {
        try {
            lock.lock();
            if (limit == 0) {
                notEmpty.await();
            }
            if (readIndex < 0) {
                readIndex = 0;
            }
            int msg = buffers[readIndex++ % capcity];
            System.out.println(Thread.currentThread().getName() + " recv msg:" + msg);
            limit--;
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }
}

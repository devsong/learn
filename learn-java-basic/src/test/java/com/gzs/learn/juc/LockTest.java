package com.gzs.learn.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class LockTest {

    @Test
    public void testLock() throws InterruptedException {
        final ExecutorService es = Executors.newFixedThreadPool(2);

        final Lock lock = new ReentrantLock();
        final CountDownLatch latch = new CountDownLatch(2);
        es.submit(() -> {
            lock.lock();
            System.out.println(11);
            lock.unlock();
            latch.countDown();
        });

        es.submit(() -> {
            lock.lock();
            System.out.println(22);
            lock.unlock();
            latch.countDown();
        });

        latch.await();
    }

    @Test
    public void testLeft() {
        // System.out.println(Integer.toBinaryString(-1 << (Integer.SIZE - 3)));
        // System.out.println(Integer.toBinaryString(0 << (Integer.SIZE - 3)));
        // System.out.println(Integer.toBinaryString(1 << (Integer.SIZE - 3)));
        // System.out.println(Integer.toBinaryString(2 << (Integer.SIZE - 3)));
        // System.out.println(Integer.toBinaryString(3 << (Integer.SIZE - 3)));
        System.out.println(Integer.toBinaryString(-3));
        System.out.println(Integer.toBinaryString(-3 << (Integer.SIZE - 2)));
        System.out.println((-3 << (Integer.SIZE - 2)));
    }

    @Test
    public void testLockSupport() {
        LockSupport.unpark(Thread.currentThread());
        System.out.println("before park");
        LockSupport.park();
        System.out.println("after  park");
        LockSupport.unpark(Thread.currentThread());
        System.out.println("second park");
        LockSupport.park();
    }

    @Test
    public void testShardLock() throws InterruptedException {
        final int size = 5;
        final CountDownLatch latch = new CountDownLatch(size);
        final ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < size; i++) {
            final int index = i;
            executorService.submit(() -> {
                System.out.println("index:" + index);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (final InterruptedException e) {
                }
                latch.countDown();
            });
        }
        latch.await();
    }
}

package com.gzs.learn.juc.base;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

import lombok.AllArgsConstructor;

public class AqsExample {

    @Test
    public void testAqs() throws InterruptedException {
        int len = 10;
        ReentrantLock lock = new ReentrantLock();
        CountDownLatch latch = new CountDownLatch(len);
        for (int i = 0; i < len; i++) {
            new Foo(lock, latch).start();
        }
        latch.await();
    }

    @Test
    public void testIntMax() {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Long.toBinaryString(1441151880762493770L));
    }
}


@AllArgsConstructor
class Foo extends Thread {
    private ReentrantLock reentrantLock;
    private CountDownLatch latch;

    @Override
    public void run() {
        final Lock lock = reentrantLock;
        try {
            lock.lock();
            int random = ThreadLocalRandom.current().nextInt(100);
            System.out.println(random);
            latch.countDown();
            Thread.sleep(random);
        } catch (InterruptedException e) {
        } finally {
            lock.unlock();
        }
    }
}

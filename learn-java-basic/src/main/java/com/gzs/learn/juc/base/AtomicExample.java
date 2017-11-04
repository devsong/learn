package com.gzs.learn.juc.base;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 原子操作示例
 *
 * @author guanzhisong
 * @date 2017年11月4日
 */
@Slf4j
public class AtomicExample {
    @Test
    public void testAtomic() throws InterruptedException {
        Counter counter = new Counter();
        int threadCount = 100;
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch waitLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            new ExampleThread(counter, startLatch, waitLatch).start();
        }
        startLatch.countDown();
        waitLatch.await();
        log.info("not thread safe counter:{}", counter.getCount());
        log.info("thread safe counter:{}", counter.getAtomicCounter().get());
    }
}


@Data
class Counter {
    private int count;
    private AtomicInteger atomicCounter;

    public Counter() {
        count = 0;
        atomicCounter = new AtomicInteger(0);
    }

    public void notSafeCount() {
        count++;
    }

    public synchronized void safeCount() {
        count++;
    }

    public void atomicCount() {
        atomicCounter.incrementAndGet();
    }
}


@AllArgsConstructor
class ExampleThread extends Thread {
    private static final int LOOP_TIMES = 100;
    private Counter counter;
    private CountDownLatch startLatch;
    private CountDownLatch waitLatch;

    @Override
    public void run() {
        try {
            startLatch.await();
            for (int i = 0; i < LOOP_TIMES; i++) {
                counter.notSafeCount();
                counter.atomicCount();
            }
        } catch (InterruptedException e) {
        } finally {
            waitLatch.countDown();
        }
    }
}

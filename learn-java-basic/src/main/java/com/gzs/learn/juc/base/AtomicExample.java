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
        // 线程数
        int threadCount = 100;
        // 可以先不用理会这两个类,此处用途是确保线程创建完毕以后能够以一个相对一致的时间点同时执行线程代码
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch waitLatch = new CountDownLatch(threadCount);
        // 创建线程
        for (int i = 0; i < threadCount; i++) {
            new ExampleThread(counter, startLatch, waitLatch).start();
        }
        // 开始执行累加
        startLatch.countDown();
        // 等待线程执行完毕
        waitLatch.await();
        log.info("not thread safe counter:{}", counter.getCount());
        log.info("thread safe counter:{}", counter.getSyncCount());
        log.info("atomic counter:{}", counter.getAtomicCounter().get());
    }
}


@Data
class Counter {
    private int count;
    private int syncCount;
    private AtomicInteger atomicCounter;

    public Counter() {
        count = 0;
        syncCount = 0;
        atomicCounter = new AtomicInteger(0);
    }

    public void notSafeCount() {
        count++;
    }

    public synchronized void safeCount() {
        syncCount++;
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
            // 线程默认是阻塞在此处的,主线程调用startLatch.countDown()即解除阻塞状态
            startLatch.await();
            for (int i = 0; i < LOOP_TIMES; i++) {
                counter.notSafeCount();
                counter.safeCount();
                counter.atomicCount();
            }
        } catch (InterruptedException e) {
        } finally {
            waitLatch.countDown();
        }
    }
}

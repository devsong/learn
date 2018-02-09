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

    @Test
    public void testLongAtomic() throws InterruptedException {
        // 获取并打印当前JVM是32位还是64位的
        String arch = System.getProperty("sun.arch.data.model");
        System.out.println(arch + "-bit");
        LongAtomTest t1 = new LongAtomTest(1);
        LongAtomTest t2 = new LongAtomTest(-1);
        Thread T1 = new Thread(t1);
        Thread T2 = new Thread(t2);
        T1.start();
        T2.start();
        T1.join();
        T2.join();
    }
}


class LongAtomTest implements Runnable {
    private static long field = 0;

    private volatile long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public LongAtomTest(long value) {
        this.setValue(value);
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 100000) {
            LongAtomTest.field = this.getValue();
            i++;
            long temp = LongAtomTest.field;
            if (temp != 1L && temp != -1L) {
                System.out.println("出现错误结果" + temp);
                System.exit(0);
            }
        }
        System.out.println("运行正确");
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

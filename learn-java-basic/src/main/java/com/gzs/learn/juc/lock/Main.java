package com.gzs.learn.juc.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Main {
    public void testMethod(int threadNum, Lock lock, String tag) throws Exception {
        final CountDownLatch startLatch = new CountDownLatch(1);
        final ExecutorService pool = Executors.newFixedThreadPool(threadNum);
        final CountDownLatch endDownLatch = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++) {
            pool.submit(() -> {
                try {
                    startLatch.await();
                    lock.lock();
                    final String name = Thread.currentThread().getName();
                    for (int j = 0; j < 100; j++) {
                        Thread.sleep(1);
                        log.info("tag:{} in thread {},data:{}", tag, name, j);
                    }
                    lock.unlock();
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
                endDownLatch.countDown();
            });
        }

        Thread.sleep(1000);
        startLatch.countDown();
        endDownLatch.await();
    }
}

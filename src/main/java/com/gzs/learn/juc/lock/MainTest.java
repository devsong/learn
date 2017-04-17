package com.gzs.learn.juc.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;

import org.springframework.stereotype.Component;

import com.gzs.learn.CostLog;

@Component
public class MainTest {
    @CostLog
    public void testMethod(int threadNum, Lock lock) throws Exception {
        CountDownLatch startLatch = new CountDownLatch(1);
        ExecutorService pool = Executors.newFixedThreadPool(threadNum);
        CountDownLatch endDownLatch = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++) {
            pool.submit(() -> {
                try {
                    startLatch.await();
                    lock.lock();
                    for (int j = 0; j < 10; j++) {
                        System.out
                                .println("in thread " + Thread.currentThread().getName() + " " + j);
                    }
                    lock.unlock();
                } catch (InterruptedException e) {
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

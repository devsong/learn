package com.gzs.learn.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ThreadPoolExecutorTest {
    @Test
    public void testThreadPoolExecutor() throws InterruptedException {
        final ExecutorService executorService =
                new ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(3));
        for (int i = 0; i < 5; i++) {
            final int index = i;
            executorService.submit(() -> {
                try {
                    Thread.sleep(2 * 1000);
                } catch (final InterruptedException e) {
                }
                System.out.println(index);
            });
        }
        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }
}

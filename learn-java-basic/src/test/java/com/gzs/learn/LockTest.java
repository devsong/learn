package com.gzs.learn;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gzs.learn.juc.lock.CLHLock;
import com.gzs.learn.juc.lock.Main;
import com.gzs.learn.juc.lock.NoLock;
import com.gzs.learn.juc.lock.SpinLock;
import com.gzs.learn.juc.lock.TicketLock;

public class LockTest {
    static ApplicationContext ctx = null;
    static {
        ctx = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
    }

    @Test
    public void testLock() throws Exception {
        final Main test = ctx.getBean(Main.class);
        final int[] threadNum = {2, 10, 20, 50};
        for (final int i : threadNum) {
            System.out.println("thread num is:" + i + "==========");
            test.testMethod(i, new NoLock(), "nolock");
            test.testMethod(i, new SpinLock(), "spin lock");
            test.testMethod(i, new TicketLock(), "ticket lock");
            test.testMethod(i, new ReentrantLock(), "reentrant lock");
            test.testMethod(i, new CLHLock(), "clhlock");
        }
    }

    @Test
    public void test() throws InterruptedException {
        final ExecutorService es = Executors.newFixedThreadPool(3);
        final ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        final CountDownLatch latch = new CountDownLatch(10);
        final CountDownLatch startLatch = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {

            final int index = i;
            es.submit(() -> {
                try {
                    startLatch.await();
                } catch (final InterruptedException e) {
                }
                for (int j = 0; j < 3; j++) {
                    map.put("" + index * 10 + j, j);
                }
                latch.countDown();
            });
        }
        es.submit(() -> {
            try {
                startLatch.await();
            } catch (final InterruptedException e) {
            }
            for (int j = 0; j < 30; j++) {
                System.out.println(map.size());
            }
            latch.countDown();
        });
        startLatch.countDown();
        latch.await();
    }

    @Test
    public void testLockSupport() {

    }
}

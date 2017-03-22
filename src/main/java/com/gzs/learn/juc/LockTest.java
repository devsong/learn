package com.gzs.learn.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class LockTest {

	@Test
	public void testLock() throws InterruptedException {
		ExecutorService es = Executors.newFixedThreadPool(2);

		Lock lock = new ReentrantLock();
		CountDownLatch latch = new CountDownLatch(2);
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
}

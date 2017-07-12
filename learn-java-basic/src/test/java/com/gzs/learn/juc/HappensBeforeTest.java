package com.gzs.learn.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.junit.Test;

public class HappensBeforeTest {
	private ExecutorService executorService = Executors.newFixedThreadPool(5);
	int a = 1;
	volatile int va = 1;

	@Test
	public void testSingleThread() {
		int a = 1;
		int b = a;
		System.out.println(b);
	}

	@Test
	public void testNotThreadSafe() throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(2);
		CountDownLatch startLatch = new CountDownLatch(1);
		final int count = 100000;
		executorService.submit(() -> {
			try {
				startLatch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < count; i++) {
				a = 0;
				if (a != 0) {
					System.out.println("a was modify by another thread");
				}
			}
			latch.countDown();
		});

		executorService.submit(() -> {
			try {
				startLatch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < count; i++) {
				a = 1;
				if (a != 1) {
					System.out.println("a was modify by another thread too");
				}
			}
			latch.countDown();
		});
		// 排除线程创建的时间消耗
		Thread.sleep(5);
		startLatch.countDown();
		latch.await();
	}

	@Test
	public void testVolatile() throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(2);
		CountDownLatch startLatch = new CountDownLatch(1);
		final int count = 100000;
		executorService.submit(() -> {
			try {
				startLatch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < count; i++) {
				va = 0;
				if (va != 0) {
					System.out.println("va was modify by another thread");
				}
			}
			latch.countDown();
		});

		executorService.submit(() -> {
			try {
				startLatch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < count; i++) {
				va = 1;
				if (va != 1) {
					System.out.println("va was modify by another thread too");
				}
			}
			latch.countDown();
		});
		// 排除线程创建的时间消耗
		Thread.sleep(5);
		startLatch.countDown();
		latch.await();
	}

	@Test
	public void testLock() throws Exception {
		CountDownLatch latch = new CountDownLatch(2);
		CountDownLatch startLatch = new CountDownLatch(1);
		final int count = 100000;
		Lock lock = new ReentrantLock();
		executorService.submit(() -> {
			try {
				startLatch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < count; i++) {
				lock.lock();
				va = 0;
				if (va != 0) {
					System.out.println("va was modify by another thread");
				}
				lock.unlock();
			}
			latch.countDown();
		});

		executorService.submit(() -> {
			try {
				startLatch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < count; i++) {
				lock.lock();
				va = 1;
				if (va != 1) {
					System.out.println("va was modify by another thread too");
				}
				lock.unlock();
			}
			latch.countDown();
		});
		// 排除线程创建的时间消耗
		Thread.sleep(5);
		startLatch.countDown();
		latch.await();
	}
}

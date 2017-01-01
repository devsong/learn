package com.gzs.learn.gc;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class ConcurrentHashMapTest {
	@Test
	public void testConcurrent() throws InterruptedException {
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

		ExecutorService service = Executors.newFixedThreadPool(3);
		CountDownLatch latch = new CountDownLatch(3);
		
		service.submit(() -> {
			for (int i = 0; i < 10; i++) {
				map.put("" + i, i + "");
				System.out.println(map.size());
			}
			latch.countDown();
		});

		service.submit(() -> {
			for (int i = 20; i < 30; i++) {
				map.put("" + i, i + "");
				System.out.println(map.size());
			}
			latch.countDown();
		});

		service.submit(() -> {
			for (int i = 40; i < 50; i++) {
				map.put("" + i, i + "");
				System.out.println(map.size());
			}
			latch.countDown();
		});

		latch.await();
	}
}

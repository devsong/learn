package com.gzs.learn.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.google.common.base.Stopwatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateFormatPerfTest {
	private String pattern = "yyyy-MM-dd HH:mm:ss";
	private int threadNum = 10;
	private int loopTimes = 1000000;
	private final ThreadLocal<SimpleDateFormat> sdfLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat(pattern));
	private ExecutorService executorService = Executors.newFixedThreadPool(10);

	@Test
	public void testSdfLocal() throws InterruptedException {
		Stopwatch stopwatch = Stopwatch.createStarted();
		final CountDownLatch latch = new CountDownLatch(threadNum);
		for (int i = 0; i < threadNum; i++) {
			executorService.submit(() -> {
				for (int j = 0; j < loopTimes; j++) {
					SimpleDateFormat sdf = sdfLocal.get();
					try {
						sdf.parse("2010-10-01 00:00:10");
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				latch.countDown();
			});

		}
		latch.await();
		stopwatch.stop();
		log.info("sdf local cost:" + stopwatch.toString());
	}

	@Test
	public void testSdf() throws InterruptedException {
		Stopwatch stopwatch = Stopwatch.createStarted();
		final CountDownLatch latch = new CountDownLatch(threadNum);
		for (int i = 0; i < threadNum; i++) {
			executorService.submit(() -> {
				for (int j = 0; j < loopTimes; j++) {
					SimpleDateFormat sdf = new SimpleDateFormat(pattern);
					try {
						sdf.parse("2010-10-01 00:00:10");
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				latch.countDown();
			});

		}
		latch.await();
		stopwatch.stop();
		log.info("sdf cost:" + stopwatch.toString());
	}

	@Test
	public void testPojo() throws InterruptedException {
		Stopwatch stopwatch = Stopwatch.createStarted();
		final CountDownLatch latch = new CountDownLatch(threadNum);
		for (int i = 0; i < threadNum; i++) {
			executorService.submit(() -> {
				for (int j = 0; j < loopTimes; j++) {
					new Pojo();
					// new Object();
				}
				latch.countDown();
			});

		}
		latch.await();
		stopwatch.stop();
		log.info("pojo cost:" + stopwatch.toString());
	}

	@Test
	public void testSdfObject() throws InterruptedException {
		Stopwatch stopwatch = Stopwatch.createStarted();
		final CountDownLatch latch = new CountDownLatch(threadNum);
		for (int i = 0; i < threadNum; i++) {
			executorService.submit(() -> {
				for (int j = 0; j < loopTimes; j++) {
					new SimpleDateFormat();
				}
				latch.countDown();
			});
		}
		latch.await();
		stopwatch.stop();
		log.info(" cost:" + stopwatch.toString());
	}
}

class Pojo {
	int id = 0;
	String name = "123";
	Object object = new Object();
}

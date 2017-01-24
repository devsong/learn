package com.gzs.learn.dubbo;

import java.util.concurrent.CountDownLatch;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProviderContainer {
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);
		new ClassPathXmlApplicationContext("classpath:META-INF/dubbo-provider.xml");
		latch.await();
	}
}

package com.gzs.learn.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerContainer {
	public static void main(String[] args) {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/dubbo-consumer.xml")) {
			context.start();
			SayHello sayHello = context.getBean(SayHello.class);
			for (int i = 0; i < Integer.MAX_VALUE; i++) {
				System.out.println(sayHello.sayHello("hello"));
				Thread.sleep(1000);
			}
		} catch (Exception e) {
		}

	}
}

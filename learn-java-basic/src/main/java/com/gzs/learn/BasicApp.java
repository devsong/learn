package com.gzs.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@ImportResource(locations = { "/META-INF/applicationContext.xml" })
public class BasicApp {
	public static void main(String[] args) {
		SpringApplication.run(BasicApp.class, args);
	}
}

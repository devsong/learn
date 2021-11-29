package com.gzs.learn.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
@Slf4j
public class ExampleApp {
    public static void main(String[] args) {
        SpringApplication.run(ExampleApp.class);
        log.info("start app success");
    }
}

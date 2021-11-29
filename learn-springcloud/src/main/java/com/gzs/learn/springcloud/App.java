package com.gzs.learn.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
@Slf4j
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
        log.info("start app success");
    }
}

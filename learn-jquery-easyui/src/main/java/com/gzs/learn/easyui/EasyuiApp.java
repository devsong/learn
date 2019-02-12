package com.gzs.learn.easyui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableAutoConfiguration
public class EasyuiApp {

    public static void main(String[] args) {
        SpringApplication.run(EasyuiApp.class, args);
    }
}

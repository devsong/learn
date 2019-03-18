package com.gzs.learn.backend.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@ImportResource("classpath:META-INF/spring.xml")
@MapperScan(basePackages = { "com.gzs.learn.backend.admin.repository" })
@ComponentScan(basePackages = { "com.gzs.learn.backend.admin" })
@Slf4j
public class WebApp extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApp.class);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(WebApp.class, args);
        ctx.registerShutdownHook();
        log.info("startup webApp success");
    }
}

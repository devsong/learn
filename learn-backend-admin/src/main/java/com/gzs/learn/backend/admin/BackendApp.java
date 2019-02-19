package com.gzs.learn.backend.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableAspectJAutoProxy
@ImportResource("/META-INF/spring.xml")
@MapperScan(basePackages = { "com.gzs.learn.backend.admin.dao" })
@ComponentScan(basePackages = { "com.gzs.learn.backend.admin" })
public class BackendApp extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BackendApp.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApp.class, args);
    }
}

package com.gzs.learn.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@EnableAutoConfiguration
@SpringBootApplication
@ImportResource("classpath:/META-INF/applicationContext.xml")
public class ElasticsearchApp {
    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApp.class, args);
    }
}

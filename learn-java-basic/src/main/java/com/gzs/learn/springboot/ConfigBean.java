package com.gzs.learn.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBean {

    @Bean
    public FooBeanPostProcessor fooBeanPostProcessor() {
        return new FooBeanPostProcessor();
    }
}

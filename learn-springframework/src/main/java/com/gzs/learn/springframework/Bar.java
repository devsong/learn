package com.gzs.learn.springframework;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Bar implements InitializingBean, DisposableBean {

    public void hello() {
        System.out.println("hello bar");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("foo -->init bean");
    }

    @Override
    public void destroy() throws Exception {
        log.info("DisposableBean-->destroy");
    }
}
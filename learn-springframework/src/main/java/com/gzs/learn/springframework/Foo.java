package com.gzs.learn.springframework;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component()
@Slf4j
@Data
public class Foo implements InitializingBean, DisposableBean {

    private String msg;

    public void hello() {
        System.out.println(msg);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("foo -->init bean foo");
    }

    @Override
    public void destroy() throws Exception {
        log.info("DisposableBean-->destroy foo");
    }
}

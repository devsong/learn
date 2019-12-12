package com.gzs.learn.springframework;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

@Component()
public class FooForBpp implements BeanPostProcessor,PriorityOrdered {

    public void hello() {
        System.out.println("hello foo");
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // log.info("BeanPostProcessor--->postProcessAfterInitialization {}", beanName);
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // if (bean instanceof Foo) {
        // ((Foo) bean).setMsg("foo");
        // }
        // log.info("BeanPostProcessor--->postProcessBeforeInitialization {}", beanName);
        return bean;
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}

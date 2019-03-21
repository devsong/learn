package com.gzs.learn.springframework;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component()
@Slf4j
public class FooForBpp implements BeanPostProcessor, BeanFactoryPostProcessor {

    public void hello() {
        System.out.println("hello foo");
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("BeanPostProcessor--->postProcessAfterInitialization");
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("BeanPostProcessor--->postProcessBeforeInitialization");
        return bean;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info("BeanFactoryPostProcessor-->post bean factory invoked");
    }
}

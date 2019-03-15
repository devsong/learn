package com.gzs.learn.backend.admin.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component("springContextUtil")
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext appContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.appContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return appContext;
    }

    public static <T> T getBean(Class<T> requiredType) throws BeansException {
        try {
            return appContext.getBean(requiredType);
        } catch (Exception e) {
            throw new RuntimeException("获取的Bean不存在！");
        }
    }

    public static Object getBean(String name) throws BeansException {
        try {
            return appContext.getBean(name);
        } catch (Exception e) {
            throw new RuntimeException("获取的Bean不存在！");
        }
    }

    public static <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return appContext.getBean(name, requiredType);
    }

    public static boolean containsBean(String name) {
        return appContext.containsBean(name);
    }

    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return appContext.isSingleton(name);
    }

    public static Class<? extends Object> getType(String name) throws NoSuchBeanDefinitionException {
        return appContext.getType(name);
    }

    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
        return appContext.getAliases(name);
    }
}
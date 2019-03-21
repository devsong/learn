package com.gzs.learn.springframework;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        FooForBpp bean = ctx.getBean(FooForBpp.class);
        bean.hello();
        ctx.registerShutdownHook();
        ctx.close();
    }
}

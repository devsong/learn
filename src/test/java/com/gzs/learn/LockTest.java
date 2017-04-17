package com.gzs.learn;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gzs.learn.juc.lock.MainTest;
import com.gzs.learn.juc.lock.SpinLock;

public class LockTest {
    ApplicationContext ctx = null;

    @Before()
    public void before() {
        ctx = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
    }

    @Test
    public void testLock() throws Exception {
        MainTest test = ctx.getBean(MainTest.class);
        test.testMethod(10, new SpinLock());
    }
}

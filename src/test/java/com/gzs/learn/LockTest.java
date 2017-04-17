package com.gzs.learn;

import java.util.concurrent.locks.ReentrantLock;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gzs.learn.juc.lock.Main;
import com.gzs.learn.juc.lock.NoLock;
import com.gzs.learn.juc.lock.SpinLock;
import com.gzs.learn.juc.lock.TicketLock;

public class LockTest {
    ApplicationContext ctx = null;

    @Before()
    public void before() {
        ctx = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
    }

    @Test
    public void testLock() throws Exception {
        Main test = ctx.getBean(Main.class);
        int[] threadNum = {10, 50, 100};
        for (int i : threadNum) {
            System.out.println("thread num is:" + i + "==========");
            test.testMethod(i, new NoLock());
            test.testMethod(i, new SpinLock());
            test.testMethod(i, new TicketLock());
            test.testMethod(i, new ReentrantLock());
        }
    }
}

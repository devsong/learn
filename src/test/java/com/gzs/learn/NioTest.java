package com.gzs.learn;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gzs.learn.nio.FileTest;

public class NioTest {
    ApplicationContext ctx = null;

    @Before()
    public void before() {
        ctx = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
    }

    @Test
    public void t() {

    }

    @Test
    public void testFile() throws IOException {
        FileTest test = ctx.getBean(FileTest.class);
        test.writeFile();
        test.writeNioFile();
    }
}

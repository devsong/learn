package com.gzs.learn;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gzs.learn.nio.file.FileTest;

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
        final FileTest test = ctx.getBean(FileTest.class);
        test.writeFile();
        test.writeNioFile();
    }

    @Test
    public void testHashPut() {
        final Map<String, Integer> map = new HashMap<>();
        map.put("key", 123);
        System.out.println("DZORD7493989779944505515".length());
    }
}

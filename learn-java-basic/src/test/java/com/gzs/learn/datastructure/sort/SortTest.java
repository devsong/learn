package com.gzs.learn.datastructure.sort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Stopwatch;

public class SortTest {

    @Test
    public void testStart() {
        System.out.println("//gfs".startsWith("//"));
    }

    @Test
    public void testDeserize() throws FileNotFoundException {
        for (int i = 0; i < 100; i++) {
            Scanner scanner = new Scanner(new File("/tmp/1"));
            String json = scanner.nextLine();
            Stopwatch stopwatch = Stopwatch.createStarted();
            Object obj = JSON.parseObject(json);
            stopwatch.stop();
            Assert.assertNotNull(obj);
            System.out.println(stopwatch.toString());
            scanner.close();
        }
    }
    
    @Test
    public void testSerize(){
        System.out.println(JSON.toJSONString("123"));
    }
    
}

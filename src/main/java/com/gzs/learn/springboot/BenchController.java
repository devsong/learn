package com.gzs.learn.springboot;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/bench/")
public class BenchController {
    private Random random = new Random();
    private static Object[] lockObj;
    private static AtomicReference<Integer>[] locks;
    private static ReentrantLock[] reentrantLocks;
    static {
        lockObj = new Object[100];
        for (int i = 0; i < lockObj.length; i++) {
            lockObj[i] = new Object();
        }

        locks = new AtomicReference[100];
        for (int i = 0; i < locks.length; i++) {
            locks[i] = new AtomicReference<Integer>(null);
        }
        reentrantLocks = new ReentrantLock[100];
        for (int i = 0; i < reentrantLocks.length; i++) {
            reentrantLocks[i] = new ReentrantLock();
        }
    }

    @RequestMapping("a/{id}")
    @ResponseBody
    public long a(@PathVariable("id") int id) throws Exception {
        long start = System.currentTimeMillis();
        int index = id % 100;
        long inner = 0;
        synchronized (lockObj[index]) {
            inner = test();
        }
        long result = System.currentTimeMillis() - start;
        System.out.println("all: " + result + " inner: " + inner);
        return result;
    }

    @RequestMapping("b/{id}")
    @ResponseBody
    public long b(@PathVariable("id") int id) throws Exception {
        long start = System.currentTimeMillis();
        id = id % 100;
        AtomicReference<Integer> lock = locks[id];
        int b = 0;
        while (!lock.compareAndSet(null, id)) {
        }
        long inner = test();
        boolean flag = lock.compareAndSet(id, null);
        long result = System.currentTimeMillis() - start;
        System.out.println("all: " + result + " inner: " + inner + " flag:" + flag);
        System.out.println(b);
        return result;
    }

    @RequestMapping("c/{id}")
    @ResponseBody
    public long c(@PathVariable("id") int id) throws Exception {
        long start = System.currentTimeMillis();
        id = id % 100;
        ReentrantLock lock = reentrantLocks[id];
        lock.lock();
        long inner = test();
        lock.unlock();
        long result = System.currentTimeMillis() - start;
        System.out.println("all: " + result + " inner: " + inner);
        return result;
    }
    
    @RequestMapping("d/{id}")
    @ResponseBody
    public long d(@PathVariable("id") int id) throws Exception {
        long start = System.currentTimeMillis();
        id = id % 100;
        AtomicReference<Integer> lock = locks[id];
        int b = 0;
        while (!lock.compareAndSet(null, id)) {
            Thread.sleep(0,5000);
        }
        long inner = test();
        boolean flag = lock.compareAndSet(id, null);
        long result = System.currentTimeMillis() - start;
        System.out.println("all: " + result + " inner: " + inner + " flag:" + flag);
        System.out.println(b);
        return result;
    }

    public long test() throws Exception {
        long innerstart = System.currentTimeMillis();
        Thread.sleep(10);
        // Thread.sleep(500);
        System.out.println(System.currentTimeMillis() - innerstart);
        return System.currentTimeMillis() - innerstart;
    }
}

package com.gzs.learn.springboot;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Stopwatch;

@Controller
@RequestMapping("/bench/")
public class BenchController {
    private static Object[] lockObj;
    private static AtomicInteger[] locks = new AtomicInteger[100];
    private static ReentrantLock[] reentrantLocks;
    static {
        lockObj = new Object[100];
        for (int i = 0; i < lockObj.length; i++) {
            lockObj[i] = new Object();
        }

        for (int i = 0; i < locks.length; i++) {
            locks[i] = new AtomicInteger(-1);
        }
        reentrantLocks = new ReentrantLock[100];
        for (int i = 0; i < reentrantLocks.length; i++) {
            reentrantLocks[i] = new ReentrantLock();
        }
    }

    @RequestMapping("sync/{id}")
    @ResponseBody
    public long a(@PathVariable("id") int id) throws Exception {
        Stopwatch stopwatch = Stopwatch.createStarted();
        final int index = id % 100;
        long inner = 0;
        synchronized (lockObj[index]) {
            inner = this.test();
        }
        stopwatch.stop();
        long cost = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.println("all: " + cost + " inner: " + inner);
        return cost;
    }

    @RequestMapping("cas/{id}")
    @ResponseBody
    public long b(@PathVariable("id") int id) throws Exception {
        Stopwatch stopwatch = Stopwatch.createStarted();
        id = id % 100;
        final AtomicInteger lock = locks[id];
        final int b = 0;
        while (!lock.compareAndSet(-1, id)) {
        }
        final long inner = this.test();
        final boolean flag = lock.compareAndSet(id, -1);
        stopwatch.stop();
        long cost = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.println("all: " + cost + " inner: " + inner + " flag:" + flag);
        System.out.println(b);
        return cost;
    }

    @RequestMapping("c/{id}")
    @ResponseBody
    public long c(@PathVariable("id") int id) throws Exception {
        final long start = System.currentTimeMillis();
        id = id % 100;
        final ReentrantLock lock = reentrantLocks[id];
        lock.lock();
        final long inner = this.test();
        lock.unlock();
        final long result = System.currentTimeMillis() - start;
        System.out.println("all: " + result + " inner: " + inner);
        return result;
    }

    @RequestMapping("d/{id}")
    @ResponseBody
    public long d(@PathVariable("id") int id) throws Exception {
        final long start = System.currentTimeMillis();
        id = id % 100;
        final AtomicInteger lock = locks[id];
        final int b = 0;
        while (!lock.compareAndSet(-1, id)) {
            Thread.sleep(0, 5000);
        }
        final long inner = this.test();
        final boolean flag = lock.compareAndSet(id, -1);
        final long result = System.currentTimeMillis() - start;
        System.out.println("all: " + result + " inner: " + inner + " flag:" + flag);
        System.out.println(b);
        return result;
    }

    public long test() throws Exception {
        final long innerstart = System.currentTimeMillis();
        Thread.sleep(10);
        // Thread.sleep(500);
        System.out.println(System.currentTimeMillis() - innerstart);
        return System.currentTimeMillis() - innerstart;
    }
}

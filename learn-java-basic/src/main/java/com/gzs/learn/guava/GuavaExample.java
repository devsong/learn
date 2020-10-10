package com.gzs.learn.guava;

import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.google.common.base.Optional;
import com.google.common.base.Stopwatch;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.util.concurrent.RateLimiter;
import com.gzs.learn.guava.eventbus.BtnEvent;
import com.gzs.learn.guava.eventbus.EventBusRecorder;
import com.gzs.learn.guava.eventbus.MouseEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GuavaExample {

    @Test
    public void testOptional() {
        Optional<Integer> optional = Optional.of(1);
        if (optional.isPresent()) {
            System.out.println(optional.get());
        }
    }

    @Test
    public void testRateLimter() throws InterruptedException {
        RateLimiter rateLimiter = RateLimiter.create(100);
        int task = 10;
        long start = System.nanoTime();
        ExecutorService es = Executors.newFixedThreadPool(task);
        long end = System.nanoTime();
        System.out.println("new thread pool cost:" + (end - start));

        start = System.nanoTime();
        SimpleDateFormat sdf = new SimpleDateFormat();
        end = System.nanoTime();
        System.out.println("new sdf cost:" + (end - start));

        System.out.println(sdf);
        CountDownLatch latch = new CountDownLatch(task);
        CountDownLatch startLatch = new CountDownLatch(1);
        Stopwatch stopwatch = Stopwatch.createStarted();
        for (int i = 0; i < task; i++) {
            final int index = i;
            es.submit(() -> {
                try {
                    startLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final double acquire = rateLimiter.acquire();
                System.out.println(String.format("task -->%s,cost -->%s", index, acquire));
                // log.info("task -->{},cost -->{}", index, acquire);
                latch.countDown();
            });
        }
        startLatch.countDown();
        latch.await();
        stopwatch.stop();
        System.out.println("cose time is:" + stopwatch.toString());
    }

    @Test
    public void testEventBus() throws InterruptedException {
        int task = 100;
        AsyncEventBus asyncEventBus = new AsyncEventBus(Executors.newFixedThreadPool(10));
        EventBus eventBus = new EventBus();
        EventBusRecorder recorder = new EventBusRecorder();
        EventBusRecorder asyncRecorder = new EventBusRecorder();
        ExecutorService executorService = Executors.newFixedThreadPool(task);
        eventBus.register(recorder);
        asyncEventBus.register(asyncRecorder);
        CountDownLatch latch = new CountDownLatch(task);
        for (int i = 0; i < task; i++) {
            final int index = i;
            executorService.submit(() -> {
                for (int j = 0; j < 1000; j++) {
                    MouseEvent event = new MouseEvent();
                    event.setIndex(index);
                    eventBus.post(event);
                    asyncEventBus.post(event);

                    BtnEvent btnEvent = new BtnEvent();
                    btnEvent.setIndex(index);
                    eventBus.post(btnEvent);
                    asyncEventBus.post(btnEvent);
                }
                latch.countDown();
            });
        }
        latch.await();
        log.info("EventBus index:{} atomicIndex:{}", recorder.getIndex(), recorder.getAtomicIndex());
        log.info("AsyncEventBus index:{} atomicIndex:{}", asyncRecorder.getIndex(), asyncRecorder.getAtomicIndex());
    }
}

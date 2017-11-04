package com.gzs.learn.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    static ThreadLocal<LocalObject> local = new ThreadLocal<LocalObject>() {
        @Override
        protected LocalObject initialValue() {
            return new LocalObject(SafeShareObject.share, NotSafeShareObject.share);
        };
    };

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 10000; i++) {
            executorService.submit(() -> local.get().getShareObject().increment());
            executorService.submit(() -> local.get().getNotSafeShareObject().increment());
        }
        executorService.awaitTermination(3, TimeUnit.SECONDS);
        executorService.shutdown();
        System.out.println(local.get().getShareObject().getCount());
        System.out.println(local.get().getNotSafeShareObject().getCount());
    }
}

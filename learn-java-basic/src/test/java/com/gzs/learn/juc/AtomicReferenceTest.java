package com.gzs.learn.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

import org.junit.Test;

public class AtomicReferenceTest {
    @Test
    public void testAtomicReferenct() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Object initObject = new Object();
        AtomicReference<Object> reference = new AtomicReference<Object>(initObject);
        Object object1 = new Object();
        Object object2 = new Object();
        executorService.submit(() -> {
            System.out.println("ref 1" + reference.compareAndSet(initObject, object1));
            System.out.println("ref 2" + reference.compareAndSet(object1, initObject));
        });

        executorService.submit(() -> {
            System.out.println("ref 3" + reference.compareAndSet(initObject, object2));
        });

        System.out.println("==============");
        int stamp = 0;
        int updateStamp = stamp + 1;
        AtomicStampedReference<Object> stampedReference =
                new AtomicStampedReference<Object>(initObject, stamp);
        executorService.submit(() -> {
            System.out.println("stamp 1"
                    + stampedReference.compareAndSet(initObject, object1, stamp, updateStamp));
            int expectStamp = updateStamp;
            int newUpdateStamp = expectStamp + 1;
            System.out.println("stamp 2" + stampedReference.compareAndSet(object1, initObject,
                    expectStamp, newUpdateStamp));
        });

        executorService.submit(() -> {
            try {
                Thread.sleep(1 * 1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("stamp 3" + stampedReference.compareAndSet(initObject, object2,
                    stampedReference.getStamp(), stampedReference.getStamp() + 1));
        });

        executorService.awaitTermination(3, TimeUnit.SECONDS);
    }
}

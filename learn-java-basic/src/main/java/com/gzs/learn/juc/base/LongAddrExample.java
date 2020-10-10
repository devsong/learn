package com.gzs.learn.juc.base;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.LongAdder;

import org.junit.Test;

import lombok.Data;
import sun.misc.Unsafe;

@SuppressWarnings("restriction")
public class LongAddrExample {

    public static Unsafe getUnsafe() {
        try {
            return Unsafe.getUnsafe();
        } catch (SecurityException tryReflectionInstead) {
        }
        try {
            return AccessController.doPrivileged((PrivilegedExceptionAction<Unsafe>) () -> {
                Class<Unsafe> k = Unsafe.class;
                for (Field f : k.getDeclaredFields()) {
                    f.setAccessible(true);
                    Object x = f.get(null);
                    if (k.isInstance(x)) {
                        return k.cast(x);
                    }
                }
                throw new NoSuchFieldError("the Unsafe");
            });
        } catch (java.security.PrivilegedActionException e) {
            throw new RuntimeException("Could not initialize intrinsics", e.getCause());
        }
    }

    @Test
    public void testThreadProbe() {

    }

    @Test
    public void testUnSafe() throws NoSuchFieldException, SecurityException {
        Unsafe unsafe = getUnsafe();
        UnSafeTest test = new UnSafeTest(1);
        long offset = unsafe.objectFieldOffset(UnSafeTest.class.getDeclaredField("val"));
        int origin = unsafe.getAndSetInt(test, offset, 2);
        System.out.println("origin:" + origin + " after swap:" + test.getVal());
        unsafe.putLong(test, offset, 3);
        System.out.println("after put:" + test.getVal());
    }

    @Test
    public void testMultiThread() throws NoSuchFieldException, SecurityException, InterruptedException {
        LongAdder adder = new LongAdder();
        int nthread = 1;
        CountDownLatch latch = new CountDownLatch(nthread);
        for (int i = 0; i < nthread; i++) {
            Thread t = new Example(adder, latch);
            t.start();
        }

        latch.await();
        System.out.println(adder.longValue());
    }

    @Test
    public void testNcpu() {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}

@Data
class UnSafeTest {
    private int val;

    public UnSafeTest(int init) {
        val = init;
    }
}

class Example extends Thread {
    private LongAdder adder;

    private CountDownLatch latch;

    public Example(LongAdder adder, CountDownLatch latch) throws NoSuchFieldException, SecurityException {
        this.adder = adder;
        this.latch = latch;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            adder.increment();
        }
        latch.countDown();
    }
}

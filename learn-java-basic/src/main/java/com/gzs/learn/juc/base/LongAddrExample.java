package com.gzs.learn.juc.base;

import java.security.PrivilegedExceptionAction;
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
            return java.security.AccessController
                    .doPrivileged((PrivilegedExceptionAction<Unsafe>) () -> {
                        Class<Unsafe> k = Unsafe.class;
                        for (java.lang.reflect.Field f : k.getDeclaredFields()) {
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
    public synchronized void testMultiThread() throws NoSuchFieldException, SecurityException {
        LongAdder adder = new LongAdder();

        for (int i = 0; i < 10; i++) {
            Thread t = new Example(adder);
            t.start();
        }
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

    public Example(LongAdder adder) throws NoSuchFieldException, SecurityException {
        this.adder = adder;
    }

    @Override
    public void run() {
        adder.increment();
    }
}

package com.gzs.learn.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;

public class SafeShareObject {
    public static SafeShareObject share = new SafeShareObject();

    private AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }
}


class NotSafeShareObject {
    public static NotSafeShareObject share = new NotSafeShareObject();

    private int count = 0;

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

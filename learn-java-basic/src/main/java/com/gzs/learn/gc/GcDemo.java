package com.gzs.learn.gc;

import java.util.concurrent.CountDownLatch;

public class GcDemo {
    public static byte[] s_arr = new byte[1024 * 1024 * 2];

    public static void main(String[] args) throws InterruptedException {
        final int looptime = 10;
        final CountDownLatch latch = new CountDownLatch(looptime);

        for (int i = 0; i < looptime; i++) {
            new java.lang.Thread(() -> {
                final byte[] arr = new byte[1024 * 1024 * 10];
                // System.gc();
                System.out.println(arr.length);
                latch.countDown();
            }).start();
        }

        latch.await();
        // final StringBuffer sb1 = new StringBuffer("a");
        //
        // final StringBuffer sb2 = new StringBuffer("a");
        //
        // System.out.println(sb1.toString().equals(sb2.toString()));
        //
        // final Byte a = new Byte((byte) 3);
        // final Byte b = new Byte((byte) 3);
        //
        // System.out.println(a.equals(b));
    }
}

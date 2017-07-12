package com.gzs.learn;

import org.junit.Test;

import com.gzs.learn.juc.notify.ObjectNotify;
import com.gzs.learn.juc.notify.ReentrantLockNotify;

public class NotifyTest {

    @Test
    public void testObjectNotify() throws Exception {
        ObjectNotify ob = new ObjectNotify();
        int len = 256;
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < len; i++) {
                try {
                    ob.write(i);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < len; i++) {
                try {
                    ob.read();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    @Test
    public void testReentrantNotify() throws Exception {
        ReentrantLockNotify ob = new ReentrantLockNotify();
        int len = 256;
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < len; i++) {
                try {
                    ob.write(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.setName("w1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < len; i++) {
                try {
                    ob.write(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.setName("w2");
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < len; i++) {
                try {
                    ob.read();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t3.setName("r1");

        t1.start();
       // t2.start();
        t3.start();
        t1.join();
        //t2.join();
        t3.join();
    }
}

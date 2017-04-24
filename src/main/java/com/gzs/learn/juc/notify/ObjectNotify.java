package com.gzs.learn.juc.notify;

public class ObjectNotify {
    private Object lock = new Object();
    private int[] buffers = new int[10];
    private int writeIndex = 0;
    private int readIndex = 0;
    private int limit = 0;
    private int capcity = buffers.length;

    public void write(int msg) throws InterruptedException {
        synchronized (lock) {
            if (limit == capcity) {
                lock.wait();
            }
            if (writeIndex < 0) {
                writeIndex = 0;
            }
            buffers[writeIndex++ % capcity] = msg;
            System.out.println("write msg:" + msg);
            Thread.sleep(1000);
            limit++;
            lock.notifyAll();
        }
    }

    public void read() throws InterruptedException {
        synchronized (lock) {
            if (limit == 0) {
                lock.wait();
            }
            if (readIndex < 0) {
                readIndex = 0;
            }
            int msg = buffers[readIndex++ % capcity];
            System.out.println("recv msg:" + msg);
            limit--;
            lock.notifyAll();
        }
    }
}

package com.gzs.learn.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;


public class PipeIoExample {
    public static void main(String[] args) throws Exception {
        PipedOutputStream os = new PipedOutputStream();
        PipedInputStream in = new PipedInputStream(os);
        new Thread(() -> {
            try {
                os.write("hello pipeline".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                byte[] buffer = new byte[512];
                int len = in.read(buffer);
                System.out.println("len:" + len);
                System.out.println(new String(buffer, 0, len));
            } catch (Exception e) {
            }
        }).start();
        Thread.sleep(3 * 1000L);
        in.close();
    }

}

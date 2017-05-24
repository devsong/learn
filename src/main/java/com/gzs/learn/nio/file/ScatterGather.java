package com.gzs.learn.nio.file;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class ScatterGather {
    @Test
    public void scatter() throws Exception {
        int len = 1024 * 1024 * 6;
        ByteBuffer head = ByteBuffer.allocate(len);
        ByteBuffer body = ByteBuffer.allocate(len);

        ByteBuffer[] buffers = {head, body};
        RandomAccessFile file = new RandomAccessFile("/tmp/file.data", "r");
        FileChannel channel = file.getChannel();
        channel.read(buffers);
        System.out.println(head.position());
        System.out.println(body.position());
        file.close();
        // channel
    }

    @Test
    public void transfer() throws Exception {
        RandomAccessFile file = new RandomAccessFile("/tmp/file.data", "r");
        FileChannel fromChannel = file.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("/tmp/file.data.out", "rw");
        FileChannel toChannel = toFile.getChannel();
        fromChannel.transferTo(0, fromChannel.size(), toChannel);

        toChannel.transferFrom(fromChannel, 0, fromChannel.size());
        fromChannel.close();
        toChannel.close();
        toFile.close();
        file.close();
    }

}

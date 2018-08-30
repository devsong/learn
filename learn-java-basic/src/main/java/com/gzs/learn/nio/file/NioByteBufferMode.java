package com.gzs.learn.nio.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class NioByteBufferMode {

    public static String FILE_PATH = "/tmp/learn/123.txt";

    public static String FILE_PATH_TO = "/tmp/learn/321.txt";

    @Test
    public void testByteBuffer() throws FileNotFoundException {
        FileChannel channel = null;
        RandomAccessFile file = new RandomAccessFile(new File(FILE_PATH), "rw");
        try {
            channel = file.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(5);
            int read = channel.read(buffer);
            while (read != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
                buffer.clear();
                read = channel.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                channel.close();
                file.close();
            } catch (IOException e) {
            }
        }
    }


    @Test
    public void testFileTransferTo() throws IOException {
        FileChannel from = null, to = null;
        RandomAccessFile fromFile = new RandomAccessFile(new File(FILE_PATH), "rw");
        RandomAccessFile toFile = new RandomAccessFile(new File(FILE_PATH_TO), "rw");
        try {
            from = fromFile.getChannel();
            to = toFile.getChannel();
            int position = 0;
            long count = fromFile.length();

            from.transferTo(position, count, to);
        } finally {
            from.close();
            to.close();
            fromFile.close();
            toFile.close();
        }
    }

    @Test
    public void testFileTransferFrom() throws IOException {
        FileChannel from = null, to = null;
        RandomAccessFile fromFile = new RandomAccessFile(new File(FILE_PATH), "rw");
        RandomAccessFile toFile = new RandomAccessFile(new File(FILE_PATH_TO), "rw");
        try {
            from = fromFile.getChannel();
            to = toFile.getChannel();
            int position = 0;
            long count = toFile.length();
            from.transferFrom(to, position, count);
        } finally {
            from.close();
            to.close();
            fromFile.close();
            toFile.close();
        }
    }

}

package com.gzs.learn.nio.file;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

import com.gzs.learn.nio.FileService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FileServiceImpl implements FileService {
    private static final String ALPHABET = "1234567890qwertyuiop{}[]asdfghjklzxcvbnm:";
    private static String NORMAL_FILE_PATH = "/tmp/learn/normal_file";
    private static String MAPPED_FILE_PATH = "/tmp/learn/mapped_file";
    private static int FILE_SIZE = 8 * 1024 * 1024;
    private static int BUFFER_LEN = 4 * 1024;
    private static byte[] buffer = new byte[BUFFER_LEN];

    static {
        for (int i = 0; i < BUFFER_LEN; i++) {
            buffer[i] = (byte) i;
        }
    }

    @Override
    public void writeNormalFile() {
        long start = System.currentTimeMillis();
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(NORMAL_FILE_PATH))) {
            // int writeTimes = FILE_SIZE / BUFFER_LEN;
            for (int j = 0; j < FILE_SIZE; j++) {
                char c = ALPHABET.charAt(ThreadLocalRandom.current().nextInt(ALPHABET.length()));
                bos.write(c);
            }

            // for (int j = 0; j < writeTimes; j++) {
            // bos.write(buffer);
            // }
            long cost = System.currentTimeMillis() - start;
            log.info("write normal file(fileSize:{}),cost:{}", FILE_SIZE, cost);
        } catch (IOException e) {
            log.error("write normal file error", e);
        }
    }

    @Override
    public void writeMappedFile() {
        long start = System.currentTimeMillis();
        try (RandomAccessFile file = new RandomAccessFile(MAPPED_FILE_PATH, "rw")) {
            FileChannel channel = file.getChannel();
            ByteBuffer b = channel.map(MapMode.PRIVATE, 0, FILE_SIZE);
            ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
            int writeTimes = FILE_SIZE / BUFFER_LEN;
            for (int j = 0; j < writeTimes; j++) {
                b.put(byteBuffer);
                // b.write(byteBuffer, channel.position());
            }
            byteBuffer.clear();
            channel.close();
        } catch (IOException e) {
        }

        long cost = System.currentTimeMillis() - start;
        log.info("write mapped file(fileSize:{}),cost:{}", FILE_SIZE, cost);
    }

    public static void main(String[] args) {
        FileService service = new FileServiceImpl();
        service.writeNormalFile();
        //service.writeMappedFile();
    }

}

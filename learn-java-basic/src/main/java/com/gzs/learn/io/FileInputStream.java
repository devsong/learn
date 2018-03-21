package com.gzs.learn.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import org.junit.Test;

public class FileInputStream {

    private static final String FILE_PATH = "/tmp/learn/normal_file";
    private static final String CHARSET = "utf-8";

    @Test
    public void testWriter() throws IOException {
        Writer writer = new FileWriter(new File(FILE_PATH));
        for (int i = 0; i < 10; i++) {
            writer.write('a' + i);
        }
        writer.close();
    }

    @Test
    public void testReader() throws IOException {
        Reader reader = new FileReader(new File(FILE_PATH));
        char[] buf = new char[1024];
        int len = reader.read(buf);
        for (int i = 0; i < len; i++) {
            System.out.print(buf[i]);
        }
        reader.close();
    }

    @Test
    public void testInputStream() throws IOException {
        InputStream in = new java.io.FileInputStream(new File(FILE_PATH));
        byte[] buf = new byte[1024];
        int len = in.read(buf);
        for (int i = 0; i < len; i++) {
            System.out.print((char) buf[i]);
        }
        in.close();
    }

    @Test
    public void testOutputStreamWriter() throws IOException {
        OutputStream os = new FileOutputStream(new File(FILE_PATH));
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os, CHARSET);
        for (int i = 0; i < 1000; i++) {
            outputStreamWriter.append((char) ('a' + i % 10));
        }
        outputStreamWriter.close();
    }

    @Test
    public void testInputStreamReader() throws IOException {
        InputStream in = new java.io.FileInputStream(new File(FILE_PATH));
        InputStreamReader reader = new InputStreamReader(in, CHARSET);
        int count = 0;
        char[] buf = new char[1024];
        while ((count = reader.read(buf)) != -1) {
            System.out.print(new String(buf, 0, count));
        }
        reader.close();
    }

    @Test
    public void testInputStreamReadOnce() throws IOException {
        InputStream in = new java.io.FileInputStream(new File(FILE_PATH));
        InputStreamReader reader = new InputStreamReader(in, CHARSET);
        int count = 0;
        while ((count = reader.read()) != -1) {
            System.out.print((char) count);
        }
        reader.close();
    }
}

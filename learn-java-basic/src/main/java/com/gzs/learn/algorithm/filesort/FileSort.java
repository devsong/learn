package com.gzs.learn.algorithm.filesort;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class FileSort {

    static final String input_file = "/tmp/data.dat";
    static final String outputFile = "/tmp/output.dat";
    static final String sliceDir = "/tmp/slice_tmp/";

    static final int len = 10;

    public static void createFile() throws IOException {
        File file = new File(input_file);
        FileWriter fileWriter = new FileWriter(file);
        Random random = new Random();

        for (int i = 0; i < len; i++) {
            int ran = random.nextInt(len);
            fileWriter.write(ran + " ");
        }
        fileWriter.close();
    }

    public static void sliceFile() throws IOException {
        FileReader reader = new FileReader(new File(input_file));
        int fileSize = 2;
        int length = len / fileSize;
        for (int i = 0; i < length; i++) {
            char[] buffer = new char[fileSize];
            String fileName = sliceDir + "data_" + i + ".data";
            File fileTmp = new File(fileName);
            if (fileTmp.exists()) {
                fileTmp.delete();
            }
            fileTmp.createNewFile();
            FileWriter fileWriter = new FileWriter(fileTmp);

            reader.read(buffer, i * fileSize, fileSize);
            fileWriter.write(buffer);
            fileWriter.close();
        }
        reader.close();
    }

    public static void sort() {

    }

    public static void main(String[] args) throws IOException {
        createFile();
        sliceFile();
    }
}

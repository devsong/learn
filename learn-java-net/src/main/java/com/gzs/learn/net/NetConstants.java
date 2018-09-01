package com.gzs.learn.net;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.collect.Lists;

/**
 * 常量类
 * 
 * @author guanzhisong
 *
 */
public interface NetConstants {
    int PORT = 10000;

    int BUFFER_SIZE = 1024;

    String BIND_ADDR = "127.0.0.1";

    List<String> EXIT = Lists.newArrayList("quit", "bye", "exit");

    ExecutorService THREAD_POOL = Executors.newFixedThreadPool(10, new NetThreadFactory());
}

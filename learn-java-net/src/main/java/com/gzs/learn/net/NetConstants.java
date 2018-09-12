package com.gzs.learn.net;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
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
    int BUFFER_SIZE = 1024;

    String BIND_ADDR = "127.0.0.1";

    int PORT_DEFAULT = 10000;

    InetSocketAddress SERVER_ADDR = new InetSocketAddress(BIND_ADDR, PORT_DEFAULT);

    Charset UTF8 = Charset.forName("UTF-8");

    List<String> EXIT = Lists.newArrayList("quit", "bye", "exit");

    ExecutorService THREAD_POOL = Executors.newFixedThreadPool(10, new NetThreadFactory());
}

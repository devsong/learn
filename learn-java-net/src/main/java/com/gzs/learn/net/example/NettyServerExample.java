package com.gzs.learn.net.example;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

import com.gzs.learn.net.netty.NettyTimeServer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyServerExample {
    @Test
    public void testTimeServer() {
        int port = Math.abs(ThreadLocalRandom.current().nextInt(Short.MAX_VALUE));
        log.info("start time server on port:{}", port);
        NettyTimeServer timeServer = new NettyTimeServer(port);
        timeServer.start();
    }

}

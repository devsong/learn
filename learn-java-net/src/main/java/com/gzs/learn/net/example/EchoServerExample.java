package com.gzs.learn.net.example;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

import com.gzs.learn.net.EchoServer;
import com.gzs.learn.net.io.StandardEchoServer;
import com.gzs.learn.net.netty.NettyEchoServer;
import com.gzs.learn.net.nio.NioEchoServer;
import com.gzs.learn.net.nio.protocol.EchoProtocolHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EchoServerExample {
    @Test
    public void testIoEchoServer() {
        int port = Math.abs(ThreadLocalRandom.current().nextInt(Short.MAX_VALUE));
        log.info("start io echo server on port:{}", port);
        EchoServer echoServer = new StandardEchoServer(port);
        echoServer.start();
    }

    @Test
    public void testNioEchoServer() {
        int port = Math.abs(ThreadLocalRandom.current().nextInt(Short.MAX_VALUE));
        log.info("start nio echo server on port:{}", port);
        EchoServer echoServer = new NioEchoServer(new EchoProtocolHandler(), port);
        echoServer.start();
    }

    @Test
    public void testNettyEchoServer() {
        int port = Math.abs(ThreadLocalRandom.current().nextInt(Short.MAX_VALUE));
        log.info("start netty echo server on port:{}", port);
        EchoServer echoServer = new NettyEchoServer(port);
        echoServer.start();
    }
}

package com.gzs.learn.net.example;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

import com.gzs.learn.net.DiscardServer;
import com.gzs.learn.net.io.StandardDiscardServer;
import com.gzs.learn.net.netty.NettyDiscardServer;
import com.gzs.learn.net.nio.NioDiscardServer;
import com.gzs.learn.net.nio.protocol.DiscardProtocolHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DiscardServerExample {

    @Test
    public void testIoDiscard() {
        int port = Math.abs(ThreadLocalRandom.current().nextInt(Short.MAX_VALUE));
        log.info("start io discard server on port:{}", port);
        DiscardServer discardServer = new StandardDiscardServer(port);
        discardServer.start();

    }

    @Test
    public void testNioDiscard() {
        int port = Math.abs(ThreadLocalRandom.current().nextInt(Short.MAX_VALUE));
        log.info("start nio discard server on port:{}", port);
        DiscardServer discardServer = new NioDiscardServer(new DiscardProtocolHandler(), port);
        discardServer.start();
    }

    @Test
    public void testNettyDiscard() {
        int port = Math.abs(ThreadLocalRandom.current().nextInt(Short.MAX_VALUE));
        log.info("start nio discard server on port:{}", port);
        DiscardServer discardServer = new NettyDiscardServer(port);
        discardServer.start();
    }
}

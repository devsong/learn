package com.gzs.learn.net.example;

import org.junit.Test;

import com.gzs.learn.net.DiscardServer;
import com.gzs.learn.net.io.StandardDiscardServer;
import com.gzs.learn.net.nio.NioDiscardServer;
import com.gzs.learn.net.nio.protocol.DiscardProtocolHandler;

public class DiscardServerExample {
    
    @Test
    public void testIoDiscard() {
        DiscardServer discardServer = new StandardDiscardServer();
    }
    public static void main(String[] args) {
        // DiscardServer server = new StandardDiscardServer();
        DiscardServer server = new NioDiscardServer(new DiscardProtocolHandler());
        server.start();
    }
}

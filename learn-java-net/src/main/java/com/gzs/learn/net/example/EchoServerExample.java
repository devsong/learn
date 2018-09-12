package com.gzs.learn.net.example;

import com.gzs.learn.net.EchoServer;
import com.gzs.learn.net.nio.NioEchoServer;
import com.gzs.learn.net.nio.protocol.EchoProtocolHandler;

public class EchoServerExample {
    public static void main(String[] args) {
        // EchoServer server = new StandardEchoServer();
        EchoServer server = new NioEchoServer(new EchoProtocolHandler());
        server.start();
    }
}

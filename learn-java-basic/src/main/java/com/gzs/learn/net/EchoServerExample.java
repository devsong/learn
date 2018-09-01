package com.gzs.learn.net;

import com.gzs.learn.net.io.StandardEchoServer;

public class EchoServerExample {
    public static void main(String[] args) {
        EchoServer server = new StandardEchoServer();
        server.start();
    }
}

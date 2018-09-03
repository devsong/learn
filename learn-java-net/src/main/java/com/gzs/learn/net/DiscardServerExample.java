package com.gzs.learn.net;

import com.gzs.learn.net.nio.NioDiscardServer;

public class DiscardServerExample {
    public static void main(String[] args) {
        // DiscardServer server = new StandardDiscardServer();
        DiscardServer server = new NioDiscardServer();
        server.start();
    }
}

package com.gzs.learn.net;

import com.gzs.learn.net.io.StandardDiscardServer;

public class DiscardServerExample {
    public static void main(String[] args) {
        DiscardServer server = new StandardDiscardServer();
        server.start();
    }
}

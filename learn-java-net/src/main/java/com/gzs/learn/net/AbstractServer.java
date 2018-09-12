package com.gzs.learn.net;

public class AbstractServer {
    protected int port;
    protected String addr;

    protected AbstractServer() {
        this(NetConstants.BIND_ADDR, NetConstants.PORT_DEFAULT);
    }

    protected AbstractServer(int port) {
        this(NetConstants.BIND_ADDR, port);
    }

    protected AbstractServer(String addr, int port) {
        this.port = port;
        this.addr = addr;
    }
}

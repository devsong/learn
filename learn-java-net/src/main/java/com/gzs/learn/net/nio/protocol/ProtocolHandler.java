package com.gzs.learn.net.nio.protocol;

import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;

public interface ProtocolHandler {
    void handleAccept(SelectionKey key, ServerSocketChannel serverSocketChannel);

    void handleRecv(SelectionKey key);

    void handleWrite(SelectionKey key);
}

package com.gzs.learn.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

import com.gzs.learn.net.AbstractServer;
import com.gzs.learn.net.DiscardServer;
import com.gzs.learn.net.NetUtils;
import com.gzs.learn.net.nio.protocol.ProtocolHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NioDiscardServer extends AbstractServer implements DiscardServer {
    private Selector selector;
    private ProtocolHandler handler;

    public NioDiscardServer(ProtocolHandler handler) {
        selector = NetUtils.getSelector();
        this.handler = handler;
    }

    public NioDiscardServer(ProtocolHandler handler, int port) {
        super(port);
        selector = NetUtils.getSelector();
        this.handler = handler;
    }

    public NioDiscardServer(ProtocolHandler handler, String addr, int port) {
        super(addr, port);
        selector = NetUtils.getSelector();
        this.handler = handler;
    }

    @Override
    public void start() {
        ServerSocketChannel serverSocketChannel = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(addr, port));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, serverSocketChannel.validOps(), null);
            while (true) {
                int numEvents = selector.select();
                if (numEvents > 0) {
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (!key.isValid()) {
                            continue;
                        }
                        if (key.isAcceptable()) {
                            handler.handleAccept(key, serverSocketChannel);
                        } else if (key.isReadable()) {
                            handler.handleRecv(key);
                        } else if (key.isWritable()) {
                            handler.handleWrite(key);
                        }
                    }
                }
            }
        } catch (IOException e) {
            log.error("init  server error", e);
        } finally {
            NetUtils.close(serverSocketChannel);
        }
    }
}

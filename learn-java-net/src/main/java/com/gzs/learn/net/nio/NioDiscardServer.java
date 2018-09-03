package com.gzs.learn.net.nio;

import static com.gzs.learn.net.NetConstants.BIND_ADDR;
import static com.gzs.learn.net.NetConstants.PORT;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import com.gzs.learn.net.DiscardServer;
import com.gzs.learn.net.NetConstants;
import com.gzs.learn.net.ResourceHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NioDiscardServer implements DiscardServer {
    private Selector selector;
    private InetSocketAddress address = new InetSocketAddress(BIND_ADDR, PORT);

    public NioDiscardServer() {
        try {
            selector = Selector.open();
        } catch (IOException e) {
            log.error("open select error", e);
            System.exit(0);
        }
    }

    @Override
    public void start() {
        ServerSocketChannel serverSocketChannel = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(address);
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
                            accept(key, serverSocketChannel);
                        } else if (key.isReadable()) {
                            recv(key);
                        } else if (key.isWritable()) {
                            write(key);
                        }
                    }
                }
            }
        } catch (IOException e) {
            log.error("init  server error", e);
        } finally {
            ResourceHandler.close(serverSocketChannel);
        }
    }

    private void accept(SelectionKey k, ServerSocketChannel serverSocketChannel) {
        SocketChannel socketChannel = null;
        ByteBuffer buf = ByteBuffer.allocate(NetConstants.BUFFER_SIZE);
        try {
            socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ, buf);
            log.info("accept client:{}", socketChannel.getRemoteAddress());
        } catch (Exception e) {
        }
    }

    private void recv(SelectionKey key) {
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        SocketChannel clientChannel = null;
        try {
            clientChannel = (SocketChannel) key.channel();
            int numRead = clientChannel.read(buffer);

            if (numRead == -1) {
                SocketAddress address = clientChannel.getRemoteAddress();
                log.info("disconnect from remote:{}", address);
                clientChannel.close();
                key.cancel();
                return;
            }

            String remoteAddr = clientChannel.getRemoteAddress().toString();
            String msg = NetConstants.UTF8.decode(buffer).toString();
            log.info("recv client:{} msg:{}", remoteAddr, msg);

            if (NetConstants.EXIT.contains(msg)) {
                clientChannel.close();
                key.cancel();
                return;
            }
        } catch (Exception e) {
            log.error("handle msg error", e);
        }
    }

    private void write(SelectionKey key) {
        SocketChannel socketChannel = null;
        try {
            socketChannel = (SocketChannel) key.channel();
            ByteBuffer buf = (ByteBuffer) key.attachment();
            buf.flip();
            socketChannel.write(buf);
            buf.compact();
        } catch (Exception e) {
        }
    }

}

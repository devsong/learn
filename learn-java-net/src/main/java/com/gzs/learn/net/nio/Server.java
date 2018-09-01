package com.gzs.learn.net.nio;

import static java.nio.channels.SelectionKey.OP_ACCEPT;
import static java.nio.channels.SelectionKey.OP_CONNECT;
import static java.nio.channels.SelectionKey.OP_READ;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Server {
    public static int BACK_LOG = 512;
    public static int BUFFER_SIZE = 1024;
    public static int port = 10000;

    private Selector selector;
    private InetSocketAddress address = new InetSocketAddress("127.0.0.1", port);

    public Server() {
        try {
            selector = Selector.open();
        } catch (IOException e) {
            log.error("open select error", e);
            System.exit(0);
        }
    }

    public boolean start() {
        ServerSocketChannel serverSocketChannel = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(address, 512);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            log.info("init server success");
            while (true) {
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (!key.isValid()) {
                        continue;
                    }
                    if (key.isAcceptable()) {
                        accept(key);
                    } else if (key.isReadable()) {
                        recv(key);
                    }
                }
            }
        } catch (Exception e) {
            log.error("init server socket error", e);
        } finally {
            try {
                if (serverSocketChannel != null) {
                    serverSocketChannel.close();
                    serverSocketChannel = null;
                }
            } catch (IOException e) {
            }
        }
        return true;
    }


    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = channel.accept();
        socketChannel.configureBlocking(false);
        Socket socket = socketChannel.socket();

        SocketAddress remote = socket.getRemoteSocketAddress();
        log.info("accept remote addr:{}", remote.toString());

        socketChannel.register(selector, OP_READ);
    }

    private void recv(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

        int numRead = channel.read(buffer);

        if (numRead == -1) {
            Socket remoteSocket = channel.socket();
            SocketAddress address = remoteSocket.getRemoteSocketAddress();
            log.info("disconnect from remote:{}", address);
            channel.close();
            key.cancel();
            return;
        }

        while (numRead != -1) {
            buffer.flip();
            byte[] content = new byte[buffer.remaining()];
            log.info("recv client msg:{}", new String(content));
            numRead = channel.read(buffer);
        }
    }

}

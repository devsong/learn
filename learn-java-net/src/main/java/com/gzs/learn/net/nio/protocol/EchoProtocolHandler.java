package com.gzs.learn.net.nio.protocol;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import com.gzs.learn.net.NetConstants;
import com.gzs.learn.net.NetUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EchoProtocolHandler implements ProtocolHandler {
    private Selector selector;

    public EchoProtocolHandler() {
        this.selector = NetUtils.getSelector();
    }

    @Override
    public void handleAccept(SelectionKey key, ServerSocketChannel serverSocketChannel) {
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

    @Override
    public void handleRecv(SelectionKey key) {
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
            String msg = new String(NetUtils.convert2Byte(buffer), NetConstants.UTF8);
            buffer.clear();
            clientChannel.write(ByteBuffer.wrap(msg.getBytes()));
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

    @Override
    public void handleWrite(SelectionKey key) {
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

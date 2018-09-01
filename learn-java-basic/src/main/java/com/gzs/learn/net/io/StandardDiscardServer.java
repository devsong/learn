package com.gzs.learn.net.io;

import static com.gzs.learn.net.NetConstants.BIND_ADDR;
import static com.gzs.learn.net.NetConstants.BUFFER_SIZE;
import static com.gzs.learn.net.NetConstants.PORT;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.gzs.learn.net.DiscardServer;
import com.gzs.learn.net.NetConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * 基于传统阻塞io模型
 * 
 * @author guanzhisong
 *
 */
@Slf4j
public class StandardDiscardServer implements DiscardServer {


    @Override
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            InetSocketAddress address = new InetSocketAddress(BIND_ADDR, PORT);
            serverSocket.bind(address);
            while (true) {
                Socket socket = serverSocket.accept();
                byte[] buffer = new byte[BUFFER_SIZE];
                int read = socket.getInputStream().read(buffer);
                if (read == -1) {
                    socket.close();
                    continue;
                }
                String remoteAddr = socket.getRemoteSocketAddress().toString();
                log.info("recv from client:{},msg:{}", remoteAddr, new String(buffer));
                socket.close();
            }
        } catch (IOException e) {
            log.error("init server error", e);
        }
    }
}

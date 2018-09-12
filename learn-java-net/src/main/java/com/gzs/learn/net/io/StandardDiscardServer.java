package com.gzs.learn.net.io;

import static com.gzs.learn.net.NetConstants.BUFFER_SIZE;
import static com.gzs.learn.net.NetConstants.EXIT;
import static com.gzs.learn.net.NetConstants.THREAD_POOL;
import static com.gzs.learn.net.NetConstants.UTF8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.gzs.learn.net.AbstractServer;
import com.gzs.learn.net.DiscardServer;
import com.gzs.learn.net.NetUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 基于传统阻塞io模型
 * 
 * @author guanzhisong
 *
 */
@Slf4j
public class StandardDiscardServer extends AbstractServer implements DiscardServer {

    public StandardDiscardServer() {
        super();
    }

    public StandardDiscardServer(int port) {
        super(port);
    }

    @Override
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket()) {
            InetSocketAddress address = new InetSocketAddress(addr, port);
            serverSocket.bind(address);
            while (true) {
                Socket socket = serverSocket.accept();
                handle(socket);
                // socket.close();
            }
        } catch (IOException e) {
            log.error("init server error", e);
        }
    }

    private void handle(Socket socket) {
        THREAD_POOL.submit(new Worker(socket));
    }
}


@Slf4j
class Worker implements Runnable {
    private Socket socket;

    public Worker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF8),
                    BUFFER_SIZE);
            while (true) {
                String msg = reader.readLine();
                String remoteAddr = socket.getRemoteSocketAddress().toString();
                log.info("recv from client:{},msg:{}", remoteAddr, msg);
                if (EXIT.contains(msg)) {
                    log.info("disconnect from remote:{}", remoteAddr);
                    socket.close();
                    break;
                }
            }
        } catch (IOException e) {
            log.error("handle data error", e);
        } finally {
            NetUtils.close(reader);
        }
    }

}

package com.gzs.learn.net.io;

import static com.google.common.base.Charsets.UTF_8;
import static com.gzs.learn.net.NetConstants.BIND_ADDR;
import static com.gzs.learn.net.NetConstants.BUFFER_SIZE;
import static com.gzs.learn.net.NetConstants.EXIT;
import static com.gzs.learn.net.NetConstants.PORT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.gzs.learn.net.DiscardServer;

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
        try (ServerSocket serverSocket = new ServerSocket()) {
            InetSocketAddress address = new InetSocketAddress(BIND_ADDR, PORT);
            serverSocket.bind(address);
            while (true) {
                Socket socket = serverSocket.accept();
                worker(socket);
                // socket.close();
            }
        } catch (IOException e) {
            log.error("init server error", e);
        }
    }

    private void worker(Socket socket) {
        new Thread(new Worker(socket)).start();
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
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8),
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
        } catch (Exception e) {
            try {
                if (socket != null) {
                    socket.close();
                    socket = null;
                }
                if (reader != null) {
                    reader.close();
                    reader = null;
                }
            } catch (IOException e1) {
                socket = null;
                reader = null;
            }
        }
    }

}

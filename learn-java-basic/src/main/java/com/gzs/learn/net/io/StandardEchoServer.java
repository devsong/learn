package com.gzs.learn.net.io;

import static com.google.common.base.Charsets.UTF_8;
import static com.gzs.learn.net.NetConstants.BIND_ADDR;
import static com.gzs.learn.net.NetConstants.BUFFER_SIZE;
import static com.gzs.learn.net.NetConstants.EXIT;
import static com.gzs.learn.net.NetConstants.PORT;
import static com.gzs.learn.net.NetConstants.THREAD_POOL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.gzs.learn.net.EchoServer;
import com.gzs.learn.net.ResourceHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * 基于传统阻塞io模型
 * 
 * @author guanzhisong
 *
 */
@Slf4j
public class StandardEchoServer implements EchoServer {
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
        THREAD_POOL.submit(new EchoWorker(socket));
    }
}


@Slf4j
class EchoWorker implements Runnable {
    private Socket socket;

    public EchoWorker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8),
                    BUFFER_SIZE);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), UTF_8),
                    BUFFER_SIZE);
            while (true) {
                String msg = reader.readLine();
                String remoteAddr = socket.getRemoteSocketAddress().toString();

                if (EXIT.contains(msg)) {
                    log.info("disconnect from remote:{}", remoteAddr);
                    writer.write("bye-bye" + "\n");
                    writer.flush();
                    socket.close();
                    break;
                }
                log.info("recv from client:{},msg:{}", remoteAddr, msg);
                writer.write(msg + "\n");
                writer.flush();
            }
        } catch (Exception e) {
            ResourceHandler.close(reader, writer, socket);
        }
    }

}

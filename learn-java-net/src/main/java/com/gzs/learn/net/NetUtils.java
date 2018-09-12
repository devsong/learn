package com.gzs.learn.net;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;

/**
 * 简易的资源处理器
 * 
 * @author guanzhisong
 *
 */
public class NetUtils {
    private static volatile Selector selector;

    /**
     * 释放资源
     * 
     * @param closeables
     */
    public static void close(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            try {
                if (closeable != null) {
                    closeable.close();
                    closeable = null;
                }
            } catch (Exception e) {
                closeable = null;
            }
        }
    }

    public static synchronized Selector getSelector() {
        if (selector != null) {
            return selector;
        }
        try {
            selector = Selector.open();
            return selector;
        } catch (IOException e) {
            System.exit(1);
            return null;
        }
    }

    public static byte[] convert2Byte(ByteBuffer buffer) {
        buffer.flip();
        byte[] b = new byte[buffer.remaining()];
        buffer.get(b);
        buffer.compact();
        return b;
    }

    public static ByteBuffer convert2Buffer(byte[] array) {
        return ByteBuffer.wrap(array);
    }

}

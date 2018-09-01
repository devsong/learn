package com.gzs.learn.net;

import java.io.Closeable;

/**
 * 简易的资源处理器
 * 
 * @author guanzhisong
 *
 */
public class ResourceHandler {
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
}

package com.gzs.learn.classloader;

import java.io.IOException;
import java.io.InputStream;

public class CustomerClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String classname) throws ClassNotFoundException {
        if (classname.equals("com.gzs.learn.classloader.ClassA")) {
            final InputStream is =
                    this.getResourceAsStream("com/gzs/learn/classloader/ClassA.class");
            final byte[] body = new byte[8192]; // Should read until EOF
            try {
                final int len = is.read(body);
                return this.defineClass("com.gzs.learn.classloader.ClassA", body, 0, len);
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }

        return super.loadClass(classname, true);
    }
}

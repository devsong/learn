package com.gzs.learn.classloader;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalArgumentException,
            IllegalAccessException, NoSuchFieldException, SecurityException {
        final CustomerClassLoader loader = new CustomerClassLoader();
        final CustomerClassLoader loader2 = new CustomerClassLoader();
        final Class<?> cls1 = loader.loadClass("com.gzs.learn.classloader.ClassA");
        final Class<?> cls2 = loader2.loadClass("com.gzs.learn.classloader.ClassA");
        cls1.getField("a").set(null, 10);
        cls2.getField("a").set(null, 2);
        System.out.println(cls1.getField("a").get(null));
        System.out.println(cls2.getField("a").get(null));
    }
}

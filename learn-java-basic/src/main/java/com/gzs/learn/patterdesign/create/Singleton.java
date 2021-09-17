package com.gzs.learn.patterdesign.create;

public class Singleton {

    private Singleton() {

    }

    private static class SingletonHolder {
        static Singleton singleton = new Singleton();
    }

    public static Singleton getInstence() {
        return SingletonHolder.singleton;
    }

    public void sayHello() {
        System.out.println("hello");
    }

    public static void main(String[] args) {
        Singleton.getInstence().sayHello();
    }
}

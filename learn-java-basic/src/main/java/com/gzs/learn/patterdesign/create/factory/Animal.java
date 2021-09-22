package com.gzs.learn.patterdesign.create.factory;

public interface Animal {

    public default void sayHello() {
        System.out.print(getName() + " ");
    }

    String getName();
}

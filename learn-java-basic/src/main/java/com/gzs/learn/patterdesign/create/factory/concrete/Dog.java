package com.gzs.learn.patterdesign.create.factory.concrete;

import com.gzs.learn.patterdesign.create.factory.Animal;

public class Dog implements Animal {

    private String name;

    public Dog() {
        this.name = "dog";
    }

    @Override
    public String getName() {
        return this.name;
    }
}

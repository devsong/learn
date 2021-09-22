package com.gzs.learn.patterdesign.create.factory.concrete;

import com.gzs.learn.patterdesign.create.factory.Animal;

public class Cat implements Animal {

    private String name;

    public Cat() {
        this.name = "cat";
    }

    @Override
    public String getName() {
        return this.name;
    }
}

package com.gzs.learn.patterdesign.create.factory.concrete;

import com.gzs.learn.patterdesign.create.factory.Animal;

public class Ultraman implements Animal {
    private String name;

    public Ultraman() {
        this.name = "Ultraman";
    }

    @Override
    public String getName() {
        return this.name;
    }

}

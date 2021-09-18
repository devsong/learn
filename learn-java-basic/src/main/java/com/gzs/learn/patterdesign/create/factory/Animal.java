package com.gzs.learn.patterdesign.create.factory;

import lombok.Data;

@Data
public class Animal {
    protected String name;

    public void sayHello() {
        System.out.println(this.name);
    };
}

package com.gzs.learn.patterdesign.create;

import lombok.Data;

@Data
public class Prototype implements Cloneable {
    private String str = "Prototype";

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    public static void main(String[] args) throws CloneNotSupportedException {
        Prototype prototype = new Prototype();
        Prototype clone = (Prototype) prototype.clone();
        System.out.println(clone.getStr());
    }
}

package com.gzs.learn.patterdesign.create.factory.simple;

import com.gzs.learn.patterdesign.create.factory.Animal;
import com.gzs.learn.patterdesign.create.factory.concrete.Cat;
import com.gzs.learn.patterdesign.create.factory.concrete.Dog;
import com.gzs.learn.patterdesign.create.factory.concrete.Ultraman;

public class SimpleFactory {

    public static final String KIND_DOG = "dog";
    public static final String KIND_CAT = "cat";
    public static final String KIND_ULTRAMAN = "ultraman";

    public static Animal buildAnimal(String kind) {
        Animal animal = null;
        switch (kind) {
            case KIND_DOG:
                animal = new Dog();
                break;
            case KIND_CAT:
                animal = new Cat();
                break;
            case KIND_ULTRAMAN:
                animal = new Ultraman();
                break;
            default:
                break;
        }
        return animal;
    }
}

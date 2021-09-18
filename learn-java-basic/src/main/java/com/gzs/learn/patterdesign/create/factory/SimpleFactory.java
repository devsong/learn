package com.gzs.learn.patterdesign.create.factory;

public class SimpleFactory {

    public static final String KIND_DOG = "dog";
    public static final String KIND_CAT = "cat";

    public static Animal buildAnimal(String kind) {
        Animal animal = null;
        switch (kind) {
            case KIND_DOG:
                animal = new Dog();
                break;
            case KIND_CAT:
                animal = new Cat();
                break;
            default:
                break;
        }
        return animal;
    }

    public static void main(String[] args) {
        Animal animal = SimpleFactory.buildAnimal(SimpleFactory.KIND_DOG);
        animal.sayHello();
    }
}

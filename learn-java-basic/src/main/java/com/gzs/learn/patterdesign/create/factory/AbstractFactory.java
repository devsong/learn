package com.gzs.learn.patterdesign.create.factory;

public abstract class AbstractFactory {
    abstract Animal buildAnimal();

    public static void main(String[] args) {
        AbstractFactory abstractFactory = new DogFactory();
        Animal animal = abstractFactory.buildAnimal();
        System.out.println(animal);
    }
}

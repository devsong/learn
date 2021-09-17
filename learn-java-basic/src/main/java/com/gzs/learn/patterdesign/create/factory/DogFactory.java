package com.gzs.learn.patterdesign.create.factory;

public class DogFactory extends AbstractFactory {

    @Override
    Animal buildAnimal() {
        return SimpleFactory.buildAnimal(SimpleFactory.KIND_DOG);
    }

}

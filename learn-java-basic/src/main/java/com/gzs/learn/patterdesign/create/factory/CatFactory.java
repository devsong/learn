package com.gzs.learn.patterdesign.create.factory;

public class CatFactory extends AbstractFactory {

    @Override
    Animal buildAnimal() {
        return SimpleFactory.buildAnimal(SimpleFactory.KIND_CAT);
    }

}

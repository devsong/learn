package com.gzs.learn.patterdesign.create.factory;

public class CatFactory implements AnimalFactory {

    @Override
    public Animal buildAnimal() {
        return SimpleFactory.buildAnimal(SimpleFactory.KIND_CAT);
    }

}

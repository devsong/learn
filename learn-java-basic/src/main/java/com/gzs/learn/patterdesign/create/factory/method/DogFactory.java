package com.gzs.learn.patterdesign.create.factory.method;

import com.gzs.learn.patterdesign.create.factory.Animal;
import com.gzs.learn.patterdesign.create.factory.simple.SimpleFactory;

public class DogFactory implements AnimalFactory {

    @Override
    public Animal buildAnimal() {
        return SimpleFactory.buildAnimal(SimpleFactory.KIND_DOG);
    }

}

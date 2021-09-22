package com.gzs.learn.patterdesign.create.factory.abs;

import com.gzs.learn.patterdesign.create.factory.Action;
import com.gzs.learn.patterdesign.create.factory.Animal;
import com.gzs.learn.patterdesign.create.factory.concrete.Action4Dog;
import com.gzs.learn.patterdesign.create.factory.method.DogFactory;

public class DogConcreteFactory implements AbstractFactory {
    @Override
    public Animal buildAnimal() {
        return new DogFactory().buildAnimal();
    }

    @Override
    public Action doAction() {
        return new Action4Dog();
    }

}

package com.gzs.learn.patterdesign.create.factory.abs;

import com.gzs.learn.patterdesign.create.factory.Action;
import com.gzs.learn.patterdesign.create.factory.Animal;
import com.gzs.learn.patterdesign.create.factory.concrete.Action4Cat;
import com.gzs.learn.patterdesign.create.factory.method.CatFactory;

public class CatConcreteFactory implements AbstractFactory{

    @Override
    public Animal buildAnimal() {
        return new CatFactory().buildAnimal();
    }

    @Override
    public Action doAction() {
        return new Action4Cat();
    }

}

package com.gzs.learn.patterdesign.create.factory.abs;

import com.gzs.learn.patterdesign.create.factory.Action;
import com.gzs.learn.patterdesign.create.factory.Animal;
import com.gzs.learn.patterdesign.create.factory.concrete.Action4Ultraman;
import com.gzs.learn.patterdesign.create.factory.method.UltramanFactory;

public class UltramanConcreteFactory implements AbstractFactory {

    @Override
    public Animal buildAnimal() {
        return new UltramanFactory().buildAnimal();
    }

    @Override
    public Action doAction() {
        return new Action4Ultraman();
    }
}

package com.gzs.learn.patterdesign.create.factory.method;

import com.gzs.learn.patterdesign.create.factory.Animal;
import com.gzs.learn.patterdesign.create.factory.simple.SimpleFactory;

public class UltramanFactory implements AnimalFactory {

    @Override
    public Animal buildAnimal() {
        return SimpleFactory.buildAnimal(SimpleFactory.KIND_ULTRAMAN);
    }

}

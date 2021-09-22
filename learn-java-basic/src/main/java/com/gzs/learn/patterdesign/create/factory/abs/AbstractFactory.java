package com.gzs.learn.patterdesign.create.factory.abs;

import com.gzs.learn.patterdesign.create.factory.Action;
import com.gzs.learn.patterdesign.create.factory.Animal;

public interface AbstractFactory {
    Animal buildAnimal();

    Action doAction();
    
    // anthoer method
}

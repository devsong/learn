package com.gzs.learn.patterdesign.create.factory.abs;

import com.gzs.learn.patterdesign.create.factory.Action;
import com.gzs.learn.patterdesign.create.factory.Animal;

public class Client {

    AbstractFactory factory;

    public Client(AbstractFactory factory) {
        this.factory = factory;
    }

    public void sayHello() {
        Animal animal = factory.buildAnimal();
        Action action = factory.doAction();
        animal.sayHello();
        System.out.println(action.actionToString());
    }

    public static void main(String[] args) {
        Client client = new Client(new CatConcreteFactory());
        client.sayHello();
    }
}

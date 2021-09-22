package com.gzs.learn.patterdesign.create.factory.method;

public class Client {

    AnimalFactory factory;

    public Client(AnimalFactory factory) {
        this.factory = factory;
    }

    public void sayHello() {
        this.factory.buildAnimal().sayHello();
    }

    public static void main(String[] args) {
        Client client = new Client(new DogFactory());
        client.sayHello();
    }
}

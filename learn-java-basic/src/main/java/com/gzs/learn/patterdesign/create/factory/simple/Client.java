package com.gzs.learn.patterdesign.create.factory.simple;

public class Client {
    String kind;

    public Client(String kind) {
        this.kind = kind;
    }

    public void sayHello() {
        SimpleFactory.buildAnimal(this.kind).sayHello();
    }

    public static void main(String[] args) {
        Client client = new Client(SimpleFactory.KIND_DOG);
        client.sayHello();
    }
}

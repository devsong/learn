package com.gzs.learn.patterdesign.structure.adapter.cls;

public class Client {

    private ObjectLifeCycle objectLifeCycle;

    public Client(ObjectLifeCycle objectLifeCycle) {
        this.objectLifeCycle = objectLifeCycle;
    }

    public static void main(String[] args) {
        Client client = new Client(new ObjectDestroyListener());
        client.objectLifeCycle.destroy();
    }
}

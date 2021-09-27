package com.gzs.learn.patterdesign.behavior.strategy;

import lombok.Data;

@Data
public class Client {

    Transportation transportation;

    public Client(Transportation transportation) {
        this.transportation = transportation;
    }

    public void toTarget() {
        transportation.toTarget(new TransportationContext());
    }

    public static void main(String[] args) {
        // 初始火车
        Client client = new Client(new RailwayTransportation());
        client.toTarget();
        // 切换为汽车
        client.setTransportation(new CarTransportation());
        client.toTarget();
    }
}

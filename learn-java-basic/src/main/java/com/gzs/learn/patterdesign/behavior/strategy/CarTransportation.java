package com.gzs.learn.patterdesign.behavior.strategy;

public class CarTransportation implements Transportation{

    @Override
    public void toTarget(TransportationContext ctx) {
        System.out.println("by car");
    }

}

package com.gzs.learn.patterdesign.behavior.strategy;

public class RailwayTransportation implements Transportation {
    @Override
    public void toTarget(TransportationContext ctx) {
        System.out.println("by railway");
    }

}

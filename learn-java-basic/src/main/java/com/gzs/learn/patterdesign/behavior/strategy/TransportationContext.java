package com.gzs.learn.patterdesign.behavior.strategy;

import lombok.Data;

@Data
public class TransportationContext {
    private Point a;
    private Point b;

    @Data
    public static class Point {
        private String address;
    }
}

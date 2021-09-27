package com.gzs.learn.patterdesign.behavior.chain;

public class Client {

    Filter head;

    public void doSomething() {
        FilterContext ctx = new FilterContext();
        ctx.setOrderStatus("V");
        head.doFilter(ctx);
    }

    public static void main(String[] args) {
        Filter a = new OrderStatusAFilter();
        Filter v = new OrderStatusVFilter(a);
        Filter n = new OrderStatusNFilter(v);
        Client client = new Client();
        client.head = n;
        client.doSomething();
    }

}

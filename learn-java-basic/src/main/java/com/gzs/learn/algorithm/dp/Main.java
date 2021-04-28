package com.gzs.learn.algorithm.dp;

import com.google.common.collect.Lists;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        int[] vol = {2, 3, 4, 5, 6};
        int[] values = {3, 4, 5, 7, 6};
        int[] count = {1, 2, 3, 4, 5};
        List<BagItem> items = Lists.newArrayList();
        for (int i = 0; i < vol.length; i++) {
            BagItem item = new BagItem();
            item.setVol(vol[i]);
            item.setValue(values[i]);
            item.setCount(count[i]);

            items.add(item);
        }
        int maxVol = 6;
        // IKnapsack knapsack = new Knapsack01(items, maxVol);
        IKnapsack knapsack = new KnapsackComplete(items, maxVol);
        // IKnapsack knapsack = new KnapsackMultiple(items,maxVol);
        int maxValue = knapsack.maxValue(maxVol);
        knapsack.print();

        System.out.printf("maxVol:%d,maxValue:%d%n", maxVol, maxValue);
        List<BagItem> bestItems = knapsack.bestPrecept(maxVol);
        System.out.println("最佳容量组合:");
        for (BagItem item : bestItems) {
            System.out.println(item);
        }

        maxVol = 9;

        maxValue = knapsack.maxValue(maxVol);
        knapsack.print();

        System.out.printf("maxVol:%d,maxValue:%d%n", maxVol, maxValue);
        bestItems = knapsack.bestPrecept(maxVol);
        System.out.println("最佳容量组合:");
        for (BagItem item : bestItems) {
            System.out.println(item);
        }

        KnapsackMultipleForInteger knapsackMultipleForInteger = new KnapsackMultipleForInteger(items);
        System.out.println("maxVol:" + maxVol + " maxValue:" + knapsackMultipleForInteger.maxValue(maxVol));
        knapsackMultipleForInteger.print();

    }
}

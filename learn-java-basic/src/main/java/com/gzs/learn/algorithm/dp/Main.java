package com.gzs.learn.algorithm.dp;

import com.google.common.collect.Lists;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        int[] vol = {2, 3, 4, 5, 6};
        int[] values = {3, 4, 5, 7, 6};
        List<BagItem> items = Lists.newArrayList();
        for (int i = 0; i < vol.length; i++) {
            BagItem item = new BagItem();
            item.setVol(vol[i]);
            item.setValue(values[i]);
            item.setCount(1);

            items.add(item);
        }
        IKnapsack knapsack = new Knapsack01(items);
        // IKnapsack knapsack = new KnapsackComplete(items);

        int maxVol = 10;
        int maxValue = knapsack.maxValue(maxVol);
        knapsack.print();

        System.out.println(String.format("maxVol:%d,maxValue:%d", maxVol, maxValue));
    }
}

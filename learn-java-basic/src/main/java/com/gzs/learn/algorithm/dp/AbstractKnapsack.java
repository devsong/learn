package com.gzs.learn.algorithm.dp;

import java.util.List;

public abstract class AbstractKnapsack implements IKnapsack {

    /**
     * dp数组
     *
     * @return
     */
    protected abstract KnapsackItem[][] getDpArray();

    protected abstract List<BagItem> getItems();

    @Override
    public void print() {
        KnapsackItem[][] dp = getDpArray();
        List<BagItem> items = getItems();
        if (dp == null) {
            return;
        }
        StringBuilder sbForVols = new StringBuilder();
        StringBuilder sbForValues = new StringBuilder();
        StringBuilder sbForCounts = new StringBuilder();
        for (BagItem item : items) {
            sbForVols.append(item.getVol()).append(" ");
            sbForValues.append(item.getValue()).append(" ");
            sbForCounts.append(item.getCount()).append(" ");
        }
        System.out.println("容量列表:");
        System.out.println(sbForVols);
        System.out.println("价值列表:");
        System.out.println(sbForValues);
        System.out.println("数量列表:");
        System.out.println(sbForCounts);

        System.out.println("动态规划数组列表:");
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 0 && j == 0) {
                    System.out.printf("%d ", i);
                } else if (i == 0) {
                    System.out.printf("% 5d ", j);
                } else if (j == 0) {
                    System.out.printf("%d ", i);
                } else {
                    System.out.printf("% 5d ", dp[i][j].getTotal());
                }
            }
            System.out.println();
        }
    }
}

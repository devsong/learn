package com.gzs.learn.algorithm.dp;

import java.util.List;

public abstract class AbstractKnapsack implements IKnapsack {

    /**
     * dp数组
     *
     * @return
     */
    protected abstract DpArrayItem[][] getDpArray();

    protected abstract List<BagItem> getItems();

    @Override
    public void print() {
        DpArrayItem[][] dp = getDpArray();
        List<BagItem> items = getItems();
        if (dp == null) {
            return;
        }
        StringBuilder sbForVols = new StringBuilder();
        StringBuilder sbForValues = new StringBuilder();
        StringBuilder sbForCounts = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            BagItem item = items.get(i);
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
                    System.out.print(String.format("%d ", i));
                } else if (i == 0) {
                    System.out.print(String.format("% 5d ", j));
                } else if (j == 0) {
                    System.out.print(String.format("%d ", i));
                } else {
                    System.out.print(String.format("% 5d ", dp[i][j].getTotal()));
                }
            }
            System.out.println();
        }
    }
}

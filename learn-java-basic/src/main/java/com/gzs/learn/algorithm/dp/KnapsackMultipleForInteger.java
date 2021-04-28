package com.gzs.learn.algorithm.dp;

import java.util.List;

/**
 * 整形数组类型的多重背包问题,便于理解算法
 *
 * @author guanzhisong
 */
public class KnapsackMultipleForInteger {
    private final List<BagItem> items;
    private int[][] dp = null;

    public KnapsackMultipleForInteger(List<BagItem> items) {
        if (items == null || items.isEmpty()) {
            throw new NullPointerException("items must not empty");
        }
        this.items = items;
    }

    /**
     * 初始化多重背包动态规划数组
     *
     * @param maxVol
     */
    private void adjust(int maxVol) {
        int len = items.size();
        // 初始化dp数组
        this.dp = new int[len + 1][maxVol + 1];
        for (int i = 1; i < len + 1; i++) {
            BagItem item = items.get(i - 1);
            for (int j = 1; j < maxVol + 1; j++) {
                for (int k = 1; k <= item.getCount(); k++) {
                    if (item.getVol() * k > j) {
                        if (dp[i][j] < dp[i - 1][j]) {
                            dp[i][j] = dp[i - 1][j];
                        }
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - item.getVol() * k] + item.getValue() * k);
                    }
                }
            }
        }
    }

    public Integer maxValue(int maxVol) {
        if (this.dp == null || this.dp[0].length < maxVol + 1) {
            adjust(maxVol);
        }
        return this.dp[dp.length - 1][maxVol];
    }

    public void print() {
        if (this.dp == null) {
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
        System.out.println(sbForVols.toString());
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
                    System.out.printf("% 5d ", dp[i][j]);
                }
            }
            System.out.println();
        }
    }
}

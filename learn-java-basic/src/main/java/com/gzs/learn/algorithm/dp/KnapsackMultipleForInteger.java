package com.gzs.learn.algorithm.dp;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 整形数组类型的多重背包问题,便于理解算法
 *
 * @author guanzhisong
 */
public class KnapsackMultipleForInteger {
    private final List<BagItem> items;
    private final Map<Integer, BagItem> itemMap;
    private int[][] dp = null;

    public KnapsackMultipleForInteger(List<BagItem> items) {
        if (items == null || items.isEmpty()) {
            throw new NullPointerException("items must not empty");
        }
        this.items = items;
        this.itemMap = items.stream().collect(Collectors.toMap(BagItem::getVol, Function.identity()));
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
                for (int k = 1; k < item.getCount(); k++) {
                    if (item.getVol() * k > j) {
                        dp[i][j] = dp[i - 1][j];
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
        for (int i = 0; i < items.size(); i++) {
            BagItem item = items.get(i);
            sbForVols.append(item.getVol()).append(" ");
            sbForValues.append(item.getValue()).append(" ");
        }
        System.out.println("容量列表:");
        System.out.println(sbForVols.toString());
        System.out.println("价值列表:");
        System.out.println(sbForValues);


        System.out.println("动态规划数组列表:");
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
                    System.out.print(String.format("% 5d ", dp[i][j]));
                }
            }
            System.out.println();
        }
    }
}

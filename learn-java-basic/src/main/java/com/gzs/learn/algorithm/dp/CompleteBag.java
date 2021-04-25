package com.gzs.learn.algorithm.dp;

import org.junit.Test;

/**
 * 完全背包问题
 * 
 * @author guanzhisong
 *
 */
public class CompleteBag {

    // 对象容量数组
    static int[] vols = {2, 3, 4, 5};
    // 对象价值数组
    static int[] values = {6, 3, 8, 10};

    public static int[][] completeBag(int[] vols, int[] values, int vol) {
        // 物品总件数,vols数组长度与weights数组长度是一致的,对应每个物品的容量与价值
        for (int i = 0; i < values.length; i++) {
            values[i] = values[i] / vols[i];
        }
        int n = vols.length;
        int[][] dp = new int[n + 1][vol + 1];

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < vol + 1; j++) {
                if (vols[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - vols[i - 1]] + values[i - 1]);
                }
            }
        }
        return dp;
    }

    public void printDp(int[] vols, int[] values, int[][] dp, int vol) {
        System.out.println("容量列表:");
        for (int i = 0; i < vols.length; i++) {
            System.out.print(vols[i] + " ");
        }

        System.out.println("");
        System.out.println("价值列表:");
        for (int i = 0; i < values.length; i++) {
            System.out.print(values[i] + " ");
        }

        // 逆推找出装入背包的所有商品的编号
        int maxVol = vol;
        String volStr = "";
        for (int i = vols.length; i > 0; i--) {
            // 若果dp[i][j]>dp[i-1][j],这说明第i件物品是放入背包的
            if (dp[i][maxVol] > dp[i - 1][maxVol]) {
                volStr = vols[i - 1] + " " + volStr;
                maxVol = maxVol - vols[i - 1];
            }
            if (maxVol == 0) {
                break;
            }
        }
        System.out.println();
        System.out.println("匹配容量列表:" + volStr);

        for (int i = 0; i < vols.length + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < vol + 1; j++) {
            dp[0][j] = j;
        }

        System.out.println("");
        System.out.println("动态规划数组列表:");
        for (int i = 0; i < vols.length + 1; i++) {
            for (int j = 0; j < vol + 1; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("最大价值:" + dp[vols.length][vol]);
    }

    @Test
    public void testPack() {
        int maxVol = 6;
        int[][] dp = completeBag(vols, values, maxVol);
        printDp(vols, values, dp, maxVol);
    }
}

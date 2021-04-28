package com.gzs.learn.algorithm.dp;

/**
 * N 人过河问题
 */
public class River {

    int[] cost = {1, 2, 5, 10};
    int[] dp = new int[cost.length];

    public int getMinTotalTime() {
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < cost.length; i++) {
            // 状态转移方程
            dp[i] = Math.min(dp[i - 1] + cost[0] + cost[i], dp[i - 2] + cost[i] + cost[0] + 2 * cost[1]);
        }
        return dp[cost.length - 1];
    }

    public static void main(String[] args) {
        River river = new River();
        System.out.println(river.getMinTotalTime());
        for(int r: river.dp){
            System.out.print(r+" ");
        }
    }
}

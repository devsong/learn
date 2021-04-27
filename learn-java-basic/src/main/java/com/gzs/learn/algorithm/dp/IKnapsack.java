package com.gzs.learn.algorithm.dp;

import java.util.List;

public interface IKnapsack {

    /**
     * 计算得出最优组合
     * 
     * @param vol
     * @return
     */
    public List<BagItem> bestPrecept(int vol);

    /**
     * 计算最优组合的价值
     * 
     * @param vol
     * @return
     */
    public Integer maxValue(int vol);

    /**
     * 打印当前动态规划数据内容
     */
    public void print();
}

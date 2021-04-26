package com.gzs.learn.algorithm.dp;

import java.util.List;

public interface IKnapsack {

    /**
     * 计算得出最优组合
     * 
     * @param maxVol
     * @return
     */
    public List<BagItem> bestPrecept(int maxVol);

    /**
     * 计算最优组合的价值
     * 
     * @param maxVol
     * @return
     */
    public Integer maxValue(int maxVol);

    /**
     * 打印当前动态规划数据内容
     */
    public void print();
}

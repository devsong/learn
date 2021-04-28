package com.gzs.learn.algorithm.dp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 背包问题dp数组条目
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KnapsackItem {
    // 当前节点
    private BagItem bagItem;
    // 前置节点
    private KnapsackItem prev;
    // 当前dp值
    private int total;
}

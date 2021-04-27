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
public class DpArrayItem {
    private BagItem bagItem;
    private DpArrayItem prev;
}

package com.gzs.learn.algorithm.dp;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import com.google.common.collect.Lists;

/**
 * 0-1背包问题
 * 
 * @author guanzhisong
 */
public class Knapsack01 implements IKnapsack {
    private final List<BagItem> items;
    private final Map<Integer, BagItem> itemMap;
    private int[][] dp = null;

    public Knapsack01(List<BagItem> items) {
        if (items == null || items.isEmpty()) {
            throw new NullPointerException("items must not empty");
        }
        this.items = items;
        this.itemMap = items.stream().collect(Collectors.toMap(BagItem::getVol, Function.identity()));
    }

    /**
     * 初始化0-1背包动态规划数组
     * 
     * @param maxVol
     */
    private void init(int maxVol) {
        int len = items.size();
        // 初始化dp数组
        this.dp = new int[len + 1][maxVol + 1];
        for (int i = 1; i < len + 1; i++) {
            BagItem item = items.get(i);
            for (int j = 1; j < maxVol + 1; j++) {
                if (item.getVol() > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - item.getVol()] + item.getValue());
                }
            }
        }
    }

    @Override
    public List<BagItem> bestPrecept(int maxVol) {
        // 已经构建过dp数组的无需再次构建
        if (this.dp == null || this.dp[0].length < maxVol + 1) {
            init(maxVol);
        }

        int len = items.size();
        // 逆推找出装入背包的所有商品的编号
        int vol = maxVol;
        List<Integer> volResults = Lists.newArrayList();
        for (int i = len; i > 0; i--) {
            BagItem item = items.get(i - 1);
            // 若果dp[i][j]>dp[i-1][j],这说明第i件物品是放入背包的
            if (dp[i][vol] > dp[i - 1][vol]) {
                volResults.add(item.getVol());
                vol -= item.getVol();
            }
            if (vol == 0) {
                break;
            }
        }
        List<BagItem> bestPrecepts = Lists.newArrayList();
        for (Integer v : volResults) {
            bestPrecepts.add(itemMap.get(v));
        }
        return bestPrecepts;
    }

    @Override
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
        final int[][] copyDp = new int[this.dp.length][this.dp[0].length];
        for (int i = 0; i < this.dp.length; i++) {
            System.arraycopy(this.dp[i], 0, copyDp[i], 0, this.dp[i].length);
        }

        for (int i = 0; i < copyDp.length; i++) {
            copyDp[i][0] = i;
        }
        for (int j = 0; j < copyDp[0].length; j++) {
            copyDp[0][j] = j;
        }

        System.out.println("");
        System.out.println("动态规划数组列表:");
        for (int i = 0; i < copyDp.length; i++) {
            for (int j = 0; j < copyDp[0].length; j++) {
                System.out.print(copyDp[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public Integer maxValue(int maxVol) {
        List<BagItem> items = bestPrecept(maxVol);
        if (CollectionUtils.isEmpty(items)) {
            return 0;
        }
        return items.stream().mapToInt(i -> i.getValue()).sum();
    }
}

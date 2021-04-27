package com.gzs.learn.algorithm.dp;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 0-1背包问题
 *
 * @author guanzhisong
 */
public class KnapsackComplete implements IKnapsack {
    private final List<BagItem> items;
    private final Map<Integer, BagItem> itemMap;
    private int[][] dp = null;

    public KnapsackComplete(List<BagItem> items) {
        if (items == null || items.isEmpty()) {
            throw new NullPointerException("items must not empty");
        }
        this.items = optimizeItems(items);
        this.itemMap = items.stream().collect(Collectors.toMap(BagItem::getVol, Function.identity()));
    }

    private List<BagItem> optimizeItems(List<BagItem> items) {
        List<BagItem> copyItems = Lists.newArrayList();
        copyItems.addAll(items);

        Collections.sort(items);
        Collections.sort(copyItems);

        Iterator<BagItem> bagItemIt = items.iterator();
        while (bagItemIt.hasNext()) {
            BagItem item = bagItemIt.next();
            for (BagItem copy : copyItems) {
                // 容量大于条目容量,价值小于条目价值
                if (item.getVol() > copy.getVol() && item.getValue() < copy.getValue()) {
                    // 移除无价值的条目
                    bagItemIt.remove();
                }
            }
        }
        return items;
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
            BagItem item = items.get(i - 1);
            for (int j = 1; j < maxVol + 1; j++) {
                int maxK = (int) Math.ceil(((double) j) / item.getVol()) + 1;
                for (int k = 1; k < maxK; k++) {
                    if (item.getVol() * k > j) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - item.getVol() * k] + item.getValue() * k);
                    }
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
        if (this.dp == null || this.dp[0].length < maxVol + 1) {
            init(maxVol);
        }
        return this.dp[dp.length - 1][maxVol];
    }
}

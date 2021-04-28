package com.gzs.learn.algorithm.dp;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 0-1背包问题
 *
 * @author guanzhisong
 */
public class KnapsackMultiple extends AbstractKnapsack {
    private final List<BagItem> items;
    private KnapsackItem[][] dp = null;
    private int lastVol;
    // 多重背包问题最大容量上限
    private final int maxVol;

    public KnapsackMultiple(List<BagItem> items, int vol) {
        if (items == null || items.isEmpty()) {
            throw new NullPointerException("items must not empty");
        }
        this.items = items;
        this.maxVol = items.stream().mapToInt(i -> i.getVol() * i.getCount()).sum();
        if (vol > maxVol) {
            vol = maxVol;
        }
        this.lastVol = vol;
        init(vol);
    }

    /**
     * 初始化0-1背包动态规划数组
     *
     * @param vol
     */
    protected void init(int vol) {
        int len = items.size();
        // 初始化dp数组
        this.dp = new KnapsackItem[len + 1][vol + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = new KnapsackItem(null, null, 0);
            }
        }
        buildDp(dp, 1, vol);
    }


    /**
     * 调整背包动态规划数组(扩容)
     *
     * @param vol
     */
    protected void adjust(int vol) {
        if (vol > maxVol) {
            vol = maxVol;
        }
        if (vol <= lastVol) {
            // 当前dp数组可用,无需扩容dp数组
            return;
        }
        int len = items.size();
        // 扩容数组
        KnapsackItem[][] newDp = new KnapsackItem[len + 1][vol + 1];

        for (int i = 0; i < newDp.length; i++) {
            for (int j = 0; j < newDp[0].length; j++) {
                if (j > this.lastVol) {
                    // 扩容数组对象,初始化值
                    newDp[i][j] = new KnapsackItem(null, null, 0);
                } else {
                    // 复用旧数组的对象
                    newDp[i][j] = dp[i][j];
                }
            }
        }
        this.dp = newDp;
        buildDp(dp, this.lastVol, vol);
        this.lastVol = vol;
    }

    protected void buildDp(KnapsackItem[][] dp, int startVol, int vol) {
        int len = dp.length;
        for (int i = 1; i < len; i++) {
            BagItem item = items.get(i - 1);
            for (int j = startVol; j < vol + 1; j++) {
                KnapsackItem prev = dp[i - 1][j];
                KnapsackItem currentItem = dp[i][j];
                for (int k = 1; k <= item.getCount(); k++) {
                    if (item.getVol() * k > j) {
                        if(prev.getTotal()>currentItem.getTotal()) {
                            currentItem.setTotal(prev.getTotal());
                            currentItem.setPrev(prev);
                        }
                    } else {
                        KnapsackItem specifyItem = dp[i - 1][j - item.getVol() * k];
                        int prevTotal = prev.getTotal();
                        int newTotal = specifyItem.getTotal() + item.getValue() * k;
                        if (prevTotal > newTotal) {
                            currentItem.setTotal(prevTotal);
                            currentItem.setPrev(prev);
                            currentItem.setBagItem(null);
                        } else {
                            BagItem bagItem = new BagItem();
                            bagItem.setValue(item.getValue());
                            bagItem.setVol(item.getVol());
                            bagItem.setCount(k);

                            currentItem.setTotal(newTotal);
                            currentItem.setPrev(specifyItem);
                            currentItem.setBagItem(bagItem);
                        }
                    }
                }
            }
        }
    }

    @Override
    public List<BagItem> bestPrecept(int vol) {
        if (vol > maxVol) {
            vol = maxVol;
        }
        if (this.lastVol < vol) {
            // 扩容dp数组
            adjust(vol);
        }
        List<BagItem> bestPrecepts = Lists.newArrayList();
        KnapsackItem item = dp[dp.length - 1][vol];
        while (item.getPrev() != null) {
            if (item.getBagItem() == null) {
                item = item.getPrev();
                continue;
            }
            if (item.getBagItem().getVol() > 0) {
                bestPrecepts.add(item.getBagItem());
            }
            item = item.getPrev();
        }
        return bestPrecepts;
    }

    @Override
    protected KnapsackItem[][] getDpArray() {
        return this.dp;
    }

    @Override
    protected List<BagItem> getItems() {
        return this.items;
    }

    @Override
    public Integer maxValue(int vol) {
        if (vol > maxVol) {
            vol = maxVol;
        }
        if (this.lastVol < vol) {
            adjust(vol);
        }
        return dp[dp.length - 1][vol].getTotal();
    }
}

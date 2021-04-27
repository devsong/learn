package com.gzs.learn.algorithm.dp;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 完全背包问题
 *
 * @author guanzhisong
 */
public class KnapsackComplete extends AbstractKnapsack {
    private final List<BagItem> items;
    private final Map<Integer, BagItem> itemMap;
    private DpArrayItem[][] dp = null;
    // 记录当前dp数组的最大容量,默认与dp数组的dp[0].length-1相等
    private int lastVol;
    private boolean isInit = false;

    public KnapsackComplete(List<BagItem> items, int vol) {
        if (items == null || items.isEmpty()) {
            throw new NullPointerException("items must not empty");
        }
        this.items = optimizeItems(items);
        this.lastVol = vol;
        this.itemMap = items.stream().collect(Collectors.toMap(BagItem::getVol, Function.identity()));
        this.isInit = init(vol);
    }

    /**
     * 优化背包数组条目,移除容量大、价值小的条目
     *
     * @param items
     * @return
     */
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
     * 初始化完全背包动态规划数组
     *
     * @param vol
     */
    protected boolean init(int vol) {
        int len = items.size();
        // 初始化dp数组
        this.dp = new DpArrayItem[len + 1][vol + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = new DpArrayItem(null, null, 0);
            }
        }
        buildDp(dp, 1, vol);
        return true;
    }

    /**
     * 初始化背包动态规划数组
     *
     * @param vol
     */
    protected void adjust(int vol) {
        // 无需调整
        if (vol < this.lastVol) {
            return;
        }

        int len = items.size();
        // 初始化dp数组
        DpArrayItem[][] newDp = new DpArrayItem[len + 1][vol + 1];
        for (int i = 0; i < newDp.length; i++) {
            for (int j = 0; j < newDp[0].length; j++) {
                if (j > this.lastVol) {
                    // 扩容数组对象,初始化值
                    newDp[i][j] = new DpArrayItem(null, null, 0);
                } else {
                    // 复用旧数组的对象
                    newDp[i][j] = dp[i][j];
                }
            }
        }
        this.dp = newDp;
        buildDp(dp, lastVol, vol);
        this.lastVol = vol;
    }

    protected void buildDp(DpArrayItem[][] dp, int startVol, int vol) {
        int len = dp.length;

        for (int i = 1; i < len; i++) {
            BagItem item = items.get(i - 1);
            // 当前元素最大的条目数量
            int maxK = (int) Math.ceil((double) vol / item.getVol());
            for (int j = startVol; j < vol + 1; j++) {
                DpArrayItem prev = dp[i - 1][j];
                DpArrayItem currentItem = dp[i][j];
                for (int k = 1; k <= maxK; k++) {
                    if (item.getVol() * k > j) {
                        if(prev.getTotal()>currentItem.getTotal()) {
                            currentItem.setTotal(prev.getTotal());
                            currentItem.setPrev(prev);
                            currentItem.setBagItem(null);
                        }
                    } else {
                        DpArrayItem specifyItem = dp[i - 1][j - item.getVol() * k];
                        int prevTotal = prev.getTotal();
                        int newTotal = specifyItem.getTotal() + item.getValue() * k;
                        if (prevTotal > newTotal) {
                            currentItem.setTotal(prev.getTotal());
                            currentItem.setPrev(prev);
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
        // 当前dp容量数组长度小于给定容量,扩容数组
        if (this.lastVol < vol) {
            adjust(vol);
        }
        List<BagItem> bestPrecepts = Lists.newArrayList();
        DpArrayItem item = dp[dp.length - 1][vol];
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
    public Integer maxValue(int vol) {
        if (this.lastVol < vol) {
            adjust(vol);
        }
        return dp[dp.length - 1][vol].getTotal();
    }

    @Override
    protected DpArrayItem[][] getDpArray() {
        return this.dp;
    }

    @Override
    protected List<BagItem> getItems() {
        return this.items;
    }
}

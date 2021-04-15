package com.gzs.learn.algorithm.dp;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * 斐波那契数列,最简单的动态规划，状态转移方程 f(n)=f(n-1)+f(n-2)
 * 
 * @author guanzhisong
 *
 */
public class FibonacciDp {
    Map<Long, Long> result = new TreeMap<>();

    public Long calc(Long n) {
        Long r = 0L;
        if (n < 2) {
            r = n;
        } else {
            Long pprev = result.get(n - 2) == null ? calc(n - 2) : result.get(n - 2);
            Long prev = result.get(n - 1) == null ? calc(n - 1) : result.get(n - 1);
            r = prev + pprev;
        }
        result.put(n, r);
        return r;
    }

    public static void main(String[] args) {
        FibonacciDp fibDp = new FibonacciDp();
        fibDp.calc(10L);
        for (Entry<Long, Long> entry : fibDp.result.entrySet()) {
            System.out.print(entry.getValue() + " ");
        }
    }
}

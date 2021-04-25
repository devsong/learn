package com.gzs.learn.algorithm.dp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public List<Long> calcByDp(Long n) {
        List<Long> result = new ArrayList<Long>(n.intValue());
        result.add(0, 1L);
        result.add(1, 1L);
        for (int i = 2; i <= n; i++) {
            result.add(i, result.get(i - 1) + result.get(i - 2));
        }
        return result;
    }

    public static void main(String[] args) {
        FibonacciDp fibDp = new FibonacciDp();
        List<Long> results = fibDp.calcByDp(10L);
        for (long r : results) {
            System.out.print(r + " ");
        }
    }
}

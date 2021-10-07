package com.gzs.learn.leetcode;

import org.junit.Test;

public class XorAlg {
    public int[] lostArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13};

    public int[] repeatArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 10, 11, 12, 13};

    @Test
    public void testFindLostNumber() {
        int maxNum = lostArr.length + 1;
        int result = lostArr[0];
        for (int i = 1; i < lostArr.length; i++) {
            result = result ^ lostArr[i];
        }
        for (int i = 1; i <= maxNum; i++) {
            result ^= i;
        }
        System.out.println(result);
    }

    @Test
    public void testFindRepeat() {
        int result = 0;
        int len = repeatArr.length;
        for (int i = 0; i < len - 1; i++) {
            int elem = repeatArr[i];
            result ^= (elem ^ (i + 1));
        }
        result = result ^ repeatArr[len - 1];
        System.out.println(result);
    }

}

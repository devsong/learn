package com.gzs.learn.algorithm.search;

import org.junit.Test;
import com.alibaba.fastjson.JSON;

/**
 * 有序数组中位数
 * 
 * @author guanzhisong
 */
public class MidNumberSearch {
    public int[] findMidNum(int[] a, int[] b) {
        int m = a.length;
        int n = b.length;
        // ensure m is shorter array
        if (m > n) {
            int[] temp = a;
            a = b;
            b = temp;

            m = a.length;
            n = b.length;
        }

        int min = 0, max = m, halfLen = (m + n + 1) / 2;
        while (min <= max) {
            int i = (min + max) / 2;
            int j = halfLen - i;
            if (i < max && a[i] < b[j - 1]) {
                min = min + 1;
            } else if (i > min && a[i - 1] > b[j]) {
                max = max - 1;
            } else {
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = b[j - 1];
                } else if (j == 0) {
                    maxLeft = a[i - 1];
                } else {
                    maxLeft = Math.max(a[i - 1], b[j - 1]);
                }

                if ((m + n) % 2 == 1) {
                    return new int[] {maxLeft};
                }

                int minRight = 0;
                if (i == m) {
                    minRight = b[j];
                } else if (j == n) {
                    minRight = a[i];
                } else {
                    minRight = Math.min(b[j], a[i]);
                }

                return new int[] {maxLeft, minRight};
            }
        }
        return null;
    }

    @Test
    public void testFind() {
        int[] num1 = {1, 3, 5, 7, 19};
        int[] num2 = {0, 2, 3, 4, 6, 8};
        System.out.println(JSON.toJSONString(findMidNum(num1, num2)));
    }
}

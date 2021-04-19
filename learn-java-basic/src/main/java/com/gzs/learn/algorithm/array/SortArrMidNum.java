package com.gzs.learn.algorithm.array;

import org.junit.Test;

public class SortArrMidNum {

    public int findMidNum(int[] a, int[] b) {
        int m = a.length;
        int n = b.length;
        // ensure m is shorter array
        if (m > n) {
            int[] temp = a;
            a = b;
            b = temp;
        }
        m = a.length;
        n = b.length;
        int min = 0, max = m, halfLen = (m + n + 1) / 2, mid = 0;
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
                    mid = maxLeft;
                    return mid;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = b[j];
                } else if (j == n) {
                    minRight = a[i];
                } else {
                    minRight = Math.min(b[j], a[i]);
                }

                mid = (maxLeft + minRight) / 2;
                return mid;
            }
        }
        return 0;
    }

    @Test
    public void testFind() {
        int[] num1 = {1, 3, 5, 7, 19};
        int[] num2 = {0, 2, 3, 4, 6, 8};
        System.out.println(findMidNum(num1, num2));
    }
}

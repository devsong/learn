package com.gzs.learn.algorithm.datastructure;

import org.junit.Test;

public class DynamicPrograming {
    private static int MAX_LEN = 10000;
    private static long[] nmap = new long[MAX_LEN];

    @Test
    public void testFabraic() {
        System.out.println(fabraic(100));
    }

    public static long fabraic(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must greater than 0");
        }
        if (nmap[n] > 0) {
            return nmap[n];
        }
        if (n == 0 || n == 1) {
            return n;
        }
        long result = fabraic(n - 1) + fabraic(n - 2);
        nmap[n] = result;
        return result;
    }
}

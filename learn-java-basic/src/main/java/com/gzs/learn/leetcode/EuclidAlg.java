package com.gzs.learn.leetcode;

import org.junit.Test;
import com.alibaba.fastjson.JSON;

public class EuclidAlg {

    public static int gcd(int a, int b) {
        if (b == 0)
            return a;
        else {
            int temp = b;
            b = a % b;
            a = temp;
            return gcd(a, b);
        }
    }

    /**
     * 方程ax+by = c 的二元一次方程整数解法
     * 
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static void euclidSln(int a, int b, int c, int[] result) {
        int gcd = gcd(a, b);
        if (c % gcd != 0) {
            return;
        }
        if (b == 0) {
            result[0] = 1;
            result[1] = 0;
        } else {
            euclidSln(b, a % b, c, result);
            int tmp = result[0];
            result[0] = result[1];
            result[1] = tmp - (a / b) * result[1];
        }
    }


    @Test
    public void test() {
        System.out.println(JSON.toJSONString(gcd(7, 70)));
        int[] result = new int[2];
        euclidSln(17, 3120, 1, result);
        System.out.println(JSON.toJSONString(result));
    }
}

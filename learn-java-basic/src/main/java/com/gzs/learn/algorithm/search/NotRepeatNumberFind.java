package com.gzs.learn.algorithm.search;

import java.util.List;
import org.junit.Test;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

public class NotRepeatNumberFind {

    public int[] a = {1, 2, 3, 4, 5, 4, 3, 2, 1};

    public int[] b = {1, 2, 3, 4, 5, 6, 4, 3, 2, 1};

    public int[] c = {1, 1, 1, 6, 6, 6, 7};

    public int findNoRepeatNumber() {
        int result = a[0];
        for (int i = 1; i < a.length; i++) {
            result = result ^ a[i];
        }
        return result;
    }

    public int[] findNoRepeatNumber2() {
        int xorShift = 0;
        for (int i = 0; i < b.length; i++) {
            xorShift = xorShift ^ b[i];
        }

        int index = 0;
        while (xorShift > 0) {
            if ((xorShift & 1) == 1) {
                break;
            } else {
                xorShift = xorShift >> 1;
                index++;
            }
        }
        List<Integer> part1 = Lists.newArrayList();
        List<Integer> part2 = Lists.newArrayList();

        for (int i = 0; i < b.length; i++) {
            int e = b[i];
            // split array
            if (((e >> index) & 1) == 1) {
                part1.add(e);
            } else {
                part2.add(e);
            }
        }
        System.out.println("part1:" + JSON.toJSONString(part1));
        System.out.println("part2:" + JSON.toJSONString(part2));
        int first = 0, second = 0;
        for (int i : part1) {
            first ^= i;
        }

        for (int i : part2) {
            second ^= i;
        }
        return new int[] {first, second};
    }


    public void findNoRepeatNumber3() {

    }


    int singleNumberOrigin(int[] a) {
        int low = 0, high = 0;
        for (int i = 0; i < a.length; i++) {
            int e = a[i];
            int temp_low = (low ^ e) & ~high;
            high = (high & ~low & ~e) | (~high & low & e);
            low = temp_low;
        }
        return low;
    }

    public int singleNumber(int[] a) {
        int low = 0, high = 0;
        for (int i = 0; i < a.length; i++) {
            int e = a[i];
            low = (low ^ e) & ~high;
            high = (high ^ e) & ~low;
        }
        return low;
    }


    @Test
    public void testFind() {
        // System.out.println(findNoRepeatNumber());
        System.out.println(JSON.toJSONString(findNoRepeatNumber2()));
        System.out.println(singleNumber(c));
    }
}

package com.gzs.learn.algorithm.search;

import java.util.Set;
import org.junit.Test;
import com.google.common.collect.Sets;

public class SlidingWindow {

    Integer a[] = {-12, 3, 13, -4, 11, 11, 15, 12, -20, -19, 15, -2, 0, 18, -6, -3, -16, -19, -16, -13};
    Character c[] = {'k', 'N', 'u', 'V', 'p', 'G', 'B', 'G', 'r', 'R', 'K', 'Q', 'd', 'd', 'C', 'T', 'J', 'L', 'k'};

    @Test
    public void init() {
        // for (int i = 0; i < a.length; i++) {
        // a[i] = ThreadLocalRandom.current().nextInt(-20, 20);
        // }
        // print(a);
        // for (int i = 0; i < c.length; i++) {
        // c[i] = (char) ThreadLocalRandom.current().nextInt('A','z');
        // }
        //
        // print(c);
    }

    @Test
    public void seqMaxSum() {
        int left = 0, right = 0;
        int result = a[0];
        for (int i = 1; i < a.length; i++) {
            int newResult = result + a[i];
            if (newResult > result) {
                right++;
                result = newResult;
            } else if (newResult > 0) {

            }
        }

        System.out.println("max:" + result + ",first:" + left + ",end:" + right);
    }

    @Test
    public void maxSeq() {
        Set<Character> maxSeq = Sets.newHashSet();
        Set<Character> currentSeq = Sets.newHashSet();
        int first = 0, end = 0;
        for (int i = 0; i < c.length; i++) {
            char cur = c[i];
            if (currentSeq.contains(cur)) {
                currentSeq.clear();
            } else {
                first = i;
                currentSeq.add(cur);
                if (currentSeq.size() > maxSeq.size()) {
                    maxSeq.clear();
                    maxSeq.addAll(currentSeq);
                    end = i;
                }
            }
        }
    }

    public void print(Object[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i].toString() + " ");
        }
        System.out.println();
    }
}

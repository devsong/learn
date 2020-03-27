package com.gzs.learn.algorithm.search;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Sets;

public class SlidingWindow {

    Integer a[] = new Integer[20];
    Character c[] = new Character[20];

    @Before
    public void init() {
        for (int i = 0; i < a.length; i++) {
            a[i] = ThreadLocalRandom.current().nextInt(-20, 20);
        }
        print(a);
        for (int i = 0; i < c.length; i++) {
            c[i] = (char) i;
        }
        print(c);
    }

    @Test
    public void seqMaxSum() {
        int maxSum = 0;
        int currentSum = 0;
        int first = 0, end = 0;
        for (int i = 0; i < a.length; i++) {
            currentSum += a[i];
            if (currentSum > maxSum) {
                maxSum = currentSum;
                end = i;
            } else if (currentSum < 0) {
                first = i + 1;
                currentSum = 0;
            }
        }
        System.out.println("max:" + maxSum + ",first:" + first + ",end:" + end);
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

package com.gzs.learn.algorithm.string;

import java.util.List;

/**
 * kmp字符串匹配
 * @author guanzhisong
 *
 */
public class KMPMatcher implements StringMatcher {

    @Override
    public List<Integer> match(String str, String pattern) {
        return null;
    }

    private int[] preProcess(char[] arr) {
        return new int[100];
    }
}

package com.gzs.learn.algorithm.string;

import java.util.List;
import com.alibaba.fastjson.JSON;

public class Main {
    public static void main(String[] args) {
        String originStr = "ababdababcdfddfabcdfababdssd";
        String pattern = "bdssd";
        // StringMatcher matcher = new BruteForceMatcher();
        // StringMatcher matcher = new KMPMatcher();
        StringMatcher matcher = new SundayMatcher();
        List<Integer> indexes = matcher.match(originStr, pattern);
        System.out.println(JSON.toJSONString(indexes));
        for (int i = 0; i < indexes.size(); i++) {
            int start = indexes.get(i);
            System.out.println(originStr.substring(start, start + pattern.length()));
        }
    }
}

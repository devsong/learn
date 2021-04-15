package com.gzs.learn.algorithm.string;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

/**
 * 暴力破解算法
 * @author guanzhisong
 */
public class BruteForceMatcher implements StringMatcher {
    public static void main(String[] args) {
        String originStr = "ababdababcdfddfabcdfababdssd";
        String pattern = "ababd";
        StringMatcher matcher = new BruteForceMatcher();
        List<Integer> indexes = matcher.match(originStr, pattern);
        System.out.println(JSON.toJSONString(indexes));
        for (int i = 0; i < indexes.size(); i++) {
            int start = indexes.get(i);
            System.out.println(originStr.substring(start, start + pattern.length()));
        }
    }

    @Override
    public List<Integer> match(String str, String pattern) {
        List<Integer> list = Lists.newArrayList();
        char[] originArr = str.toCharArray();
        char[] patternArr = pattern.toCharArray();
        int n = originArr.length;
        int m = patternArr.length;
        for (int i = 0; i < n - m + 1; i++) {
            int originStart = i;
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                char originChar = originArr[originStart + j];
                char patternChar = patternArr[j];
                if (originChar != patternChar) {
                    // not match
                    flag = false;
                    break;
                }
            }
            if (flag) {
                list.add(originStart);
            }
        }
        return list;
    }
}

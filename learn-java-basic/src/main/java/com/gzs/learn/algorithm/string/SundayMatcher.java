package com.gzs.learn.algorithm.string;

import java.util.List;
import com.google.common.collect.Lists;

public class SundayMatcher implements StringMatcher {
    static final int ASCII_SIZE = 126;

    @Override
    public List<Integer> match(String str, String pattern) {
        return sunday(str.toCharArray(), pattern.toCharArray());
    }

    protected List<Integer> sunday(char[] total, char[] part) {
        List<Integer> result = Lists.newArrayList();
        int tSize = total.length;
        int pSize = part.length;
        int[] move = new int[ASCII_SIZE];
        // 主串参与匹配最末位字符移动到该位需要移动的位数
        for (int i = 0; i < ASCII_SIZE; i++) {
            move[i] = pSize + 1;
        }

        for (int i = 0; i < pSize; i++) {
            move[part[i]] = pSize - i;
        }

        int s = 0;// 模式串头部在字符串位置

        int j;// 模式串已经匹配了的长度

        while (s <= tSize - pSize) {// 到达末尾之前
            j = 0;
            while (total[s + j] == part[j]) {
                j++;
                if (j >= pSize) {
                    // result.add(s);
                    // s = 0;
                    // break;
                    result.add(s);
                    return result;
                }
            }
            s += move[total[s + pSize]];
        }
        return result;
    }
}

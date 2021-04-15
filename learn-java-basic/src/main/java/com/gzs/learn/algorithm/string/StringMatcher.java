package com.gzs.learn.algorithm.string;

import java.util.List;

/**
 * 字符串匹配算法
 * 
 * @author guanzhisong
 *
 */
public interface StringMatcher {
    /**
     * 
     * @param str
     * @param pattern
     * @return
     */
    List<Integer> match(String str, String pattern);
}

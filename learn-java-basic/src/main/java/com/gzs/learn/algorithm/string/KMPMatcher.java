package com.gzs.learn.algorithm.string;

import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

/**
 * kmp字符串匹配
 * 
 * @author guanzhisong
 *
 */
public class KMPMatcher implements StringMatcher {
    /**
     * KMP模式匹配
     * 
     * @author Tianma
     *
     */
    static class KMPStringMatcher {
        /**
         * 获取KMP算法中pattern字符串对应的next数组
         * 
         * @param p 模式字符串对应的字符数组
         * @return
         */
        protected int[] getNext(char[] p) {
            // 已知next[j] = k,利用递归的思想求出next[j+1]的值
            // 如果已知next[j] = k,如何求出next[j+1]呢?具体算法如下:
            // 1. 如果p[j] = p[k], 则next[j+1] = next[k] + 1;
            // 2. 如果p[j] != p[k], 则令k=next[k],如果此时p[j]==p[k],则next[j+1]=k+1,
            // 如果不相等,则继续递归前缀索引,令 k=next[k],继续判断,直至k=-1(即k=next[0])或者p[j]=p[k]为止
            int pLen = p.length;
            int[] next = new int[pLen];
            int k = -1;
            int j = 0;
            next[0] = -1; // next数组中next[0]为-1
            while (j < pLen - 1) {
                if (k == -1 || p[j] == p[k]) {
                    k++;
                    j++;
                    next[j] = k;
                } else {
                    k = next[k];
                }
            }
            for (int i = 0; i < next.length; i++) {
                System.out.print(next[i]+" ");
            }
            return next;
        }

        public List<Integer> indexOf(String source, String pattern) {
            List<Integer> result = Lists.newArrayList();
            int i = 0, j = 0;
            char[] src = source.toCharArray();
            char[] ptn = pattern.toCharArray();
            int sLen = src.length;
            int pLen = ptn.length;
            int[] next = getNext(ptn);
            boolean end = false;
            while (!end) {
                while (i < sLen && j < pLen) {
                    // 如果j = -1,或者当前字符匹配成功(src[i] = ptn[j]),都让i++,j++
                    if (j == -1 || src[i] == ptn[j]) {
                        i++;
                        j++;
                    } else {
                        // 如果j!=-1且当前字符匹配失败,则令i不变,j=next[j],即让pattern模式串右移j-next[j]个单位
                        j = next[j];
                    }
                }
                // 找到匹配项
                if (j == pLen) {
                    int findIndex = i - j;
                    result.add(findIndex);
                    j = 0;
                }
                if (i == sLen || (sLen - i) < pLen) {
                    end = true;
                }
            }
            return result;
        }
    }

    /**
     * 优化的KMP算法(对next数组的获取进行优化)
     * 
     * @author Tianma
     *
     */
    static class OptimizedKMPStringMatcher extends KMPStringMatcher {

        @Override
        protected int[] getNext(char[] p) {
            // 已知next[j] = k,利用递归的思想求出next[j+1]的值
            // 如果已知next[j] = k,如何求出next[j+1]呢?具体算法如下:
            // 1. 如果p[j] = p[k], 则next[j+1] = next[k] + 1;
            // 2. 如果p[j] != p[k], 则令k=next[k],如果此时p[j]==p[k],则next[j+1]=k+1,
            // 如果不相等,则继续递归前缀索引,令 k=next[k],继续判断,直至k=-1(即k=next[0])或者p[j]=p[k]为止
            int pLen = p.length;
            int[] next = new int[pLen];
            int k = -1;
            int j = 0;
            next[0] = -1; // next数组中next[0]为-1
            while (j < pLen - 1) {
                if (k == -1 || p[j] == p[k]) {
                    k++;
                    j++;
                    // 修改next数组求法
                    if (p[j] != p[k]) {
                        next[j] = k;// KMPStringMatcher中只有这一行
                    } else {
                        // 不能出现p[j] = p[next[j]],所以如果出现这种情况则继续递归,如 k = next[k],
                        // k = next[[next[k]]
                        next[j] = next[k];
                    }
                } else {
                    k = next[k];
                }
            }
            for (int i = 0; i < next.length; i++) {
                System.out.print(next[i]+" ");
            }
            return next;
        }
    }

    @Override
    public List<Integer> match(String str, String pattern) {
        KMPStringMatcher matcher = new OptimizedKMPStringMatcher();
        List<Integer> index = matcher.indexOf(str, pattern);
        if (CollectionUtils.isEmpty(index)) {
            return Lists.newArrayList();
        }
        return index;
    }

    public static void main(String[] args) {
        String originStr = "ababdababcdfddfabcdfababdssd";
        String pattern = "ababd";
        StringMatcher matcher = new KMPMatcher();
        List<Integer> indexes = matcher.match(originStr, pattern);
        System.out.println(JSON.toJSONString(indexes));
        for (int i = 0; i < indexes.size(); i++) {
            int start = indexes.get(i);
            System.out.println(originStr.substring(start, start + pattern.length()));
        }
    }
}

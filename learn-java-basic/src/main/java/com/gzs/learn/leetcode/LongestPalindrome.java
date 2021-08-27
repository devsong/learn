package com.gzs.learn.leetcode;

public class LongestPalindrome {
    public static String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        if (s.length() == 2) {
            return s.charAt(0) == s.charAt(1) ? s : s.substring(0, 1);
        }
        int maxLen = 0;
        char[] charArray = s.toCharArray();
        int start = 0, end = 0;
        for (int i = 0; i < charArray.length; i++) {
            int len1 = expandString(charArray, i, i);
            int len2 = expandString(charArray, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > maxLen) {
                maxLen = len;
                start = i - (maxLen - 1) / 2;
                end = i + maxLen / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    public static int expandString(char[] s, int start, int end) {
        while (start >= 0 && end < s.length && s[start] == s[end]) {
            start--;
            end++;
        }
        return end - start - 1;
    }

    public static void main(String[] args) {
        String str = "ccc";
        System.out.println(longestPalindrome(str));
    }
}

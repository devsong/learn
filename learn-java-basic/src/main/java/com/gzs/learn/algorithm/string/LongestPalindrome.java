package com.gzs.learn.algorithm.string;

/**
 * 最长回文串
 * 
 * @author guanzhisong
 *
 */
public class LongestPalindrome {

	public static String longestPalindrome(String s) {
		return midToBound(s);
	}

	public static String dynamicProgramSolution(String s) {
		// 长度小于2直接判断即可
		if (s.length() <= 2) {
			return s.charAt(0) == s.charAt(1) ? s : String.valueOf(s.charAt(0));
		}

		int len = s.length();
		int[][] dp = new int[len][len];
		// 单个位置的字符必是回文串
		for (int i = 0; i < len; i++) {
			dp[i][i] = 1;
		}
		for (int i = 0; i < len; i++) {
			for (int j = 1; j < len; j++) {
				char first = s.charAt(i);
				char second = s.charAt(j);
				dp[i][j] = (dp[i + 1][j - 1] == 1 && first == second) ? 1 : 0;
			}
		}
		printArr(dp);
		return "";
	}

	public static String midToBound(String s) {
		char[] charArray = s.toCharArray();
		int len = s.length();
		int[] lenArray = new int[s.length()];
		// 最大长度、中心点位置
		int maxLen = 0, begin = 0, end = 0;
		for (int i = 0; i < len; i++) {
			boolean resetMaxLen = false;
			boolean doublePos = false;
			if ((i + 1) < len && charArray[i] == charArray[i + 1]) {
				// 双中心
				doublePos = true;
			}

			for (int j = 0; j < len; j++) {
				int left = i - j;
				int right = i + j;
				if (doublePos) {
					right += 1;
				}
				if (left < 0 || right >= len) {
					break;
				}
				if (charArray[left] == charArray[right]) {
					if ((right - left) > 1) {
						lenArray[i] = lenArray[i] + 1;
					}
					if (lenArray[i] >= maxLen && !resetMaxLen) {
						begin = left;
						end = right;
						lenArray[i] = right - left;
						maxLen = lenArray[i];
					}
				} else {
					resetMaxLen = true;
				}
			}
		}
		for (int i = 0; i < len; i++) {
			System.out.print(lenArray[i] + " ");
		}
		System.out.println("start:" + begin + ",end:" + end + ",maxLen:" + maxLen);
		return s.substring(begin, end + 1);
	}

	public static void printArr(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		String str = "aaa";
		System.out.println(longestPalindrome(str));
		// System.out.println(str.substring(15, 22));
		// System.out.println(str.substring(9, 16));
	}
}

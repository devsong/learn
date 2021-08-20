package com.gzs.learn.leetcode;

import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSON;

public class TwoSum {

    public static int[] arr = {1, 100, 3, 4, 2, 9, 7, 6, 8, 11, 20, 13};

    public static int[] twoSum(int[] nums, int target) {
        // return bruteSolution(arr, 6);
        return hashAlgorithm(nums, target);
    }

    /**
     * 暴力解法
     * 
     * @param nums
     * @param target
     * @return
     */
    public static int[] bruteSolution(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        return null;
    }

    /**
     * HashMap解法
     * 
     * @param nums
     * @param target
     * @return
     */
    public static int[] hashAlgorithm(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int first = nums[i];
            // put index
            int left = target - first;
            if (map.get(left) != null) {
                return new int[] {map.get(left), i};
            }
            if (map.get(first) == null) {
                map.put(first, i);
            }
        }
        return null;
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void bubbleSort2(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static int binarySearch(int[] arr, int search) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (arr[mid] == search) {
                return mid;
            } else if (arr[mid] > search) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(twoSum(new int[] {3, 3, 4}, 6)));
        bubbleSort(arr);
        System.out.println(JSON.toJSONString(arr));
        bubbleSort2(arr);
        // System.out.println("target index is:" + binarySearch(arr, 1));
        System.out.println(JSON.toJSONString(arr));
    }
}

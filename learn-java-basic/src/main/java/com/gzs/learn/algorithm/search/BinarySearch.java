package com.gzs.learn.algorithm.search;

import org.junit.Test;

public class BinarySearch {

    @Test
    public void testBinarySearch() {
        int a[] = {1, 2, 3, 4, 5, 6};
        System.out.println(binarySearch(a, 0, a.length, 1));
    }

    /**
     * 
     * @param a
     * @param low
     * @param high
     * @return
     */
    public static int binarySearch(int a[], int low, int high, int target) {
        while (low < high) {
            int mid = (low + high) / 2;
            int midVal = a[mid];
            if (midVal == target) {
                return mid;
            } else if (midVal > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
}

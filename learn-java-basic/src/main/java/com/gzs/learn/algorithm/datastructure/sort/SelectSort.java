package com.gzs.learn.algorithm.datastructure.sort;

import org.junit.Test;

public class SelectSort implements Sortable<Integer> {

    @Override
    public void sort(Integer[] array) {
        int len = array.length;
        for (int i = 0; i < len - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
    }

    @Test
    public void testSelectSort() {
        sort(ARRAY);
        print(ARRAY);
    }

}

package com.gzs.learn.algorithm.datastructure.sort;

import org.junit.Test;

public class SelectSort implements Sortable<Integer> {

    @Override
    public Integer[] sort(Integer[] array) {
        int len = array.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (array[i] > array[j]) {
                    swap(array, i, j);
                }
            }
        }
        return array;
    }

    @Test
    public void testSelectSort() {
        print(sort(ARRAY));
    }

}

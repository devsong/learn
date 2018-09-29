package com.gzs.learn.algorithm.datastructure.sort;

import org.junit.Test;

public class BubbleSort implements Sortable<Integer> {

    @Override
    public Integer[] sort(Integer[] array) {
        int len = array.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, i, j);
                }
            }
        }
        return array;
    }

    @Test
    public void testBubbleSort() {
        print(sort(ARRAY));
    }

}

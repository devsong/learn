package com.gzs.learn.algorithm.array;

import org.junit.Test;

public class ArrayExample {
    int[] sortArr = {1, 2, 2, 3, 3, 4, 4, 4, 4, 4, 5};

    @Test
    public void testRemoveRepeat() {
        int count = this.removeRepeat();
        System.out.println("count:" + count);
        this.printArr(sortArr, count);
    }

    public int removeRepeat() {
        int insertIndex = 0;
        int repeat = sortArr[insertIndex];
        for (int i = 0; i < sortArr.length; i++) {
            if (repeat == sortArr[i]) {
                continue;
            }
            repeat = sortArr[i];
            this.swap(sortArr, insertIndex, i + 1);
            insertIndex++;
        }
        return insertIndex;
    }

    /**
     * 交换数组指定位置
     *
     * @param arr
     * @param i
     * @param j
     */
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        // arr[i] = arr[i] + arr[j];
        // arr[j] = arr[i] - arr[j];
        // arr[i] = arr[i] - arr[j];
    }

    private void printArr(int[] arr, int len) {
        for (int i = 0; i < len; i++) {
            System.out.print(arr[i] + " ");
        }
    }

}

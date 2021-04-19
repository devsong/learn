package com.gzs.learn.algorithm.array;

import org.junit.Test;

public class ArrayExample {
    int[] sortArr = {1, 1, 2, 2, 3, 3, 4, 4, 4, 4, 4, 5,5};

    @Test
    public void testRemoveRepeat() {
        int count = this.removeRepeat();
        System.out.println("count:" + count);
        // this.printArr(sortArr, sortArr.length);
    }

    public int removeRepeat() {
        int repeatCount = 0;
        int totalRepeatCount = 0;
        int repeat = sortArr[0];
        boolean flag = false;
        for (int i = 1; i < sortArr.length - repeatCount + 1; i++) {
            if (i >= sortArr.length) {
                break;
            }
            if (sortArr[i] == repeat) {
                repeatCount++;
                totalRepeatCount++;
                flag = true;
            } else {
                repeat = sortArr[i];
                flag = false;
            }
            if (repeatCount > 0 && !flag) {
                for (int n = i - 1; n <= sortArr.length - repeatCount; n++) {
                    swap(sortArr, n - repeatCount, n);
                }
                this.printArr(sortArr, sortArr.length);
                System.out.println();
                i -= repeatCount;
                repeatCount = 0;
            }
        }
        return sortArr.length - totalRepeatCount;
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

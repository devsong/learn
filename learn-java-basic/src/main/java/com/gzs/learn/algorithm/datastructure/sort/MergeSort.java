package com.gzs.learn.algorithm.datastructure.sort;

public class MergeSort implements Sortable<Integer> {

    @Override
    public void sort(Integer[] array) {
        sort0(array, 0, array.length);
    }

    private static void sort0(Integer[] array, int low, int high) {
        while (low < high) {
            int mid = (low + high) / 2;
            if (low < mid) {
                sort0(array, low, mid);
                sort0(array, mid + 1, high);
            }
        }
    }

    /**
     * 2路归并
     * 
     * @param array 原始数组
     * @param sub1Low
     * @param sub1High
     * @param sub2Low
     * @param sub2High
     * @return
     */
    private static Integer[] merge(Integer[] array, int sub1Low, int sub1High, int sub2Low, int sub2High) {
        Integer[] result = new Integer[array.length];
        int i = sub1Low, j = sub2Low, k = 0;
        while (i <= sub1High && j <= sub2High) {
            if (array[i] < array[j]) {
                result[k++] = array[i];
                i++;
            } else {
                result[k++] = array[j];
                j++;
            }
        }

        // 处理尾部升序部分数据,执行system.arraycopy
        if (k <= result.length) {
            int remain = sub1High - i + 1;
            if (remain > 0) {
                System.arraycopy(array, i, result, k, remain);
            } else {
                remain = sub2High - j + 1;
                System.arraycopy(array, j, result, k, remain);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Integer[] sub1 = {1, 2, 3, 8, 9, 11};
        // Integer[] sub2 = {4, 5, 6, 7, 10};
        Integer[] result = merge(sub1, 0, 2, 3, 5);
        for (Integer e : result) {
            System.out.print(e + " ");
        }
    }

}

package com.gzs.learn.algorithm.datastructure.sort;

public class MergeSort implements Sortable<Integer> {

    @Override
    public void sort(Integer[] array) {
        Integer[] copyArray = new Integer[array.length];
        sort0(array, 0, array.length, copyArray);
        print(copyArray);
    }

    private void sort0(Integer[] array, int low, int high, Integer[] copyArray) {
        int mid = low + high / 2;
        if (low < high) {
            // 递归调用
            sort0(array, low, mid, copyArray);
            sort0(array, mid + 1, high, copyArray);
            merge(array, low, mid, high, copyArray);
        }
    }

    private void merge(Integer[] array, int low, int mid, int high, Integer[] copyArray) {
        int l = low, m = mid + 1, insertPos = low;
        // int len = high - low + 1;
        while (l <= mid || m <= high) {
            if (array[insertPos] < array[m]) {
                copyArray[insertPos++] = array[l++];
            } else {
                copyArray[insertPos++] = array[m++];
            }
        }
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        // Integer[] sub1 = {1, 2, 3, 8, 9, 11};
        // Integer[] sub2 = {4, 5, 6, 7, 10};
        // Integer[] result = new Integer[sub1.length + sub2.length];
        //
        // mergeSort.merge(result, sub1, 0, sub1.length - 1, sub2, 0, sub2.length - 1);
        // for (Integer e : result) {
        // System.out.print(e + " ");
        // }

        mergeSort.print(ARRAY);
        mergeSort.sort(ARRAY);
    }

}

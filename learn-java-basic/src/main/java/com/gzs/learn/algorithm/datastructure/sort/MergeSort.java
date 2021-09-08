package com.gzs.learn.algorithm.datastructure.sort;

public class MergeSort implements Sortable<Integer> {

    @Override
    public void sort(Integer[] array) {
        Integer[] result = new Integer[array.length];
        sort0(array, 0, array.length - 1, result);
    }

    private void sort0(Integer[] array, int low, int high, Integer[] result) {
        int len = high - low;
        int mid = len / 2 + low;
        if (low < high) {
            // left
            sort0(array, low, mid, result);
            // right
            sort0(array, mid + 1, high, result);
            // merge
            merge(array, low, high, result);
        }
    }

    private void merge(Integer[] origin, int start, int end, Integer[] result) {
        // 结果集起始下标
        int sortPos = start;
        // 中间位置
        int mid = (start + end) / 2;
        // 左右下标起始位置
        int left = start, right = mid + 1;
        while (left <= mid && right <= end) {
            if (origin[left] < origin[right]) {
                result[sortPos++] = origin[left++];
            } else {
                result[sortPos++] = origin[right++];
            }
        }
        while (left <= mid) {
            result[sortPos++] = origin[left++];
        }
        while (right <= end) {
            result[sortPos] = origin[right++];
        }
        // 归并后的数据copy回元数组
        for (int i = start; i <= end; i++) {
            origin[i] = result[i];
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
        mergeSort.sort(ARRAY);
        System.out.println();
        mergeSort.print(ARRAY);
        // mergeSort.sort(ARRAY);
    }

}

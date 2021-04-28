package com.gzs.learn.algorithm.datastructure.sort;

public class MergeSort implements Sortable<Integer> {

    @Override
    public void sort(Integer[] array) {
        sort0(array, 0, array.length);
    }

    private void sort0(Integer[] array, int low, int high) {
        Integer[] result = new Integer[array.length];
        int firstLen = 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (low < mid) {
                sort0(array, low, mid);
                sort0(array, mid + 1, high);
            } else {
                if (array[low] > array[high]) {
                    swap(array, low, high);
                }
                merge(result,array,low,mid-low,array,mid+1,high);

            }
        }
        // merge(result, array, low, firstLen, array, high - firstLen, high);
    }

    /**
     * 2路归并
     *
     * @param result
     * @param sub1
     * @param sub1Low
     * @param sub1High
     * @param sub2
     * @param sub2Low
     * @param sub2High
     */
    private void merge(Integer[] result, Integer[] sub1, int sub1Low, int sub1High, Integer[] sub2, int sub2Low, int sub2High) {
        int i = sub1Low, j = sub2Low, k = 0;
        while (i <= sub1High && j <= sub2High) {
            if (sub1[i] < sub2[j]) {
                result[k++] = sub1[i++];
            } else {
                result[k++] = sub2[j++];
            }
        }

        // 处理尾部升序部分数据,执行system.arraycopy
        if (k <= result.length) {
            int remain = sub1High - i + 1;
            if (remain > 0) {
                System.arraycopy(sub1, i, result, k, remain);
            } else {
                remain = sub2High - j + 1;
                System.arraycopy(sub2, j, result, k, remain);
            }
        }
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
//        Integer[] sub1 = {1, 2, 3, 8, 9, 11};
//        Integer[] sub2 = {4, 5, 6, 7, 10};
//        Integer[] result = new Integer[sub1.length + sub2.length];
//
//        mergeSort.merge(result, sub1, 0, sub1.length - 1, sub2, 0, sub2.length - 1);
//        for (Integer e : result) {
//            System.out.print(e + " ");
//        }

        mergeSort.print(ARRAY);
        mergeSort.sort(ARRAY);
        mergeSort.print(ARRAY);
    }

}

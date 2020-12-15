package com.gzs.learn.algorithm.datastructure.sort;

public class InsertSort implements Sortable<Integer> {

    @Override
    public Integer[] sort(Integer[] array) {
        Integer[] copy = new Integer[array.length];
        System.arraycopy(array, 0, copy, 0, array.length);
        int len = copy.length;
        for (int i = 1; i < len; i++) {
            int pivot = copy[i];
            int j = i;
            while (j > 0 && copy[j - 1] > pivot) {
                copy[j] = copy[j - 1];
                j--;
            }
            copy[j] = pivot;
        }
        return copy;
    }

    public static void main(String[] args) {
        Sortable<Integer> s = new InsertSort();
        Integer[] result = s.sort(Sortable.ARRAY);
        s.print(result);
    }

}

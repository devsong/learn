package com.gzs.learn.algorithm.datastructure.sort;

public class InsertSort implements Sortable<Integer> {

    @Override
    public void sort(Integer[] array) {
        int len = array.length;
        for (int i = 1; i < len; i++) {
            int pivot = array[i];
            int j = i;
            while (j > 0 && array[j - 1] > pivot) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = pivot;
        }
    }

    public static void main(String[] args) {
        Sortable<Integer> s = new InsertSort();
        s.sort(Sortable.ARRAY);
        s.print(Sortable.ARRAY);
    }

}

package com.gzs.learn.algorithm.datastructure.sort;

public class QuickSort implements Sortable<Integer> {

    @Override
    public void sort(Integer[] array) {
        int low = 0;
        int high = array.length - 1;
        quickSort(array, low, high);
    }

    private void quickSort(Integer[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        int pos = partition(array, low, high);
        quickSort(array, low, pos - 1);
        quickSort(array, pos + 1, high);
    }

    private int partition(Integer[] array, int low, int high) {
        int pivot = array[low], pos = low + 1;
        for (int i = pos; i <= high; i++) {
            if (array[i] < pivot) {
                swap(array, i, pos);
                pos++;
            }
        }
        swap(array, low, pos - 1);
        return pos - 1;
    }

    public static void main(String[] args) {
        QuickSort sort = new QuickSort();
        sort.sort(ARRAY);
        sort.print(ARRAY);
    }
}

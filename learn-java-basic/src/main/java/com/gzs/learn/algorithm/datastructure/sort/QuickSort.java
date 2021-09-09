package com.gzs.learn.algorithm.datastructure.sort;

public class QuickSort implements Sortable<Integer> {

    @Override
    public void sort(Integer[] array) {
        int low = 0;
        int high = array.length - 1;
        quickSort(array, low, high);
    }

    private void quickSort(Integer[] array, int low, int high) {
        if (low < high) {}
        int p = array[low], pos = low, left = low + 1;

        while (left <= high) {
            int elem = array[left];
            if (elem >= p) {
                left++;
            } else {
                for (int j = left; j > low; j--) {
                    array[j] = array[j - 1];
                }
                array[low] = elem;
                pos++;
            }
        }

       
            quickSort(array, low, pos);
            if (pos < high) {
                quickSort(array, pos + 1, high);
            }
        }
    }

    public static void main(String[] args) {
        QuickSort sort = new QuickSort();
        sort.sort(ARRAY);
        sort.print(ARRAY);
    }
}

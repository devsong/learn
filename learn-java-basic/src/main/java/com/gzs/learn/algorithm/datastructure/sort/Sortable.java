package com.gzs.learn.algorithm.datastructure.sort;

public interface Sortable<T> {
    Integer[] ARRAY = { 9, 7, 8, 5, 3, 2, 1, 4, 6, 0 };

    public void sort(T[] array);

    default void print(T[] array) {
        for (T t : array) {
            System.out.print(t.toString() + ",");
        }
    }

    default void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

package com.gzs.learn.algorithm.datastructure.sort;

public interface Sortable<T> {
    Integer[] ARRAY = {24, 1, 23, 2, 3, 4, 1, 5, 6, 7};

    public T[] sort(T[] array);

    default void print(T[] array) {
        for (T t : array) {
            System.out.print(t.toString() + " ");
        }
    }

    default void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

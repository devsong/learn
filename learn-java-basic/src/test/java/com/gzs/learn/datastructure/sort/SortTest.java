package com.gzs.learn.datastructure.sort;

import java.util.Arrays;
import org.junit.Test;

public class SortTest {

    int a[] = { 6, 3, 8, 9, 4, 2, 7, 1, 0, 5 };

    // int a[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    // int a[] = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
    @Test
    public void testSysSort() {
        System.out.println("system sort:" + a.length);
        Arrays.sort(a);
        print(a);
        System.out.println();
    }

    @Test
    public void testBubbleSort() {
        System.out.println("bubble sort:" + a.length);
        bubbleSort(a);
        print(a);
        System.out.println();
        System.out.println(a.length);
    }

    public static void bubbleSort(int a[]) {
        int len = a.length;
        int loopTimes = 0, cmpTimes = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                loopTimes++;
                if (a[i] > a[j]) {
                    int temp = a[j];
                    a[j] = a[i];
                    a[i] = temp;
                    cmpTimes++;
                }
            }
        }
        System.out.println("bubble looptimes:" + loopTimes + " cmptimes:" + cmpTimes);
    }

    @Test
    public void testSelectSort() {
        System.out.println("select sort:" + a.length);
        selectSort(a);
        print(a);
        System.out.println();
        System.out.println(a.length);
    }

    public static void selectSort(int a[]) {
        int len = a.length;
        int loopTimes = 0, cmpTimes = 0;
        for (int i = 1; i < len; i++) {
            int pivot = a[i - 1], index = i - 1;
            for (int j = i; j < len; j++) {
                loopTimes++;
                if (a[j] < a[index]) {
                    index = j;
                }
            }
            if (index != (i - 1)) {
                a[i - 1] = a[index];
                a[index] = pivot;
                cmpTimes++;
            }
        }
        System.out.println("select looptimes:" + loopTimes + " cmptimes:" + cmpTimes);
    }

    @Test
    public void testInsertSort() {
        System.out.println("insert sort:" + a.length);
        insertSort(a);
        print(a);
        System.out.println();
        System.out.println(a.length);
    }

    public static void insertSort(int a[]) {
        int len = a.length;
        // int loopTimes = 0, cmpTimes = 0;
        for (int i = 1; i < len; i++) {
            int pivot = a[i];
            int j = i;
            while (j > 0 && a[j - 1] > pivot) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = pivot;
        }
        // System.out.println("insert looptimes:" + loopTimes + " cmptimes:" + cmpTimes);
    }

    public static void print(int a[]) {
        int len = a.length;
        for (int i = 0; i < len; i++) {
            System.out.print(a[i] + " ");
        }
    }
}

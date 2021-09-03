package com.gzs.learn.leetcode;

import org.junit.Test;

public class PrimeNumber {

    @Test
    public void print() {
        print(100);
        System.out.println();
        print(excluedPrime(100));
    }

    void print(int n) {
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                System.out.print(i + " ");
            }
        }
    }

    boolean isPrime(int n) {
        int sqrtN = (int) Math.sqrt(n) + 1;
        for (int i = 2; i < sqrtN; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    int[] excluedPrime(int n) {
        int[] a = new int[n - 1];
        int len = a.length;
        for (int i = 0; i < len; i++) {
            a[i] = i + 2;
        }
        for (int k = 0; k < len; k++) {
            int exclude = a[k];
            while (exclude == 0 && k < len - 1) {
                k++;
                exclude = a[k];
            }

            if (exclude * exclude > n) {
                break;
            }
            for (int j = k + 1; j < len - 1; j++) {
                int elem = a[j];
                while (elem == 0 && j < len - 1) {
                    j++;
                    elem = a[j];
                }
                if (elem % exclude == 0) {

                    a[j] = 0;
                }
            }
        }
        return a;
    }

    void print(int[] prime) {
        for (int i = 0; i < prime.length; i++) {
            if (prime[i] == 0) {
                continue;
            }
            System.out.print(prime[i] + " ");
        }
        System.out.println();
    }

}

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
        int[] list = new int[n - 1];
        int len = list.length;
        for (int i = 2; i <= n; i++) {
            list[i - 2] = i;
        }
        for (int k = 0; k < len; k++) {
            Integer exclude = list[k];
            while (exclude == 0 && k < len - 1) {
                k++;
                exclude = list[k];
            }

            if (exclude * exclude > n) {
                break;
            }
            for (int j = k + 1; j < len - 1; j++) {
                int elem = list[j];
                while (elem == 0 && j < len - 1) {
                    j++;
                    elem = list[j];
                }
                if (elem % exclude == 0) {

                    list[j] = 0;
                }
            }
        }
        return list;
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

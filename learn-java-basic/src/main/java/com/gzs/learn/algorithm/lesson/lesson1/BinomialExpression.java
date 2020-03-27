package com.gzs.learn.algorithm.lesson.lesson1;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.base.Stopwatch;

public class BinomialExpression {
    int x = 2;
    static int a[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    static int ITERATION_COUNTS = 10000000;

    @Test
    public void recursiveMethod() {
        Stopwatch start = Stopwatch.createStarted();
        long result = 0;
        for (int n = 0; n < ITERATION_COUNTS; n++) {
            result = 0;
            for (int i = 0; i < a.length; i++) {
                result += a[i] * Math.pow(x, i);
            }
        }
        start.stop();

        long elapse = start.elapsed(TimeUnit.MILLISECONDS);
        System.out.println("result=" + result + " recursiveMethod cost:" + elapse);
    }

    @Test
    public void iterationMethod() {
        Stopwatch start = Stopwatch.createStarted();
        long result = 0;
        for (int n = 0; n < ITERATION_COUNTS; n++) {
            result = 0;
            int len = a.length;
            int p = a[len - 1];
            for (int i = len - 1; i > 0; i--) {
                p = a[i - 1] + x * p;
            }
            result = p;
        }
        start.stop();

        long elapse = start.elapsed(TimeUnit.MILLISECONDS);
        System.out.println("result=" + result + " iterationMethod cost:" + elapse);
    }
}

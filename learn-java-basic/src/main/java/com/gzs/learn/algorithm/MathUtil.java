package com.gzs.learn.algorithm;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;
import org.junit.Test;
import com.google.common.base.Stopwatch;

public class MathUtil {
    private DecimalFormat df = new DecimalFormat("#.#####");;

    @Test
    public void calcLimit() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        int n = 100000;
        BigDecimal numerator = calcSingle(n + 1, n);
        BigDecimal denominator = calcSingle(n, n);
        BigDecimal result = numerator.divide(denominator, 10, RoundingMode.HALF_UP);
        stopwatch.stop();
        System.out.println(df.format(result));
        System.out.println(stopwatch.toString());
    }

    /**
     * 
     * @param base 基数
     * @param index 指数
     * @return
     */
    private BigDecimal calcSingle(int base, int index) {
        int threshold = 10;
        if (index < threshold) {
            return new BigDecimal(Math.pow(base, index));
        }
        BigDecimal tmpResult = new BigDecimal(Math.pow(base, threshold));
        int loopCount = index % threshold == 0 ? index / threshold : index / threshold + 1;
        BigDecimal result = new BigDecimal(1);
        for (int i = 0; i < loopCount; i++) {
            if (i < loopCount - 1) {
                result = result.multiply(tmpResult);
            } else {
                // 算出最后一轮
                if (index % threshold == 0) {
                    result = result.multiply(tmpResult);
                } else {
                    result = result.multiply(new BigDecimal(Math.pow(base, index % threshold)));
                }
            }
        }
        return result;
    }

    @Test
    public void testSingleCacl() {
        System.out.println(df.format(calcSingle(2, 20)));
    }

    @Test
    public void testHash() {
        int a = 31 * 3;
        int b = (3 << 5) - 3;
        System.out.println(a == b);
        a = 17 * 3;
        b = (3 << 4) + 3;
        System.out.println(a == b);
    }

    @Test
    public void testSqrt() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            double d = random.nextDouble();
            Math.sqrt(d);
        }
        stopwatch.stop();
        System.out.println("system sqrt cost:" + stopwatch.toString());
    }

    @Test
    public void testNewTonIterator() {
        System.out.println(Math.sqrt(2));
        double eps = 0.00000001;
        System.out.println(newtonIterator(2, eps));
        Stopwatch stopwatch = Stopwatch.createStarted();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            double d = random.nextDouble();
            newtonIterator(d, eps);
        }
        stopwatch.stop();
        System.out.println("newton iterator sqrt cost:" + stopwatch.toString());
    }

    @Test
    public void testBinaryIterator() {
        double eps = 0.000000000001;
        double[] testArray = {0.2, 0.81, 0.16, 2, 4, 5};
        for (double t : testArray) {
            System.out.println(Math.sqrt(t));
            System.out.println(binaryIterator(t, eps));
        }
        Stopwatch stopwatch = Stopwatch.createStarted();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            double d = random.nextDouble() * 1000;
            binaryIterator(d, eps);
        }
        stopwatch.stop();
        System.out.println("binary iterator sqrt cost:" + stopwatch.toString());
    }

    private static double newtonIterator(double a, double eps) {
        double val = a;
        double last = a / 2;
        do {
            last = val;
            val = (val + a / val) / 2;
        } while (Math.abs(val - last) > eps);
        return val;
    }

    private static double binaryIterator(double a, double eps) {
        if (a < 0) {
            throw new IllegalArgumentException("paramter must be greater than 0");
        }
        if (a == 0) {
            return 0;
        }
        if (a > 1) {
            double low = 0, high = a;
            double mid = (low + high) / 2;
            double last = 0;
            do {
                last = mid * mid;
                if (last == a) {
                    return mid;
                }
                if (last < a) {
                    low = mid;
                } else {
                    high = mid;
                }
                mid = (low + high) / 2;
            } while (Math.abs(last - a) > eps);
            return mid;
        } else {
            double low = a, high = 1;
            double mid = (low + high) / 2;
            double last = 0;
            do {
                last = mid * mid;
                if (last == a) {
                    return mid;
                }
                if (last < a) {
                    low = mid;
                } else {
                    high = mid;
                }
                mid = (low + high) / 2;
            } while (Math.abs(last - a) > eps);
            return mid;
        }
    }
}

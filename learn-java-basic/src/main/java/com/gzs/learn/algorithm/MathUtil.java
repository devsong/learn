package com.gzs.learn.algorithm;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

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
}

package com.gzs.learn.juc;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * cpu 缓存测试用例
 *
 * @author guanzhisong
 * @date 2017年7月12日
 */
@Slf4j
public class CacheLineHit {
    private static final int RUNS = 3;
    private static final int DIMENSION_1 = 1024 * 1024;
    private static final int DIMENSION_2 = 6;

    private static long[][] longs = new long[DIMENSION_1][DIMENSION_2];
    private static long[] longs_direct = new long[DIMENSION_1 * DIMENSION_2];

    @Test
    public void startUp() {
        for (int i = 0; i < DIMENSION_1; i++) {
            for (int j = 0; j < DIMENSION_2; j++) {
                longs[i][j] = j;
            }
        }
        for (int i = 0; i < longs_direct.length; i++) {
            longs_direct[i] = i % DIMENSION_2;
        }
        addByRowFirst(RUNS);
        System.out.println("------------");
        addByColumnFirst(RUNS);
        System.out.println("------------");
        addByDirect(RUNS);
    }

    /**
     * 按行顺序添加
     *
     * @param runs
     */
    private static void addByRowFirst(int runs) {
        log.info("add by row order  order starting....");
        for (int r = 0; r < runs; r++) {
            final long start = System.nanoTime();
            long sum = 0L;
            for (int i = 0; i < DIMENSION_1; i++) {
                for (int j = 0; j < DIMENSION_2; j++) {
                    sum += longs[i][j];
                }
            }

            long cost = System.nanoTime() - start;
            log.info("add by row order end result sum:{}, cost time:{}", sum, cost);
        }
    }

    /**
     * 按列顺序添加
     *
     * @param runs 运行次数
     */
    private static void addByColumnFirst(int runs) {
        log.info("add by column order starting....");
        for (int r = 0; r < runs; r++) {
            final long start = System.nanoTime();
            long sum = 0L;
            for (int j = 0; j < DIMENSION_2; j++) {
                for (int i = 0; i < DIMENSION_1; i++) {
                    sum += longs[i][j];
                }
            }
            long cost = System.nanoTime() - start;
            log.info("add by column order end result sum:{}, cost time:{}", sum, cost);
        }
    }

    /**
     * 直接添加
     *
     * @param runs 运行次数
     */
    private static void addByDirect(int runs) {
        log.info("add by direct starting....");
        for (int r = 0; r < runs; r++) {
            final long start = System.nanoTime();
            long sum = 0L;
            final int len = DIMENSION_1 * DIMENSION_2;
            for (int j = 0; j < len; j++) {
                sum += longs_direct[j];
            }

            long cost = System.nanoTime() - start;
            log.info("add by column order end result sum:{}, cost time:{}", sum, cost);
        }
    }

    @Test
    public void testCalender() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        System.out.println("hours:" + calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("minute:" + calendar.get(Calendar.MINUTE));
    }
}

package com.gzs.learn.juc;

/**
 * cpu 缓存测试用例
 *
 * @author guanzhisong
 * @date 2017年7月12日
 */
public class CacheLineHit {
    private static final int RUNS = 3;
    private static final int DIMENSION_1 = 1024 * 1024;
    private static final int DIMENSION_2 = 6;

    private static long[][] longs = new long[DIMENSION_1][DIMENSION_2];
    private static long[] longs_direct = new long[DIMENSION_1 * DIMENSION_2];

    public static void main(String[] args) throws Exception {
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

    private static void addByRowFirst(int runs) {
        System.out.println("starting....");
        for (int r = 0; r < runs; r++) {
            final long start = System.nanoTime();
            long sum = 0L;
            for (int i = 0; i < DIMENSION_1; i++) {
                for (int j = 0; j < DIMENSION_2; j++) {
                    sum += longs[i][j];
                }
            }

            System.out.println("sum=" + sum);
            System.out.println("cost:" + (System.nanoTime() - start));
        }
    }

    private static void addByColumnFirst(int runs) {
        System.out.println("starting....");
        for (int r = 0; r < runs; r++) {
            final long start = System.nanoTime();
            long sum = 0L;
            for (int j = 0; j < DIMENSION_2; j++) {
                for (int i = 0; i < DIMENSION_1; i++) {
                    sum += longs[i][j];
                }
            }

            System.out.println("sum=" + sum);
            System.out.println("cost:" + (System.nanoTime() - start));
        }
    }

    private static void addByDirect(int runs) {
        System.out.println("starting....");
        for (int r = 0; r < runs; r++) {
            final long start = System.nanoTime();
            long sum = 0L;
            final int len = DIMENSION_1 * DIMENSION_2;
            for (int j = 0; j < len; j++) {
                sum += longs_direct[j];
            }

            System.out.println("sum=" + sum);
            System.out.println("cost:" + (System.nanoTime() - start));
        }
    }
}

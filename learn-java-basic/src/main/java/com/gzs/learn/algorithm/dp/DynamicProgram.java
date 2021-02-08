package com.gzs.learn.algorithm.dp;

/**
 * 动态规划之背包问题
 * 
 * @author guanzhisong
 *
 */
public class DynamicProgram {
    static int C = 20;
    static int[] VOL = {2, 3, 6, 4, 2, 8, 3};
    static int[] WEIGHT = {5, 3, 2, 4, 5, 6, 8};

}


class Diamond {
    private int vol;
    private int weight;

    public Diamond(int vol, int weight) {
        this.vol = vol;
        this.weight = weight;
    }
}

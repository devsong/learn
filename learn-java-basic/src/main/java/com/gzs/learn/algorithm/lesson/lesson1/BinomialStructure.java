package com.gzs.learn.algorithm.lesson.lesson1;

import lombok.Data;

@Data
public class BinomialStructure implements Comparable<BinomialStructure> {
    // 基数
    private Integer base;
    // 指数
    private Integer index;
    // 系数
    private Double coefficient;

    @Override
    public int compareTo(BinomialStructure o) {
        return o.getIndex().compareTo(this.getIndex());
    }
}

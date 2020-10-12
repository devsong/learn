package com.gzs.learn.algorithm.tree;

/**
 * 平衡树
 * @author guanzhisong
 *
 * @param <T>
 */
public interface IAvlTree<T> {
    // 前 中 后序遍历
    public static final int PRE_ORDER = 1;
    public static final int MID_ORDER = 2;
    public static final int POST_ORDER = 3;
    // 层次遍历
    public static final int LEVEL_ORDER = 4;

    public int level();

    public void add();
}

package com.gzs.learn.algorithm.tree;

import java.util.List;

/**
 * 平衡树
 * @author guanzhisong
 *
 * @param <T>
 */
public interface IAvlTree<T extends Comparable<T>> {
    // 前 中 后序遍历
    static final int PRE_ORDER = 1;
    static final int MID_ORDER = 2;
    static final int POST_ORDER = 3;
    // 层次遍历
    static final int LEVEL_ORDER = 4;

    /**
     * 层级
     * @return
     */
    int level();

    TreeNode<T> find(TreeNode<T> node);

    /**
     * 添加节点
     * @param treeNode
     */
    void add(TreeNode<T> treeNode);

    /**
     * 删除节点
     * @param treeNode
     */
    void remove(TreeNode<T> treeNode);

    /**
     * 前序遍历
     * @return
     */
    List<T> preOrder();

    /**
     * 中序遍历
     * @return
     */
    List<T> midOrder();

    /**
     * 后序遍历
     * @return
     */
    List<T> postOrder();
}

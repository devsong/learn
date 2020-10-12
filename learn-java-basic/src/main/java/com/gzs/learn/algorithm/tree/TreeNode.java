package com.gzs.learn.algorithm.tree;

import lombok.Data;

@Data
public class TreeNode<T> {
    /**
     * 节点值
     */
    private T val;
    /**
     * 节点层数
     */
    private int level;
    /**
     * 左子树
     */
    private TreeNode<T> left;
    /**
     * 右子树
     */
    private TreeNode<T> right;

    public TreeNode(T val, int level) {
        this.val = val;
        this.level = level;
    }

    /**
     *  判断是否是叶子节点
     * @return
     */
    public boolean isLeaf() {
        return left == null && right == null;
    }
}

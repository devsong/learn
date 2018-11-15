package com.gzs.learn.algorithm.tree;

import lombok.Data;

@Data
public class TreeNode {
    /**
     * 节点值
     */
    private int val;
    /**
     * 节点层数
     */
    private int level;
    /**
     * 是否是叶子节点
     */
    private boolean isLeaf;
    /**
     * 左子树
     */
    private TreeNode left;
    /**
     * 右子树
     */
    private TreeNode right;

    public TreeNode(int val, int level, boolean isLeaf) {
        this.val = val;
        this.level = level;
        this.isLeaf = isLeaf;
    }
}

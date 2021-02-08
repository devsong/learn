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

    /**
     * 父节点
     */
    private TreeNode<T> parent;



    public static class RBTreeNode<T> extends TreeNode<T> {
        public boolean RED = true;
        public boolean BLACK = false;
        /**
         * 红黑树标志
         */
        private boolean red;

        public RBTreeNode() {
            super();
            this.red = RED;
        }

        public boolean isRed() {
            return red;
        }

        public void setRed(boolean red) {
            this.red = red;
        }
    }

    public static class AVLTreeNode<T> extends TreeNode<T> {
        public static final int EH = 0;
        public static final int LH = 1;
        public static final int RH = -1;

        public boolean taller = false;
        public int balanceFactor = 0;

        public AVLTreeNode(T value, int level, int balanceFactor) {
            this.setVal(value);
            this.setLevel(level);
            this.balanceFactor = balanceFactor;
        }
    }

    public TreeNode() {

    }

    public TreeNode(T val, int level) {
        this.val = val;
        this.level = level;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    /**
     * 判断是否是叶子节点
     * 
     * @return
     */
    public boolean isLeaf() {
        return left == null && right == null;
    }
}

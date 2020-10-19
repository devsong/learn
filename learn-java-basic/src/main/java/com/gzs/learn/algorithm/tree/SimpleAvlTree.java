package com.gzs.learn.algorithm.tree;

import java.util.List;

/**
 * 普通平衡二叉树
 * @author guanzhisong
 *
 * @param <T>
 */
public class SimpleAvlTree<T extends Comparable<T>> extends AbstractAvlTree<T> {
    public SimpleAvlTree() {

    }

    public SimpleAvlTree(T data[]) {
        this(data, false);
    }

    public SimpleAvlTree(T data[], boolean isSort) {
        createTreeNode(data, isSort);
    }

    /**
     * 
     * @param data data for array
     * @param isSort sort flag
     * @return
     */
    private void createTreeNode(T data[], boolean isSort) {
        if (data == null) {
            throw new IllegalArgumentException("data must not be null");
        }
        if (isSort) {
            this.root = createFromBinarySearch(data, 0, data.length, 0);
        } else {
            this.root = createFromNormalArray(data, 0);
        }
    }

    private TreeNode<T> createFromNormalArray(T a[], int level) {
        return null;
    }

    /**
     * level for node
     * @param node
     * @return
     */
    @Override
    public int level() {
        if (root == null) {
            return -1;
        } else {
            return level;
        }
    }

    /**
     * 插入操作
     * @param treeNode
     */
    @Override
    public void add(TreeNode<T> treeNode) {

    }

    /**
     * 删除操作
     * @param treeNode
     */
    @Override
    public void remove(TreeNode<T> treeNode) {

    }

    public static void main(String[] args) {
        Integer a[] = { 1, 2, 3, 4, 5, 6, 7, 8 };
        SimpleAvlTree<Integer> avlTree = new SimpleAvlTree<Integer>(a, true);
        // List<Integer> result = avlTree.preOrder();
        // List<Integer> result = avlTree.midOrder();
        List<Integer> result = avlTree.postOrder();
        for (Integer data : result) {
            System.out.print(data + " ");
        }
        System.out.println();
        System.out.println("AvlTree level is:" + avlTree.level());
    }
}

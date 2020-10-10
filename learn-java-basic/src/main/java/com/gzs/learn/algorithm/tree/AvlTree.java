package com.gzs.learn.algorithm.tree;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class AvlTree {

    /**
     * 
     * @param data data for array
     * @param isSort sort flag
     * @return
     */
    public TreeNode createTreeNode(int data[], boolean isSort) {
        if (data.length == 0) {
            return null;
        }
        if (isSort) {
            return createFromBinarySearch(data, 0, data.length, 0);
        } else {
            return createFromNormalArray(data, 0);
        }
    }

    public TreeNode createFromNormalArray(int a[], int level) {
        return null;
    }

    /**
     * level for node
     * @param node
     * @return
     */
    public int level(TreeNode node) {
        if (node == null) {
            return -1;
        } else {
            return node.getLevel();
        }
    }

    /**
     *  二分法构造
     * @param a array for data
     * @param low low pos
     * @param high high pos
     * @param level level
     * @return
     */
    public TreeNode createFromBinarySearch(int a[], int low, int high, int level) {
        TreeNode node = null;
        if (low < high) {
            int mid = (low + high) / 2;
            node = new TreeNode(a[mid], level, false);
            level = level + 1;
            node.setLeft(createFromBinarySearch(a, low, mid, level));
            node.setRight(createFromBinarySearch(a, mid + 1, high, level));
        }
        return node;
    }

    public void preOrder(TreeNode root) {
        if (root != null) {
            System.out.print(root.getVal() + " ");
            preOrder(root.getLeft());
            preOrder(root.getRight());
        }
    }

    public void midOrder(TreeNode root) {
        if (root != null) {
            midOrder(root.getLeft());
            System.out.print(root.getVal() + " ");
            midOrder(root.getRight());
        }
    }

    public void postOrder(TreeNode root) {
        if (root != null) {
            postOrder(root.getLeft());
            postOrder(root.getRight());
            System.out.print(root.getVal() + " ");
        }
    }

    @Test
    public void testCreateTreeNode() {
        int a[] = { 1, 2, 3, 4, 5, 6, 7 };
        TreeNode root = createTreeNode(a, true);
        preOrder(root);
        System.out.println();
        midOrder(root);
        System.out.println();
        postOrder(root);
    }

    @Test
    public void testIterator() {
        List<Integer> list = Lists.newArrayList(1, 2, 3);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            int val = iterator.next();
            System.out.println(val);
            if (val == 2) {
                iterator.remove();
            }
        }
    }
}

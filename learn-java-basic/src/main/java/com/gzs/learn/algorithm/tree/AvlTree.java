package com.gzs.learn.algorithm.tree;

import org.junit.Test;

public class AvlTree {

    public TreeNode createTreeNode(int a[]) {
        if (a.length == 0) {
            return null;
        }
        return createFronBinarySearch(a, 0, a.length, 0);
    }

    public TreeNode createFronBinarySearch(int a[], int low, int high, int level) {
        TreeNode root = null;
        if (low < high) {
            int mid = (low + high) / 2;
            root = new TreeNode(a[mid], level, false);
            level++;
            root.setLeft(createFronBinarySearch(a, low, mid, level));
            root.setRight(createFronBinarySearch(a, mid + 1, high, level));
        }
        return root;
    }

    public void frontLoop(TreeNode root) {
        if (root != null) {
            System.out.print(root.getVal() + " ");
            frontLoop(root.getLeft());
            frontLoop(root.getRight());
        }
    }

    public void midLoop(TreeNode root) {
        if (root != null) {
            midLoop(root.getLeft());
            System.out.print(root.getVal() + " ");
            midLoop(root.getRight());
        }
    }

    public void endLoop(TreeNode root) {
        if (root != null) {
            endLoop(root.getLeft());
            endLoop(root.getRight());
            System.out.print(root.getVal() + " ");
        }
    }

    @Test
    public void testCreateTreeNode() {
        int a[] = {1, 2, 3, 4, 5, 6, 7};
        TreeNode root = createTreeNode(a);
        frontLoop(root);
        System.out.println();
        midLoop(root);
        System.out.println();
        endLoop(root);
    }
}

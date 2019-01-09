package com.gzs.learn.algorithm.tree;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class AvlTree {

	public TreeNode createTreeNode(int a[]) {
		if (a.length == 0) {
			return null;
		}
		return createFromBinarySearch(a, 0, a.length, 0);
	}

	public TreeNode createFromBinarySearch(int a[], int low, int high, int level) {
		TreeNode root = null;
		if (low < high) {
			int mid = (low + high) / 2;
			root = new TreeNode(a[mid], level, false);
			level++;
			root.setLeft(createFromBinarySearch(a, low, mid, level));
			root.setRight(createFromBinarySearch(a, mid + 1, high, level));
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
		int a[] = { 1, 2, 3, 4, 5, 6, 7 };
		TreeNode root = createTreeNode(a);
		frontLoop(root);
		System.out.println();
		midLoop(root);
		System.out.println();
		endLoop(root);
	}

	@Test
	public void testIterator() {
		List<Integer> list = Lists.newArrayList(1, 2, 3);
		Iterator<Integer> iterator = list.iterator();
		while (iterator.hasNext()) {
			int val = iterator.next();
			System.out.println(val);
			if(val == 2) {
				iterator.remove();
			}
		}
	}
}

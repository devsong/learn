package com.gzs.learn.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.junit.Test;
import com.alibaba.fastjson.JSON;
import com.gzs.learn.algorithm.tree.SimpleAvlTree;
import com.gzs.learn.algorithm.tree.TreeNode;

public class TreeTest {

    @Test
    public void testAvlTree() {
        int[] data = {8, 5, 9, 7, 6, 2, 3, 1};
        SimpleAvlTree<Integer> avl = new SimpleAvlTree<>();
        for (int i = 0; i < data.length; i++) {
            avl.add(data[i]);
        }

        List<Integer> levelOrder = avl.levelOrder();
        System.out.print("level order:");
        for (Integer v : levelOrder) {
            System.out.print(v + " ");
        }

        System.out.println();
        System.out.print("pre order:");
        List<Integer> preOrder = avl.preOrder();
        for (Integer v : preOrder) {
            System.out.print(v + " ");
        }

        System.out.println();
        System.out.print("mid order:");
        List<Integer> midOrder = avl.midOrder();
        for (Integer v : midOrder) {
            System.out.print(v + ",");
        }

        System.out.println();
        System.out.print("post order:");
        List<Integer> postOrder = avl.postOrder();
        for (Integer v : postOrder) {
            System.out.print(v + ",");
        }
    }

    @Test
    public void testKthElem() {
        int[] data = {8, 5, 9, 7, 6, 2, 3, 1};
        SimpleAvlTree<Integer> avl = new SimpleAvlTree<>();
        for (int i = 0; i < data.length; i++) {
            avl.add(data[i]);
        }
        System.out.println();
        System.out.print("mid order:");
        List<Integer> midOrder = avl.midOrder();
        for (Integer v : midOrder) {
            System.out.print(v + ",");
        }

        TreeNode<Integer> root = avl.getRoot();
        Stack<TreeNode<Integer>> s = new Stack<>();
        while (root != null) {
            s.push(root);
            root = root.getLeft();
        }
        int n = 0, k = 6;
        TreeNode<Integer> elem = null;
        List<Integer> elemList = new ArrayList<Integer>();
        while (!s.isEmpty()) {
            elem = s.pop();
            elemList.add(elem.getVal());
            n++;
            if (n == k) {
                break;
            }
            TreeNode<Integer> right = elem.getRight();
            if (right != null) {
                n += scan(right, elemList);
                if (n > k) {
                    break;
                }
            }
        }

        System.out.println("elemlist is :" + JSON.toJSONString(elemList));
        System.out.println("kth elem is:" + elemList.get(k - 1));
    }

    private int scan(TreeNode<Integer> head, List<Integer> elemList) {
        if (head == null) {
            return 0;
        }
        int height = 1;
        TreeNode<Integer> l = head.getLeft(), r = head.getRight();
        height += scan(l, elemList);
        elemList.add(head.getVal());
        height += scan(r, elemList);
        return height;
    }
}

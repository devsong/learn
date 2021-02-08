package com.gzs.learn.algorithm.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public abstract class AbstractAvlTree<T extends Comparable<T>> implements IAvlTree<T> {
    /**
     * 根节点
     */
    protected TreeNode<T> root;

    /**
     * 层级
     */
    protected int level;

    /**
     * 平衡因子
     */
    protected int bf;

    /**
     * 树的深度
     */
    protected int deep;

    /**
     * 层序遍历辅助队列
     */
    protected Deque<TreeNode<T>> queue = new ArrayDeque<>();

    /**
     * 查找节点
     *
     * @param node
     * @return
     */
    @Override
    public TreeNode<T> find(TreeNode<T> node) {
        return find0(node, this.root);
    }

    /**
     * 递归查询节点
     *
     * @param node
     * @param root
     * @return
     */
    private TreeNode<T> find0(TreeNode<T> node, TreeNode<T> root) {
        int compareResult = node.getVal().compareTo(root.getVal());
        if (compareResult == 0) {
            return root;
        }
        if (compareResult < 0) {
            TreeNode<T> left = root.getLeft();
            if (left == null) {
                // 未找到
                return null;
            } else {
                return find0(node, left);
            }
        } else {
            TreeNode<T> right = root.getRight();
            if (right == null) {
                // 未找到
                return null;
            } else {
                return find0(node, right);
            }
        }
    }

    /**
     * 左旋
     *
     * @param treeNode
     */
    protected TreeNode<T> LRotate(TreeNode<T> treeNode) {
        TreeNode<T> parent = treeNode.getRight();
        treeNode.setRight(treeNode.getLeft());
        parent.setLeft(treeNode);
        return parent;
    }

    /**
     * 右旋
     *
     * @param treeNode
     */
    protected TreeNode<T> RRotate(TreeNode<T> treeNode) {
        TreeNode<T> parent = treeNode.getLeft();
        treeNode.setLeft(parent.getRight());
        parent.setRight(treeNode);
        return parent;
    }

    /**
     * 二分法构造
     *
     * @param data array for data
     * @param low low pos
     * @param high high pos
     * @param level level
     * @return
     */
    protected TreeNode<T> createFromBinarySearch(T[] data, int low, int high, int level) {
        TreeNode<T> node = null;
        if (low < high) {
            int mid = (low + high) / 2;
            node = new TreeNode<T>(data[mid], level);
            level = level + 1;
            this.level = this.level <= level ? level : this.level;
            node.setLeft(createFromBinarySearch(data, low, mid, level));
            node.setRight(createFromBinarySearch(data, mid + 1, high, level));
        }
        return node;
    }

    @Override
    public List<T> preOrder() {
        List<T> result = new ArrayList<T>();
        innerIterate(root, PRE_ORDER, result);
        return result;
    }

    @Override
    public List<T> midOrder() {
        List<T> result = new ArrayList<T>();
        innerIterate(root, MID_ORDER, result);
        return result;
    }

    @Override
    public List<T> postOrder() {
        List<T> result = new ArrayList<T>();
        innerIterate(root, POST_ORDER, result);
        return result;
    }

    @Override
    public List<T> levelOrder() {
        // 层次遍历
        List<T> data = new ArrayList<>();
        Deque<TreeNode<T>> queue = new ArrayDeque<>();
        if (root == null) {
            return data;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.poll();
            data.add(node.getVal());
            if (node.getLeft() != null) {
                queue.offer(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.offer(node.getRight());
            }
        }
        return data;
    }

    private void innerIterate(TreeNode<T> node, int order, List<T> data) {
        if (node == null) {
            return;
        }
        switch (order) {
            case PRE_ORDER:
                // 先序
                data.add(node.getVal());
                innerIterate(node.getLeft(), order, data);
                innerIterate(node.getRight(), order, data);
                break;
            case MID_ORDER:
                // 中序
                innerIterate(node.getLeft(), order, data);
                data.add(node.getVal());
                innerIterate(node.getRight(), order, data);
                break;
            case POST_ORDER:
                // 后序
                innerIterate(node.getLeft(), order, data);
                innerIterate(node.getRight(), order, data);
                data.add(node.getVal());
                break;
            default:
                break;
        }
    }
}

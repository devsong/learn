package com.gzs.learn.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAvlTree<T extends Comparable<T>> implements IAvlTree<T> {
    /**
     *  根节点
     */
    protected TreeNode<T> root;

    /**
     *  层级
     */
    protected int level;

    /**
     * 查找节点
     * @param node
     * @return
     */
    @Override
    public TreeNode<T> find(TreeNode<T> node) {
        return find0(node, this.root);
    }

    /**
     * 递归查询节点
     * @param node
     * @param root
     * @return
     */
    private TreeNode<T> find0(TreeNode<T> node, TreeNode<T> root) {
        int compareResult = node.getVal().compareTo(root.getVal());
        if (compareResult == 0) {
            return root;
        } else if (compareResult < 0) {
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
     *  左旋
     * @param treeNode
     */
    protected TreeNode<T> LRotate(TreeNode<T> treeNode) {
        TreeNode<T> root = null, left = null, right = null;
        // 根节点
        root = treeNode.getRight();
        // 左子树
        left = treeNode;
        // 右子树
        right = root.getRight();

        left.setLevel(left.getLevel() + 1);
        right.setLevel(right.getLevel() - 1);

        root.setLevel(root.getLevel() - 1);
        root.setLeft(left);
        root.setRight(right);
        return root;
    }

    /**
     *  右旋
     * @param treeNode
     */
    protected TreeNode<T> RRotate(TreeNode<T> treeNode) {
        TreeNode<T> root = null, left = null, right = null;

        root = treeNode.getLeft();
        left = treeNode;
        right = root.getLeft();

        left.setLevel(left.getLevel() + 1);
        right.setLevel(right.getLevel() - 1);

        root.setLevel(root.getLevel() - 1);
        root.setLeft(left);
        root.setRight(right);

        return root;
    }

    /**
     *  左右型
     * @param treeNode
     */
    protected TreeNode<T> LRRotate(TreeNode<T> treeNode) {
        TreeNode<T> root = null, left = null, right = null;

        root = treeNode.getLeft();
        left = treeNode;
        right = root.getLeft();

        left.setLevel(left.getLevel() + 1);
        right.setLevel(right.getLevel() - 1);

        root.setLevel(root.getLevel() - 1);
        root.setLeft(left);
        root.setRight(right);

        return root;
    }

    /**
     *  右左型
     * @param parent
     * @param node
     */
    protected void RLRotate(TreeNode<T> parent, TreeNode<T> node) {
        TreeNode<T> rightLevel1 = parent.getRight();
        TreeNode<T> rightLevel2 = rightLevel1.getLeft();
        node.setLeft(rightLevel2);
        node.setRight(rightLevel1);
        rightLevel1.setLeft(null);
        rightLevel1.setRight(null);
        rightLevel1.setLevel(rightLevel1.getLevel() + 1);
        rightLevel2.setLeft(null);
        rightLevel2.setRight(null);
        rightLevel2.setLevel(rightLevel2.getLevel() + 1);
        parent.setLeft(node);
    }

    /**
     *  二分法构造
     * @param data array for data
     * @param low low pos
     * @param high high pos
     * @param level level
     * @return
     */
    protected TreeNode<T> createFromBinarySearch(T data[], int low, int high, int level) {
        TreeNode<T> node = null;
        if (low < high) {
            int mid = (low + high) / 2;
            node = new TreeNode<>(data[mid], level);
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

    private void innerIterate(TreeNode<T> node, int order, List<T> data) {
        if (node == null) {
            return;
        }
        switch (order) {
        case PRE_ORDER:
            data.add(node.getVal());
            innerIterate(node.getLeft(), order, data);
            innerIterate(node.getRight(), order, data);
            break;
        case MID_ORDER:
            innerIterate(node.getLeft(), order, data);
            data.add(node.getVal());
            innerIterate(node.getRight(), order, data);
            break;
        case POST_ORDER:
            innerIterate(node.getLeft(), order, data);
            innerIterate(node.getRight(), order, data);
            data.add(node.getVal());
            break;
        case LEVEL_ORDER:

        default:
            break;
        }
    }
}

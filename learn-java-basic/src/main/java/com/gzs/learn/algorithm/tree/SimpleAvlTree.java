package com.gzs.learn.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 普通平衡二叉树
 * @author guanzhisong
 *
 * @param <T>
 */
public class SimpleAvlTree<T> implements IAvlTree<T> {
    // 根节点
    private TreeNode<T> root;

    // 层级
    private int level;

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
    public void createTreeNode(T data[], boolean isSort) {
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
    public void add(TreeNode<T> treeNode) {

    }

    /**
     * 删除操作
     * @param treeNode
     */
    public void remove(TreeNode<T> treeNode) {

    }

    public List<T> preOrder() {
        List<T> result = new ArrayList<T>();
        innerIterate(root, PRE_ORDER, result);
        return result;
    }

    public List<T> midOrder() {
        List<T> result = new ArrayList<T>();
        innerIterate(root, MID_ORDER, result);
        return result;
    }

    public List<T> postOrder() {
        List<T> result = new ArrayList<T>();
        innerIterate(root, POST_ORDER, result);
        return result;
    }

    /**
     *  左旋
     * @param treeNode
     */
    private TreeNode<T> LRotate(TreeNode<T> treeNode) {
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
    private TreeNode<T> RRotate(TreeNode<T> treeNode) {
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
    private TreeNode<T> LRRotate(TreeNode<T> treeNode) {
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
    private void RLRotate(TreeNode<T> parent, TreeNode<T> node) {
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
    private TreeNode<T> createFromBinarySearch(T data[], int low, int high, int level) {
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

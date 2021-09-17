package com.gzs.learn.algorithm.tree;

import static com.gzs.learn.algorithm.tree.TreeNode.AVLTreeNode.EH;
import static com.gzs.learn.algorithm.tree.TreeNode.AVLTreeNode.LH;
import static com.gzs.learn.algorithm.tree.TreeNode.AVLTreeNode.RH;
import com.gzs.learn.algorithm.tree.TreeNode.AVLTreeNode;

public class SimpleAvlTree<T extends Comparable<T>> extends AbstractAvlTree<T> {
    private boolean taller = false;

    private AVLTreeNode<T> leftBalance(AVLTreeNode<T> node, AVLTreeNode<T> preNode) {
        AVLTreeNode<T> child = (AVLTreeNode<T>) node.getLeft();
        AVLTreeNode<T> root = null;
        switch (child.balanceFactor) {
            case LH:
                node.balanceFactor = child.balanceFactor = EH;
                root = (AVLTreeNode<T>) RRotate(node);
                if (preNode != null && node.getVal().compareTo(preNode.getVal()) < 0) {
                    preNode.setLeft(root);
                }
                if (preNode != null && node.getVal().compareTo(preNode.getVal()) > 0) {
                    preNode.setRight(root);
                }
                break;
            case RH:
                AVLTreeNode<T> rchild = (AVLTreeNode<T>) child.getRight();
                switch (rchild.balanceFactor) {
                    case EH:
                        node.balanceFactor = child.balanceFactor = EH;
                        break;
                    case LH:
                        node.balanceFactor = RH;
                        child.balanceFactor = EH;
                        break;
                    case RH:
                        node.balanceFactor = EH;
                        child.balanceFactor = LH;
                        break;
                    default:
                        break;
                }
                rchild.balanceFactor = EH;
                node.setLeft(LRotate(child));
                root = (AVLTreeNode<T>) RRotate(node);
                if (preNode != null && node.getVal().compareTo(preNode.getVal()) < 0) {
                    preNode.setLeft(root);
                }
                if (preNode != null && node.getVal().compareTo(preNode.getVal()) > 0) {
                    preNode.setRight(root);
                }
                break;
            default:
                break;
        }
        return root;
    }

    private AVLTreeNode<T> rightBalance(AVLTreeNode<T> node, AVLTreeNode<T> preNode) {
        AVLTreeNode<T> child = (AVLTreeNode<T>) node.getRight();
        AVLTreeNode<T> root = null;
        switch (child.balanceFactor) {
            case RH:
                node.balanceFactor = child.balanceFactor = EH;
                root = (AVLTreeNode<T>) LRotate(node);
                if (preNode != null && node.getVal().compareTo(preNode.getVal()) < 0) {
                    preNode.setLeft(root);
                }
                if (preNode != null && node.getVal().compareTo(preNode.getVal()) > 0) {
                    preNode.setRight(root);
                }
                break;
            case LH:
                AVLTreeNode<T> lchild = (AVLTreeNode<T>) child.getLeft();
                switch (lchild.balanceFactor) {
                    case EH:
                        node.balanceFactor = child.balanceFactor = EH;
                        break;
                    case RH:
                        node.balanceFactor = LH;
                        child.balanceFactor = EH;
                        break;
                    case LH:
                        node.balanceFactor = EH;
                        child.balanceFactor = RH;
                        break;
                    default:
                        break;
                }
                lchild.balanceFactor = EH;
                node.setRight(RRotate(child));
                root = (AVLTreeNode<T>) LRotate(node);
                if (preNode != null && node.getVal().compareTo(preNode.getVal()) < 0) {
                    preNode.setLeft(root);
                }
                if (preNode != null && node.getVal().compareTo(preNode.getVal()) > 0) {
                    preNode.setRight(root);
                }
                break;
            default:
                break;
        }
        return root;
    }

    private boolean insertNode(T value) {
        return insertNode((AVLTreeNode<T>) root, value, null);
    }

    private boolean insertNode(AVLTreeNode<T> node, T value, AVLTreeNode<T> preNode) {
        if (node == null) {
            node = new AVLTreeNode<>(value, 0, EH);
            taller = true;
            if (preNode != null && node.getVal().compareTo(preNode.getVal()) < 0) {
                preNode.setLeft(node);
            }
            if (preNode != null && node.getVal().compareTo(preNode.getVal()) > 0) {
                preNode.setRight(node);
            }
            super.root = node;
            return true;
        }

        if (value == node.getVal()) {
            super.root = node;
            return false;
        }

        if (value.compareTo(node.getVal()) < 0) {
            if (!insertNode((AVLTreeNode<T>) node.getLeft(), value, node)) {
                super.root = node;
                return false;
            }
            if (taller) {
                switch (node.balanceFactor) {
                    case EH:
                        taller = true;
                        node.balanceFactor = LH;
                        break;
                    case RH:
                        taller = false;
                        node.balanceFactor = EH;
                        break;
                    case LH:
                        taller = false;
                        node = leftBalance(node, preNode);
                        if (preNode != null) {
                            node = preNode;
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        if (value.compareTo(node.getVal()) > 0) {
            if (!insertNode((AVLTreeNode<T>) node.getRight(), value, node)) {
                super.root = node;
                return false;
            }
            if (taller) {
                switch (node.balanceFactor) {
                    case EH:
                        taller = true;
                        node.balanceFactor = RH;
                        break;
                    case LH:
                        taller = false;
                        node.balanceFactor = EH;
                        break;
                    case RH:
                        taller = false;
                        node = rightBalance(node, preNode);
                        if (preNode != null) {
                            node = preNode;
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        super.root = node;
        return true;
    }


    @Override
    public int level() {
        return 0;
    }

    @Override
    public void add(TreeNode<T> treeNode) {
        insertNode(treeNode.getVal());
    }

    @Override
    public void add(T val) {
        insertNode(val);
    }

    @Override
    public void remove(TreeNode<T> treeNode) {

    }

    @Override
    public void remove(T val) {

    }

    public TreeNode<T> getRoot() {
        return root;
    }
}

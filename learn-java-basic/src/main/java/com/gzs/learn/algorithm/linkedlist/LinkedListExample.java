package com.gzs.learn.algorithm.linkedlist;

import java.util.Stack;

import org.junit.Test;

/**
 * 链表常用操作
 * @author guanzhisong
 *
 */
public class LinkedListExample {

    @Test
    public void initTest() {
        Node<Integer> head = new Node<>(new Integer[] { 1, 2, 3, 4, 4, 5 });
        Node.print(head);
    }

    /**
     * 递归方式
     */
    @Test
    public void reverse1() {
        Node<Integer> head = new Node<>(new Integer[] { 1, 2, 3, 4, 4, 5 });
        Node.print(head.reverse1(head));
    }

    /**
     * 三指针法
     */
    @Test
    public void reverse2() {
        Node<Integer> head = new Node<>(new Integer[] { 1, 2, 3, 4, 4, 5 });
        Node.print(head.reverse2(head));
    }

    /**
     * 堆栈算法
     */
    @Test
    public void stackSolutionTest() {
        Node<Integer> head = new Node<>(new Integer[] { 1, 2, 3, 4, 4, 5 });
        Stack<Node<Integer>> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        Node<Integer> newHead = stack.pop();
        Node<Integer> pNode = newHead;
        while (!stack.empty()) {
            pNode.next = stack.pop();
            pNode = pNode.next;
        }
        // 断开环路
        pNode.next = null;
        Node.print(newHead);
    }
}

class Node<T> {
    public T data;
    public Node<T> next;

    public Node() {
        this.data = null;
        this.next = null;
    }

    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public Node(T[] arr) {
        Node<T> current = null;
        for (int i = 0; i < arr.length; i++) {
            Node<T> node = new Node<>(arr[i], null);
            if (i == 0) {
                current = this;
                this.data = arr[i];
                this.next = null;
            } else {
                current.next = node;
                current = node;
            }
        }
    }

    // 递归方式
    public Node<T> reverse1(Node<T> head) {
        Node<T> first = head;
        if (first == null || first.next == null) {
            return head;
        } else {
            Node<T> newHead = reverse1(head.next);
            Node<T> next = head.next;
            // 断开环路,重新指向
            next.next = head;
            head.next = null;
            return newHead;
        }
    }

    public Node<T> reverse2(Node<T> head) {
        Node<T> first = head;
        if (first == null || first.next == null) {
            return this;
        }
        Node<T> second = first.next;
        Node<T> third = second.next;
        while(second != null) {
            // 保留后续节点引用
            third = second.next;
            // 设置指向为前置节点引用
            second.next = first;
            // 指针后移
            first = second;
            second = third;
        }
        // 断开头节点环路
        head.next = null;
        return first;
    }

    public static <T> void print(Node<T> head) {
        Node<T> e = head;
        while (e != null) {
            System.out.print(e.data.toString() + " ");
            e = e.next;
        }
    }

    @Override
    public String toString() {
        return this.data + "";
    }
}

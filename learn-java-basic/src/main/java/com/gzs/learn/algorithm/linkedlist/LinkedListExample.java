package com.gzs.learn.algorithm.linkedlist;

import java.util.Stack;

import org.junit.Test;

public class LinkedListExample {

    @Test
    public void initTest() {
        Node<Integer> head = new Node<>(new Integer[] {1, 2, 3, 4, 4, 5});
        Node.print(head);
    }

    /**
     * 三指针法
     */
    @Test
    public void reverseTest() {
        Node<Integer> head = new Node<>(new Integer[] {1, 2, 3, 4, 4, 5});
        Node.print(head.reverseNode());
    }

    /**
     * 堆栈算法
     */
    @Test
    public void stackSolutionTest() {
        Node<Integer> head = new Node<>(new Integer[] {1, 2, 3, 4, 4, 5});
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

    }

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public Node(T[] arr) {
        Node<T> head = null;
        for (int i = 0; i < arr.length; i++) {
            Node<T> node = new Node<>(arr[i], null);
            if (i == 0) {
                head = this;
                this.data = arr[i];
                this.next = null;
            } else {
                head.next = node;
                head = node;
            }
        }
    }

    public Node<T> reverseNode() {
        Node<T> p, q, r;
        p = this;
        q = this.next;
        if (q == null) {
            return p;
        }
        r = q.next;
        if (r == null) {
            q.next = p;
            p.next = null;
            return q;
        }
        p.next = null;
        while (r.next != null) {
            q.next = p;
            p = q;
            q = r;
            r = r.next;
        }
        q.next = p;
        r.next = q;
        return r;
    }

    public static <T> void print(Node<T> head) {
        Node<T> e = head;
        while (e != null) {
            System.out.print(e.data.toString() + " ");
            e = e.next;
        }
    }
}

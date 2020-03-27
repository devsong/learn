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



package com.gzs.learn.algorithm.linkedlist;

public class LinkList<T> {
    private Node<T> head;
    private Node<T> tail;

    public LinkList() {
        this.head = null;
        this.tail = null;
    }

    public LinkList<T> appendNode(Node<T> e) {
        if (head == null) {
            head = tail = e;
            head.index = 1;
            head.next = null;
            head.prev = null;
            return this;
        }
        Node<T> current = this.tail;
        current.next = e;

        e.prev = current;
        e.next = null;
        e.index = current.index + 1;
        this.tail = e;
        return this;
    }

    public void print() {
        Node<T> current = this.head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
    }

    public void reversePrint() {
        Node<T> current = this.tail;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.prev;
        }
    }

    public static void main(String[] args) {
        Node<Integer> node1 = new Node<>(0);
        Node<Integer> node2 = new Node<>(1);
        Node<Integer> node3 = new Node<>(2);

        LinkList<Integer> linkList = new LinkList<Integer>();
        linkList.appendNode(node1).appendNode(node2).appendNode(node3);
        linkList.print();
        linkList.reversePrint();
    }

}

class Node<T> {
    public T data;
    public int index;
    public Node<T> prev;
    public Node<T> next;

    public Node() {
        this.data = null;
        this.index = -1;
        this.prev = null;
        this.next = null;
    }

    public Node(T data) {
        this.data = data;
        this.index = -1;
        this.prev = null;
        this.next = null;
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

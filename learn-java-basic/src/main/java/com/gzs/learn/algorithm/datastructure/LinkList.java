package com.gzs.learn.algorithm.datastructure;

public class LinkList {

    public LinkNode reservse(LinkNode head) {
        return null;
    }

    public void print(LinkNode node) {
        while (node.next != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }
}


class LinkNode {
    int data;
    LinkNode next;
    int len;
}

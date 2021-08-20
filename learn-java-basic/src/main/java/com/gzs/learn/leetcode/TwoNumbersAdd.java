package com.gzs.learn.leetcode;

import lombok.Getter;

public class TwoNumbersAdd {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int i = 0, j = 0;
        while(l1.getNext() != null) {
            int val1 = l1.getVal();
        }
        return null;
    }
}


@Getter
class ListNode {
    private int val;
    private ListNode next;

    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

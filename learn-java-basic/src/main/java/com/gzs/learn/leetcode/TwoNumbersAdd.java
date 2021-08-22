package com.gzs.learn.leetcode;


public class TwoNumbersAdd {
	static int[] firstArr = { 1, 2, 3, 5, 8 };

	static int[] secondArr = { 5, 9, 1, 5, 8, 0, 7 };

	public ListNode addTwoNumbers(ListNode first, ListNode second) {
		// 头指针
		ListNode head = new ListNode(0);
		ListNode p = head;
		boolean hasCarry = false;
		while (first != null || second != null || hasCarry) {
			int result = 0;
			if (hasCarry) {
				// 具备进位
				result += 1;
				// 重置进位
				hasCarry = false;
			}
			if (first != null) {
				result += first.getVal();
				first = first.getNext();
			}
			if (second != null) {
				result += second.getVal();
				second = second.getNext();
			}

			if (result >= 10) {
				result = result % 10;
				hasCarry = true;
			}

			ListNode node = new ListNode(result, null);
			p.setNext(node);
			p = p.getNext();

		}
		return head.getNext();
	}

	public void print(ListNode node) {
		while (node != null) {
			System.out.print(node.getVal() + " ");
			node = node.getNext();
		}
	}

	public ListNode build(int[] arr) {
		ListNode head = new ListNode(arr[0]);
		ListNode p = head;
		for (int i = 1; i < arr.length; i++) {
			p.setNext(new ListNode(arr[i], null));
			p = p.getNext();
		}
		return head;
	}

	public static void main(String[] args) {
		TwoNumbersAdd add = new TwoNumbersAdd();
		ListNode firListNode = add.build(firstArr);
		ListNode secondNode = add.build(secondArr);
		add.print(firListNode);
		System.out.println();
		add.print(secondNode);
		System.out.println();
		add.print(add.addTwoNumbers(firListNode, secondNode));
	}
}

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

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public ListNode getNext() {
		return next;
	}

	public void setNext(ListNode next) {
		this.next = next;
	}

}

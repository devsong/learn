package com.gzs.learn.leetcode.stackqueue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.junit.Test;

public class BracketDecide {
	Map<Character, Character> bracketMap = new HashMap<>();

	public BracketDecide() {
		bracketMap.put('}', '{');
		bracketMap.put(']', '[');
		bracketMap.put(')', '(');
	}

	public boolean isMatch(String str) {
		if (str.length() % 2 != 0) {
			return false;
		}
		Stack<Character> stack = new Stack<>();
		char[] arr = str.toCharArray();
		for (char ch : arr) {
			if (bracketMap.containsKey(ch)) {
				if (stack.isEmpty() || bracketMap.get(ch) != stack.peek()) {
					return false;
				}
				stack.pop();
			} else {
				stack.push(ch);
			}

		}
		return stack.isEmpty();
	}

	@Test
	public void decideBracket() {
		String str = "[[[((}{))]]]";
		BracketDecide bd = new BracketDecide();
		boolean match = bd.isMatch(str);
		System.out.println(match);
	}
}

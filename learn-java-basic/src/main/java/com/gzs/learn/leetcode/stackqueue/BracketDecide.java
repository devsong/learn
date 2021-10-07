package com.gzs.learn.leetcode.stackqueue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.junit.Test;

public class BracketDecide {

    char[] leftBraket = {'{', '[', '('};

    char[] rightBraket = {'}', ']', ')'};

    Map<Character, Character> bracketMap = new HashMap<>();

    public BracketDecide() {
        bracketMap.put('{', '}');
        bracketMap.put('[', ']');
        bracketMap.put('(', ')');
    }

    public boolean isMatch(String str) {
        if (str.length() % 2 != 0) {
            return false;
        }
        Stack<Character> leftStack = new Stack<>();
        Stack<Character> rightStack = new Stack<>();
        char[] arr = str.toCharArray();
        for (char ch : arr) {
            if (isLeftBraket(ch)) {
                leftStack.push(ch);
            } else if (isRightBraket(ch)) {
                rightStack.push(ch);
            }
            if ((!leftStack.isEmpty() && !rightStack.isEmpty())
                    && isMatchBracket(leftStack.peek(), rightStack.peek())) {
                leftStack.pop();
                rightStack.pop();
            }

        }
        return leftStack.isEmpty() && rightStack.isEmpty();
    }

    private boolean isLeftBraket(char c) {
        for (char ch : leftBraket) {
            if (ch == c) {
                return true;
            }
        }
        return false;
    }

    private boolean isRightBraket(char c) {
        for (char ch : rightBraket) {
            if (ch == c) {
                return true;
            }
        }
        return false;
    }

    private boolean isMatchBracket(char c1, char c2) {
        if (bracketMap.get(c1) == c2) {
            return true;
        }
        return false;
    }

    @Test
    public void decideBracket() {
        String str = "[[[(({}))]]]";
        BracketDecide bd = new BracketDecide();
        boolean match = bd.isMatch(str);
        System.out.println(match);
    }
}

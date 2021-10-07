package com.gzs.learn.leetcode.stackqueue;

import java.util.ArrayDeque;
import java.util.Queue;
import org.junit.Test;

public class StackAndQueue {
    int[] elems = {0, 1, 2, 3, 4, 5};

    @Test
    public void testStack() {
        QStack<Integer> s = new QStack<>();
        for (int e : elems) {
            s.push(e);
        }
        while (!s.isEmpty()) {
            System.out.print(s.pop() + " ");
        }
    }
}


class QStack<T> {
    private Queue<T> first;
    private Queue<T> second;

    public QStack() {
        this.first = new ArrayDeque<>();
        this.second = new ArrayDeque<>();
    }

    public T pop() {
        return first.remove();
    }

    public T peek() {
        return first.peek();
    }

    public boolean push(T data) {
        second.clear();
        second.add(data);
        while (!first.isEmpty()) {
            second.add(first.remove());
        }
        first.clear();
        while (!second.isEmpty()) {
            first.add(second.remove());
        }
        return true;
    }

    public int size() {
        return first.size();
    }

    public boolean isEmpty() {
        return first.isEmpty();
    }
}

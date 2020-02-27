package com.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/problems/implement-stack-using-queues/
public class MyStack {
    private Queue<Integer> queue1 = new LinkedList<>();
    private Queue<Integer> queue2 = new LinkedList<>();
    private Queue<Integer> emptyQueue = null;
    private Queue<Integer> nonEmptyQueue = null;

    /** Initialize your data structure here. */
    public MyStack() {
        emptyQueue = queue2;
        nonEmptyQueue = queue1;
    }

    /** Push element x onto stack. */
    public void push(int x) {
        nonEmptyQueue.offer(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int size = nonEmptyQueue.size();

        for (int i = 0 ; i < size - 1 ; i++) {
            emptyQueue.offer(nonEmptyQueue.poll());
        }
        int retVal = nonEmptyQueue.poll();
        Queue<Integer> temp = nonEmptyQueue;
        nonEmptyQueue = emptyQueue;
        emptyQueue = temp;
        return retVal;
    }

    /** Get the top element. */
    public int top() {
        return (Integer)((List)nonEmptyQueue).get(nonEmptyQueue.size()-1);
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return nonEmptyQueue.size() == 0;
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();

        stack.push(1);
        stack.push(2);
        System.out.println(stack.top());   // returns 2
        System.out.println(stack.pop());   // returns 2
        System.out.println(stack.empty()); // returns false
    }
}

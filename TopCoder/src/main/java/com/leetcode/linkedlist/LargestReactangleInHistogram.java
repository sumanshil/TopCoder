package com.leetcode.linkedlist;

import java.util.Stack;

//https://leetcode.com/problems/largest-rectangle-in-histogram/
public class LargestReactangleInHistogram {

    /*
    public int largestRectangleArea(int[] heights) {
        int[] leftSmaller = new int[heights.length];
        int[] rightSmaller = new int[heights.length];
        leftSmaller[0] = 0;
        rightSmaller[rightSmaller.length-1] = rightSmaller.length;

        Stack<Integer> rightStack = new Stack<>();

        for (int i = 0 ; i < heights.length ; i++) {
            while (!rightStack.isEmpty() && heights[rightStack.peek()] > heights[i]) {
                rightSmaller[rightStack.peek()] = i;
                rightStack.pop();
            }
            rightStack.push(i);
        }
        Stack<Integer> leftStack = new Stack<>();

        for (int i = heights.length-1 ; i >= 0 ; i--) {
            while (!leftStack.isEmpty() && heights[leftStack.peek()] > heights[i]) {
                leftSmaller[leftStack.peek()] = i;
                leftStack.pop();
            }
            leftStack.push(i);
        }
        int max = Integer.MIN_VALUE;

        for (int i = 0 ; i < heights.length ; i++) {
            max = Math.max(max, heights[i]* ((rightSmaller[i] - leftSmaller[i])-1));
        }
        return max;
    }
    */
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int max = Integer.MIN_VALUE;
        for (int i = 0 ; i < heights.length ; i++) {
            if (stack.peek() == -1 || heights[stack.peek()] < heights[i]) {
                stack.push(i);
            } else {
                while (stack.peek() != -1 &&  heights[stack.peek()] > heights[i]) {
                    max = Math.max(max, heights[stack.pop()] * ((i - stack.peek())-1));
                }
                stack.push(i);
            }
        }

        while (stack.peek() != -1) {
            max = Math.max(max, heights[stack.pop()] * ((heights.length - stack.peek())-1));
        }
        return max;
    }
    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 6, 2, 3};
        int result = new LargestReactangleInHistogram().largestRectangleArea(arr);
        System.out.println(result);
    }
}

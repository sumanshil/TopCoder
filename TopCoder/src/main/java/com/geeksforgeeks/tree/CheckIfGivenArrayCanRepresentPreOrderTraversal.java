package com.geeksforgeeks.tree;

import java.util.Stack;

/**
 * Created by sshil on 11/5/2015.
 */
//http://www.geeksforgeeks.org/check-if-a-given-array-can-represent-preorder-traversal-of-binary-search-tree/
public class CheckIfGivenArrayCanRepresentPreOrderTraversal {
    public void check(int[] arr){
        //boolean result = checkRecursive(arr, 0, arr.length);
        boolean result = checkUsingStack(arr);
        System.out.println(result);
    }

    private boolean checkUsingStack(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length ; i++) {

            if (arr[i] < root) {
                return  false;
            }

            while(!stack.empty() && stack.peek() < arr[i]){
                root = stack.pop();
            }
            stack.push(arr[i]);
        }
        return true;
    }


    private boolean checkRecursive(int[] arr, int start, int end) {
        if (start == end){
            return true;
        }
        int root = arr[start];
        int nextGreaterElementIndex = getNextGreaterElement(arr,
                                                            root,
                                                            start+1,
                                                            end);
        if (nextGreaterElementIndex == -1){
            return true;
        }
        boolean searchResult = checkIfLesserValueExistsAfterGreaterElement(
                                                 arr,
                                                 root,
                                                 nextGreaterElementIndex,
                                                 end);
        return !searchResult
                && checkRecursive(arr,
                                  start+1,
                                  nextGreaterElementIndex)
                && checkRecursive(arr,
                                  nextGreaterElementIndex,
                                  end);

    }

    private boolean checkIfLesserValueExistsAfterGreaterElement(int[] arr,
                                                                int root,
                                                                int nextGreaterElementIndex,
                                                                int end) {
        for (int i = nextGreaterElementIndex ; i < end ; i++) {
            if (arr[i] < root){
                return true;
            }
        }
        return  false;
    }

    private int getNextGreaterElement(int[] arr,
                                      int root,
                                      int start,
                                      int end) {
        for ( int i = start ; i < end ; i++) {
            if ( arr[i] > root) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {40, 30, 35, 20, 80, 100};
        new CheckIfGivenArrayCanRepresentPreOrderTraversal().check(arr);
    }
}

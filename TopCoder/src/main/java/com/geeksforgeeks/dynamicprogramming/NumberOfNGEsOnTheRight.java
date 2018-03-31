package com.geeksforgeeks.dynamicprogramming;

import java.util.Stack;

public class NumberOfNGEsOnTheRight {

    public void find (int[] arr, int queries[]) {

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int next[] = new int[arr.length];

        for (int i = 1 ; i < arr.length ; i++) {
            while (!stack.isEmpty()){
                int current = stack.peek();
                if (arr[current] < arr[i]) {
                    next[current] = i;
                    stack.pop();
                } else
                    break;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            int i = stack.pop();
            next[i] = -1;
        }

        int dp[] = new int[arr.length];
        dp[arr.length-1] = 0;
        for ( int i = arr.length-2 ; i >= 0 ; i--) {
            if (next[i] == -1){
                dp[i] = 0;
            } else {
                dp[i] = dp[next[i]] + 1;
            }
        }
        for ( int query : queries) {
            System.out.println(dp[query]);
        }

    }

    public static void main(String[] args) {
        int arr[] = { 3, 4, 2, 7, 5, 8, 10, 6};
        int queries[] = {0, 4};
        new NumberOfNGEsOnTheRight().find(arr, queries);
    }

}

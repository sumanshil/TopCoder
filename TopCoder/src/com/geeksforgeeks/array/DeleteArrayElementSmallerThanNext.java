package com.geeksforgeeks.array;

import java.util.Stack;

//https://www.geeksforgeeks.org/delete-array-elements-which-are-smaller-than-next-or-become-smaller/
public class DeleteArrayElementSmallerThanNext {

    public void find (int arr[], int k) {
        Stack<Integer> stack = new Stack<>();
        int count = 0;
        int i;
        for (i = 0 ; i < arr.length ; i++) {
            while (!stack.isEmpty() && stack.peek() < arr[i] && count < k) {
                stack.pop();
                count++;
            }
            stack.push(arr[i]);
            if (count == k) {
                break;
            }
        }
        int result[] = new int[stack.size() + (arr.length-(i+1))];
        int j = stack.size()-1;
        while (!stack.isEmpty()) {
            result[j] = stack.pop();
        }
        i = i+1;
        int l = j+1;
        while (i < arr.length) {
            result[l++] = arr[i++];
        }
        for (Integer ill : result) {
            System.out.println(ill);
        }
    }


    public static void main(String[] args) {
        int arr[] = {23, 45, 11, 77, 18};
        new DeleteArrayElementSmallerThanNext().find(arr, 3);
    }

}

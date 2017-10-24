package com.geeksforgeeks.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//http://www.geeksforgeeks.org/combinational-sum/
public class CombonationalSumIterative {

    private List<Stack<Integer>> result = new ArrayList<>();
    private Stack<Integer> current = new Stack<>();

    public void find(int[] arr, int sum) {
        recursive(arr, sum, 0);
    }

    private void recursive(int[] arr, int sum, int index) {
        if (sum < 0){
            return;
        }
        if (sum == 0){
            result.add(current);
            return;
        }

        while (index < arr.length && sum - arr[index] >= 0){
            current.push(arr[index]);
            recursive(arr, sum - arr[index], index);
            index++;
            current.pop();
        }
    }


    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 8};
        new CombonationalSumIterative().find(arr, 8);
    }

}

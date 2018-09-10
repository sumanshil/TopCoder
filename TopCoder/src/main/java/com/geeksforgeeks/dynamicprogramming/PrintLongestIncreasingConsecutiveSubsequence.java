package com.geeksforgeeks.dynamicprogramming;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//https://www.geeksforgeeks.org/printing-longest-increasing-consecutive-subsequence/
public class PrintLongestIncreasingConsecutiveSubsequence {
    public void calculate(int arr[]) {
        List<Integer> dp = new LinkedList<>();
        dp.set(0, 1);
        int max = Integer.MIN_VALUE;
        for (int i = 1 ; i < arr.length ; i++){
            if (dp.contains(arr[i] - 1)) {
                dp.set(arr[i], dp.get(arr[i] - 1) + 1);
                max = Math.max(max, dp.get(arr[i] - 1) + 1);
            } else {
                dp.set(arr[i], 1);
            }
        }
        System.out.println(max);
    }


    public static void main(String[] args) {
        int arr[] = {3, 10, 3, 11, 4, 5, 6, 7, 8, 12};
        new PrintLongestIncreasingConsecutiveSubsequence().calculate(arr);
    }

}

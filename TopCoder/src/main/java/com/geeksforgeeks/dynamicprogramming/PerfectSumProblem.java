package com.geeksforgeeks.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

//https://www.geeksforgeeks.org/perfect-sum-problem-print-subsets-given-sum/
public class PerfectSumProblem {

    boolean dp[][];
    int arr[];
    public void find (int arr[], int sum) {
        this.arr = arr;
        dp = new boolean[sum + 1][arr.length + 1];
        for ( int i = 0 ; i < dp[0].length ; i++) {
            dp[0][i] = true;
        }

        for ( int i = 1 ; i < dp.length ; i++) {
            dp[i][0] = false;
        }

        for ( int currentSum = 1 ; currentSum < dp.length ; currentSum++) {
            for (int arrIndex = 1 ; arrIndex < dp[0].length ; arrIndex++) {
                if (arr[arrIndex-1] > currentSum) {
                    dp[currentSum][arrIndex] = dp[currentSum][arrIndex-1];
                } else {
                    dp[currentSum][arrIndex] = dp[currentSum][arrIndex-1]
                                               || dp[currentSum-arr[arrIndex-1]][arrIndex-1];
                }
            }
        }

        for ( int i = 0 ; i < dp.length ; i++) {
            System.out.print(i +" ");
            for ( int j = 0 ; j < dp[0].length ; j++) {
                System.out.print(dp[i][j] +" ");
            }
            System.out.println();
        }

        for ( int i = 0 ; i < dp[0].length ; i++) {
            if (dp[dp.length-1][i]) {
                List<Integer> list = new ArrayList<>();
                list.add(arr[i-1]);
                recursive(dp.length-1 - arr[i-1], i-1, sum-arr[i-1], list);
            }
        }



    }

    private void recursive(int row, int col, int sum, List<Integer> list) {
        if (row < 0 || col < 0) {
            return;
        }
        if (sum == 0 && dp[row][col]) {
            // print result;
            list.stream().map(e -> e+"").forEach(System.out::print);
            System.out.println();
            return;
        }

        if (!dp[row][col]) {
            return;
        }

        for (int i = col ; i >= 0 ; i--) {
            if (dp[row][i]) {
                list.add(arr[i-1]);
                recursive(sum-arr[i-1], i-1, sum-arr[i-1], list);
                list.remove(list.size()-1);
            }
        }
    }

    public static void main(String[] args) {
//        int arr[] =  {2, 3, 5, 6, 8, 10};
        int arr[] =  {1 ,4, 2, 3, 5, 6, 8, 10};
        int sum = 10;
        new PerfectSumProblem().find(arr, sum);
    }

}

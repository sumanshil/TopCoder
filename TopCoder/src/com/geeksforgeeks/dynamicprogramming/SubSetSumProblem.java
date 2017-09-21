package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/dynamic-programming-subset-sum-problem/
public class SubSetSumProblem {

    public void find (int[] arr, int sum) {
        boolean result = recursive(arr, arr.length, sum);
        System.out.println(result);
    }

    private boolean recursive(int[] arr, int index, int sum) {
        if (sum == 0) {
            return true;
        }

        if (index == 0 && sum > 0){
            return false;
        }

        if (arr[index-1] > sum) {
            return recursive(arr, index-1, sum);
        }
        return recursive(arr, index-1, sum-arr[index-1])
                || recursive(arr, index-1, sum);
    }


    public static void findDP (int[] arr, int sum){
        int rowLength = arr.length + 1;
        int colLength = sum + 1;
        boolean dp[][] = new boolean[rowLength][colLength];
        dp[0][0] = true;
        for ( int i = 1 ; i < colLength ; i++) {
            dp[0][i] = false;
        }
        for ( int i = 1 ; i < rowLength ; i++) {
            dp[i][0] = true;
        }

        for ( int i = 1 ; i < rowLength ; i++) {
            for ( int j = 1 ; j < colLength ; j++) {
                if (arr[i-1] > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j-arr[i-1]] || dp[i-1][j];
                }
            }
        }
        System.out.println(dp[rowLength-1][colLength-1]);
    }
    public static void main(String[] args) {
        int[] arr = {3, 34, 4, 12, 5, 2};
        int sum = 13;
        new SubSetSumProblem().findDP(arr, sum);
    }
}

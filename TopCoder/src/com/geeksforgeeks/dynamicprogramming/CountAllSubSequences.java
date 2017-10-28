package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/count-subsequences-product-less-k/
public class CountAllSubSequences {

    public void find (int arr[], int k) {
        int result = recursive(arr, 1, k, arr.length);
        System.out.println(result);
    }

    private int recursive(int[] arr, int currentResult, int targetResult, int index) {
        if (index == 0){
            return 0;
        }

        int multiply = currentResult * arr[index-1];
        if ( multiply < targetResult) {
            int r1 = recursive(arr, multiply, targetResult, index-1) + 1;
            int r2 = recursive(arr, currentResult, targetResult, index-1);
            return r1 + r2;
        } else {
            return recursive(arr, currentResult, targetResult, index-1);
        }
    }

    private void dp(int arr[], int k) {
        int column = arr.length + 1;
        int rowNumber = k + 1;
        int dp[][] = new int[rowNumber][column];
        // row number
        // col element
        for ( int i = 0 ; i < rowNumber; i++) {
            dp[i][0] = 0;
        }

        for ( int multiplyNumber = 1 ; multiplyNumber < rowNumber ; multiplyNumber++) {
            for (int arrIndex = 1 ; arrIndex < column ; arrIndex++) {
                // number of subsequence using j-1 terms
                dp[multiplyNumber][arrIndex] = dp[multiplyNumber][arrIndex-1];
                // if arr[j-1] > i it will surely make
                // product greater thus it won't contribute
                // then
                if (arr[arrIndex-1] <= multiplyNumber &&  arr[arrIndex-1] > 0) {
                    dp[multiplyNumber][arrIndex] += dp[multiplyNumber/arr[arrIndex-1]][arrIndex-1] + 1;
                }
            }
        }
        System.out.println(dp[rowNumber-1][column-1]);
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4};
        int result = 10;
        new CountAllSubSequences().dp(arr, result);
    }

}

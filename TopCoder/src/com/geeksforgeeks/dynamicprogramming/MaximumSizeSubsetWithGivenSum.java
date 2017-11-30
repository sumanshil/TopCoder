package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/maximum-size-subset-given-sum/
public class MaximumSizeSubsetWithGivenSum {

    public void find (int set[], int sum) {
        int result = recursive(set, sum, set.length);
        System.out.println(result);
    }

    private int recursive(int[] set, int sum, int index) {
        if (sum == 0){
            return 0;
        }
        if (index == 0 && sum > 0) {
            return 0;
        }

        if (set[index-1] > sum) {
            return recursive(set, sum, index-1);
        }
        int result1 = recursive(set, sum - set[index-1], index-1);
        int result2 = recursive(set, sum, index-1);
        return Math.max(result1 + 1, result2);
    }

    private void dp (int arr[], int sum) {
        int[][] dp = new int[sum + 1][arr.length+1];
        for ( int currentSum = 1 ; currentSum <= sum ; currentSum++) {
            for (int index = 1 ; index <= arr.length ; index++) {
                if (arr[index-1] > currentSum) {
                    dp[currentSum][index] = dp[currentSum][index-1];
                } else {
                    dp[currentSum][index] = Math.max(dp[currentSum-arr[index-1]][index-1] + 1, dp[currentSum][index-1]);
                }
            }
        }
        System.out.println(dp[sum][arr.length]);
    }


    public static void main(String[] args) {
        int arr[] = {2, 3, 5, 7, 10, 15};
        int sum = 10;
        new MaximumSizeSubsetWithGivenSum().dp(arr, sum);
    }

}

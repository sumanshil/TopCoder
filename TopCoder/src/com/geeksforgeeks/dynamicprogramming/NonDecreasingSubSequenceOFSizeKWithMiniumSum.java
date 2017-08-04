package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/non-decreasing-subsequence-of-size-k-with-minimum-sum/
public class NonDecreasingSubSequenceOFSizeKWithMiniumSum {

    public void find(int[] arr, int k){
        int rowLength = arr.length;
        int colLength = k;

        int[][] dp = new int[rowLength][colLength];
        //dp[0][0] = 0;
        for ( int i = 0 ; i < rowLength ; i++){
            dp[i][0] = arr[i];
        }


        for ( int i = 0 ; i < rowLength ; i++) {
            for ( int j = 0 ; j < k ; j++) {
                if ( j == 0){
                    continue;
                }
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for ( int i = 1; i < rowLength ; i++) {
            for (int j = 0 ; j < i ; j ++ ) {
                if (arr[j] <= arr[i]) {
                    for ( int length = 1 ; length < k ; length++) {
                        if (dp[j][length-1] == Integer.MAX_VALUE){
                            continue;
                        }
                        dp[i][length] = Math.min(dp[i][length], dp[j][length-1]+arr[i]);
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0 ; i < rowLength ; i++){
            if (dp[i][k-1] < min ){
                min = dp[i][k-1];
            }
        }

        System.out.println(min);
    }
    public static void main(String[] args) {
        int[] arr = {58, 12, 11, 12, 82, 30, 20, 77, 16, 86};
        int k = 5;
        new NonDecreasingSubSequenceOFSizeKWithMiniumSum().find(arr, k);
    }
}

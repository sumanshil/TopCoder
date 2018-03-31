package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/painters-partition-problem/
public class PaintersPartitionProblem {

    public void find (int arr[], int k) {
        int result = recursive(arr, k, arr.length);
        System.out.println(result);
    }

    private int recursive(int[] arr, int k, int length) {
        // one block
        if (length == 1) {
            return arr[0];
        }
        // one worker
        if ( k == 1){
            return sum(arr, 0, length-1);
        }
        int best = Integer.MAX_VALUE;
        for (int i = 1 ; i <= arr.length ; i++){
           // best = Math.min(best, Math.max(recursive(arr, k-1, i), sum(arr, i, arr.length-1)));
            int result1 = recursive(arr, k-1, i);
            int result2 = sum(arr, i, arr.length-1);
            best = Math.min(best, Math.max(result1, result2));
        }
        return best;
    }

    private void dp(int arr[], int k) {
        int dp[][] = new int[k+1][arr.length+1];

        // for one worker
        for (int i = 0 ; i <= arr.length; i++) {
            dp[1][i] = sum(arr, 0, i-1);
        }

        // one board
        for (int i = 1 ; i <= k ; i++) {
            dp[i][1] = arr[0];
        }

        for (int worker = 2 ; worker <= k ; worker++) {
            for (int partition = 2 ; partition <= arr.length; partition ++) {

                int best = Integer.MAX_VALUE;
                for (int p = 1 ; p <= partition ; p++ ){
                    best = Math.min(best, Math.max(dp[worker-1][p], sum(arr, p, partition-1)));
                }
                dp[worker][partition] = best;
            }
        }
    }

    private int sum(int[] arr, int start, int end) {
        int sum = 0;
        for ( int i = start ; i <= end ; i++) {
            sum += arr[i];
        }
        return sum;
    }


    public static void main(String[] args) {
        int arr[] = { 10, 20, 30, 40 };
        int k = 2;
        new PaintersPartitionProblem().dp(arr, k);
    }

}

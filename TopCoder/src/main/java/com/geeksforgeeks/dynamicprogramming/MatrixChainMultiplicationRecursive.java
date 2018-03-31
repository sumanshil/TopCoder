package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/dynamic-programming-set-8-matrix-chain-multiplication/
public class MatrixChainMultiplicationRecursive {

    public void find (int arr[]){
        int result = recursive(arr, 1, arr.length-1);
        System.out.println(result);
    }

    private int recursive(int[] arr, int low, int high) {
        if (low == high){
            return 0;
        }

        int minResult = Integer.MAX_VALUE;
        for ( int midSection = low  ; midSection < high ; midSection++) {
            int min = recursive(arr,low, midSection)+
                               recursive(arr,midSection+1, high)
                    + arr[low-1]*arr[midSection]*arr[high];
            minResult = Math.min(min, minResult);
        }
        return minResult;

    }

    static int MatrixChainOrder(int p[], int i, int j)
    {
        if (i == j)
            return 0;

        int min = Integer.MAX_VALUE;

        // place parenthesis at different places between first
        // and last matrix, recursively calculate count of
        // multiplications for each parenthesis placement and
        // return the minimum count
        for (int k=i; k<j; k++)
        {
            int count = MatrixChainOrder(p, i, k) +
                        MatrixChainOrder(p, k+1, j) +
                        p[i-1]*p[k]*p[j];

            if (count < min)
                min = count;
        }

        // Return minimum count
        return min;
    }

    public void dp(int[] arr) {
        int dp[][] = new int[arr.length][arr.length];

        for ( int L = 2 ; L < arr.length ; L++) {
            for ( int i = 1 ; i + L - 1 < arr.length ; i++ ){
                 int j = i + L -1;
                 int min = Integer.MAX_VALUE;
                 for ( int k = i ; k < j ; k++) {
                     min = Math.min(min, dp[i][k] + dp[k+1][j]+ arr[i-1]*arr[k]*arr[j]);
                 }
                 dp[i][j] = min;
            }
        }
        System.out.println(dp[1][arr.length-1]);
    }

    public static void main(String[] args) {
        int[] arr = {40, 20, 30, 10, 30};
        new MatrixChainMultiplicationRecursive().dp(arr);
    }

}

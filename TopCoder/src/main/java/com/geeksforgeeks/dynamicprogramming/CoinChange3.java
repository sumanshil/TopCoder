package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
public class CoinChange3 {

    public void count(int[] arr, int n) {
        int result = recursive(arr, arr.length, n);
        System.out.println(result);
    }

    private int recursive(int[] arr, int length, int n) {
        if (n == 0) {
            return 1;
        }

        if (n < 0) {
            return 0;
        }

        if (length <= 0 && n >= 1){
            return 0;
        }
        return recursive(arr, length-1, n)
               + recursive(arr, length, n-arr[length-1]);
    }

    public int dp(int arr[], int n){
        int colLength = arr.length;
        int matrix[][] = new int[n+1][colLength];
        for ( int colIndex = 0 ; colIndex < colLength ; colIndex++) {
            matrix[0][colIndex] = 1;
        }

        for ( int coinSum = 1 ; coinSum <=n ; coinSum++ ) {
            for ( int coinIndex = 0; coinIndex < arr.length ; coinIndex++) {
                int x = ( coinSum - arr[coinIndex]) >= 0 ?  matrix[coinSum-arr[coinIndex]][coinIndex]: 0;
                int y = ( coinIndex >= 1) ? matrix[coinSum][coinIndex-1] : 0;
                matrix[coinSum][coinIndex] = x + y;
            }
        }
        return matrix[n][colLength-1];
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int n = 4;
        int res = new CoinChange3().dp(arr, n);
        System.out.println(res);
    }

}

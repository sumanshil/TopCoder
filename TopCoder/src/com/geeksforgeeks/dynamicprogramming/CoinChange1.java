package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 7/12/2016.
 */
//http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
public class CoinChange1 {
    public static void find(int[] s, int n){
        int result = findDP(s, s.length, n);
        System.out.println(result);
    }

    private static int findRecursive(int[] s, int coinIndex, int sum) {
        if (sum < 0){
            return 0;
        }
        if (sum == 0){
            return 1;
        }
        if (coinIndex <= 0 && sum > 0){
            return 0;
        }
        return findRecursive(s, coinIndex, sum-s[coinIndex])
               +findRecursive(s, coinIndex-1, sum);
    }

    private static int findDP(int[] s, int coinIndex, int sum) {
        int[][] matrix = new int[sum+1][coinIndex];
        for (int i = 0 ; i < coinIndex ; i++){
            matrix[0][i] = 1;
        }

        for ( int i = 1 ; i < sum+1 ; i++ ) {
            for ( int j = 0 ; j < coinIndex ; j++ ) {
                int count1 = (i-s[j] >= 0) ? matrix[i-s[j]][j] : 0;
                int count2 =(j >= 1) ? matrix[i][j-1] : 0;
                matrix[i][j] = count1+ count2;
            }
        }
        return matrix[sum][coinIndex-1];
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3};
        int n = 4;
        CoinChange1.find(arr, n);
    }
}

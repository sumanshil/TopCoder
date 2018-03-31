package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 6/27/17.
 */
//http://www.geeksforgeeks.org/find-maximum-possible-stolen-value-houses/
public class FindMaximumPossibleStolenValues {

    public void find(int[] arr) {
        int r1 = findRecursive(0, 0, arr);
        int r2 = findRecursive(0, 1, arr);
        System.out.println(Math.max(r1, r2));
    }


    public void findDP(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        dp[1] = arr[1];
        dp[2] = arr[2];
        int max = Integer.max(dp[0], Math.max(dp[1], dp[2]));
        for ( int i = 3 ; i < arr.length ; i++){
            dp[i] = arr[i]+Math.max(dp[i-2], dp[i-3]);
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);

    }

    private int findRecursive(int sum, int index, int[] arr) {
        if (index >= arr.length){
            return sum;
        }

        int s1 = findRecursive(sum + arr[index], index + 2, arr);
        int s2 = findRecursive(sum + arr[index], index + 3, arr);
        return  Math.max(s1, s2);
    }

    public static void main(String[] args) {
        int[] hVal = {6, 7, 1, 3, 8, 2, 4};
        new FindMaximumPossibleStolenValues().findDP(hVal);
    }
}

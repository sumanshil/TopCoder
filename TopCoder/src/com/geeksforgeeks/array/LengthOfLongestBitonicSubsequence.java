package com.geeksforgeeks.array;

import java.util.Arrays;

//http://www.geeksforgeeks.org/length-longest-strict-bitonic-subsequence/
public class LengthOfLongestBitonicSubsequence {

    public void find (int arr[]) {
        int lis[] = new int[arr.length];
        int lds[] = new int[arr.length];

        Arrays.fill(lis, Integer.MIN_VALUE);
        Arrays.fill(lds, Integer.MIN_VALUE);

        lis[0]= 1;
        lds[lds.length-1] = 1;
        for ( int i = 1 ; i < arr.length ; i++) {
            for ( int j = 0 ; j < i ; j++) {
                if (arr[j] < arr[i] && (arr[i] - arr[j] == 1)) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
        }

        lds[lds.length-1] = 1;
        for ( int i = lds.length-2 ; i >= 0 ; i--) {
            for ( int j = lds.length -1 ; j >= 0 ; j--) {
                if ( arr[j] < arr[i] && (arr[i] - arr[j]) == 1) {
                    lds[i] = Math.max(lds[i], lds[j] + 1);
                }
            }
        }

        int result = Integer.MIN_VALUE;
        for ( int i = 0 ; i < arr.length; i++) {
            result = Math.max(result, lis[i] + lds[i]);
        }
        System.out.println(result-1);

    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 5, 3, 6, 7, 4, 6, 5};
        new LengthOfLongestBitonicSubsequence().find(arr);
    }

}

package com.geeksforgeeks.dynamicprogramming;

import java.util.Arrays;

//http://www.geeksforgeeks.org/find-longest-bitonic-sequence-increasing-decreasing-parts-two-different-arrays/
public class FindLongestBitonicSubSequenceTwoArrays {

    private void find (int arr1[], int arr2[]) {
        int lis[] = new int[arr1.length];
        int lds[] = new int[arr2.length];

        Arrays.fill(lis, Integer.MIN_VALUE);
        Arrays.fill(lds, Integer.MIN_VALUE);

        lis[0] = 1;
        for ( int i = 1 ; i < arr1.length ; i ++) {
            for ( int j = 0 ; j < i ; j++) {
                if (arr1[j] < arr1[i]){
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
        }

        lds[lds.length-1] = 1;
        for (int i = lds.length - 2 ; i >= 0 ; i--) {
            for (int j = lds.length - 1; j > i ; j--) {
                if (arr2[j] < arr2[i]) {
                    lds[i] = Math.max(lds[i], lds[j] + 1);
                }
            }
        }

        int maxLength = Integer.MIN_VALUE;

        for ( int i = 0 ; i < arr1.length ; i++) {
            for (int j = 0 ; j < arr2.length ; j++) {
                if (arr2[j] < arr1[i]){
                    maxLength = Math.max(maxLength, lis[i] + lds[j]);
                }
            }
        }


        for ( int i = 0 ; i < arr2.length ; i++) {
            for (int j = 0 ; j < arr1.length ; j++) {
                if (arr1[j] < arr2[i]) {
                    maxLength = Math.max(maxLength, lds[j] + lis[i]);
                }
            }
        }
        System.out.println(maxLength);
    }

    public static void main(String[] args) {
        int arr1[] = {1, 5, 2, 4, 3, 5};
        int arr2[] = {8, 6, 4, 7, 3, 2};
        new FindLongestBitonicSubSequenceTwoArrays().find(arr1, arr2);
    }
}

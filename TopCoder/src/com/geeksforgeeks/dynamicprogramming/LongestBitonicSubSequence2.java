package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/dynamic-programming-set-15-longest-bitonic-subsequence/
public class LongestBitonicSubSequence2 {

    public void find (int[] arr) {
        int[] leftArr = new int[arr.length];
        int[] rightArr = new int[arr.length];

        leftArr[0] = 1;
        for (int i = 1 ; i < leftArr.length ; i++) {
            leftArr[i] = 0;
        }

        rightArr[rightArr.length-1] = 1;
        for (int i = rightArr.length-2 ; i >= 0 ; i--) {
            rightArr[i] = 0;
        }

        for (int i = 1 ; i < arr.length ; i++) {
            for ( int j = 0 ; j < i ; j++) {
                if (arr[j] < arr[i]) {
                    leftArr[i] = Math.max(leftArr[i], leftArr[j]+1);
                }
            }
        }

        for ( int i = rightArr.length - 2 ; i >= 0 ; i-- ) {
            for ( int j = rightArr.length-1 ; j > i ; j--) {
                if (arr[i] > arr[j]) {
                    rightArr[i] = Math.max(rightArr[i], rightArr[j]+1);
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0 ; i < arr.length ; i++) {
            max = Math.max(max, leftArr[i] + rightArr[i]);
        }
        System.out.println(max - 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 11, 2, 10, 4, 5, 2, 1};
        new LongestBitonicSubSequence2().find(arr);
    }
}

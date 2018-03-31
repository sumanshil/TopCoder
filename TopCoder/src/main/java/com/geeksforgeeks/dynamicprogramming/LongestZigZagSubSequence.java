package com.geeksforgeeks.dynamicprogramming;

// http://www.geeksforgeeks.org/longest-zig-zag-subsequence/
public class LongestZigZagSubSequence {

    public void find(int arr[]) {
        int lastUpper[] = new int[arr.length];
        int lastLower[] = new int[arr.length];
        lastLower[0] = 1;
        lastUpper[0] = 1;
        for (int i = 1 ; i < arr.length ; i++){
            for ( int j = 0 ; j < i ; j++) {
                if (arr[j] < arr[i]) {
                    lastLower[i] = Math.max(lastLower[i], lastUpper[j] + 1);
                } else if (arr[j] > arr[i]) {
                    lastUpper[i] = Math.max(lastUpper[i], lastLower[j] + 1);
                }
            }
        }

        int maxZigZagSequence = Integer.MIN_VALUE;
        for ( int i = 0 ; i < arr.length ; i++){
            maxZigZagSequence = Math.max(maxZigZagSequence, Math.max(lastLower[i], lastUpper[i]));
        }
        System.out.println(maxZigZagSequence);
    }

    public static void main(String[] args) {
        int arr[] = {4, 3, 3, 1, 3, 3, 1, 3};
        new LongestZigZagSubSequence().find(arr);
    }

}

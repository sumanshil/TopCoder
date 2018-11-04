package com.leetcode.array.leetcode;

//https://leetcode.com/problems/smallest-range-i/description/
public class SmallestRange {
    public int smallestRangeI(int[] A, int K) {
        if (A.length == 1) return 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for ( int i = 0 ; i < A.length ; i++) {
            if (A[i] < min) {
                min = A[i];
            }

            if (A[i] > max) {
                max = A[i];
            }
        }
        int avg = (max + min)/2;
        if (Math.abs(avg - min) <= K && Math.abs(max - avg) <= K) {
            return 0;
        }

        int newMin = min + K;
        int newMax = max - K;
        return Math.abs(newMax - newMin);
    }

    public static void main(String[] args) {
        int[] arr = {10, 7, 1};
        int K = 3;
        int res = new SmallestRange().smallestRangeI(arr, K);
        System.out.println(res);
    }
}

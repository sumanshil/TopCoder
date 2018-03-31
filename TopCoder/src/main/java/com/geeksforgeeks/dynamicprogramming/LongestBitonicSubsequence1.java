package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 1/28/2016.
 */
//http://www.geeksforgeeks.org/dynamic-programming-set-15-longest-bitonic-subsequence/
public class LongestBitonicSubsequence1 {

	public void find(int[] arr) {
		int[] lMaxLength = new int[arr.length];
		int[] rMaxLength = new int[arr.length];

		for (int i = 0 ; i < arr.length ; i++){
			lMaxLength[i] = 1;
			rMaxLength[i] = 1;
		}

		for ( int i = 1; i < arr.length ; i++) {
			for ( int j = 0 ; j < i ; j++) {
				if (arr[j] < arr[i]) {
					if (lMaxLength[j]+1 > lMaxLength[i]){
						lMaxLength[i] = lMaxLength[j]+1;
					}
				}
			}
		}

		for ( int i = arr.length-2 ; i >= 0 ; i--) {
			for ( int j = arr.length-1 ; j > i ; j--) {
				if (arr[j] < arr[i]) {
					if (rMaxLength[j]+1 > rMaxLength[i]) {
						rMaxLength[i] = rMaxLength[j]+1;
					}
				}
			}
		}

		int maxVal = Integer.MIN_VALUE;
		for ( int i = 0 ; i < lMaxLength.length ; i++) {
			if (lMaxLength[i]+rMaxLength[i] > maxVal) {
				maxVal = lMaxLength[i]+rMaxLength[i];
			}
		}

		System.out.println(maxVal);
	}
	public static void main(String[] args) {
		int[] arr = {1, 11, 2, 10, 4, 5, 2, 1};
		new LongestBitonicSubsequence1().find(arr);
	}
}

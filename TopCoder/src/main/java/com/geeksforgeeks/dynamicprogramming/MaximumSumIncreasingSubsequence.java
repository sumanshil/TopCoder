package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 1/28/2016.
 */
//http://www.geeksforgeeks.org/dynamic-programming-set-14-maximum-sum-increasing-subsequence/
public class MaximumSumIncreasingSubsequence {

	public void maxSumIS(int[] arr) {
		int[] maxSumArr = new int[arr.length];
		maxSumArr[0] = arr[0];
		for ( int i = 1 ; i < arr.length ; i++){
			maxSumArr[i] = Integer.MIN_VALUE;
		}

		for ( int i = 1 ; i < arr.length ; i++) {
			for ( int j = 0 ; j < i ; j++) {
				if ( arr[j] < arr[i] ) {
					if (maxSumArr[j]+arr[i] > maxSumArr[i]){
						maxSumArr[i] = maxSumArr[j]+arr[i];
					}
				}
			}
		}

		int maxSoFar = Integer.MIN_VALUE;
		for ( int i = 0 ; i < maxSumArr.length ; i++) {
			if (maxSumArr[i] > maxSoFar) {
				maxSoFar = maxSumArr[i];
			}
		}
		System.out.println(maxSoFar);
	}
	public static void main(String[] args) {
		int[] arr = {1, 101, 2, 3, 100, 4, 5};
		new MaximumSumIncreasingSubsequence().maxSumIS(arr);
	}
}

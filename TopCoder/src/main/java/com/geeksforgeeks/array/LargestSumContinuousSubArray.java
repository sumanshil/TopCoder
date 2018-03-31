package com.geeksforgeeks.array;

/**
 * Created by sshil on 1/9/2016.
 */
public class LargestSumContinuousSubArray {

	public void largestSumContinuousSubArray(int[] arr){
		int maxSumSoFar = Integer.MIN_VALUE;
		int maxEndingHere = 0;
		for ( int i = 0 ; i < arr.length ; i++ ){
			maxEndingHere += arr[i];
			if (maxEndingHere < 0 ){
				maxEndingHere = 0;
			}
			maxSumSoFar = Integer.max(maxEndingHere, maxSumSoFar);
		}
		System.out.println(maxSumSoFar);
	}

	public static void main(String[] args) {
		int[] arr = {-2, -3, 4, -1, -2, 1, 5, -3};
		new LargestSumContinuousSubArray().largestSumContinuousSubArray(arr);
	}
}

package com.geeksforgeeks.array;

/**
 * Created by sshil on 1/9/2016.
 */
public class LongestIncreasingSubsequence {

	public void findLCS(int[] arr){
		int maxValue = findUsingDynamic(arr);
		System.out.println(maxValue);
	}

	private int findUsingDynamic(int[] arr){
		int[] maxArray = new int[arr.length];

		for (int i = 0 ; i < arr.length ; i++){
			maxArray[i] = 1;
		}
		for ( int i = 1 ; i < arr.length ; i++) {
			for ( int j = 0 ; j < i ; j++) {
				if (arr[i] > arr[j]) {
					if (maxArray[j]+1 > maxArray[i]) {
						maxArray[i] = maxArray[j]+1;
					}
				}
			}
		}

		int max = Integer.MIN_VALUE;
		for ( int i = 0 ; i < arr.length ; i++) {
			if (maxArray[i] > max) {
				max = maxArray[i];
			}
		}
		return  max;
	}


	private int findRecursive(int[] arr, int index) {
		if (index == arr.length-1){
			return 1;
		}
		int element = arr[index];
		int maxLength = Integer.MIN_VALUE;
		for ( int i = index+1; i < arr.length ; i++){
			if (arr[i] > element) {
				int result = findRecursive(arr,i);
				if (result+1 > maxLength){
					maxLength = result+1;
				}
			}
		}
		return maxLength;
	}


	public static void main(String[] args) {
		int[] arr = { 10, 22, 9, 33, 21, 50, 41, 60, 80 };
		new LongestIncreasingSubsequence().findLCS(arr);
	}
}

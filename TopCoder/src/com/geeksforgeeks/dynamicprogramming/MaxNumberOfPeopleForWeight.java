package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 2/3/2016.
 */
public class MaxNumberOfPeopleForWeight {
	public void find(int[] weightArr, int weight){
		//int result = countRecursive(weightArr, weight, 0, 0);
		int result = countDynamic(weightArr, weight);
		System.out.println(result);
	}

	private int countDynamic(int[] weightArr, int weight){
		int rowLength = weightArr.length+1;
		int colLength = weight+1;
		int[][] matrix = new int[rowLength][colLength];
		matrix[0][0] = 0;
		// matrix[i][j] contains maximum number of elements that contributes to weight j
		for ( int i = 1 ; i < colLength; i++) {
			matrix[0][i] = 0;
		}

		for (int i = 1; i < rowLength ; i++){
			matrix[i][0] = 0;
		}

		for ( int i = 1 ; i < rowLength ; i++) {// weightArr
			for ( int j = 1; j < colLength ; j++){ // weight
				int currentWeight = weightArr[i-1];
				if (currentWeight > j) {
					matrix[i][j] = matrix[i-1][j];
				} else {
					matrix[i][j] = Math.max(matrix[i-1][j], matrix[i-1][j-currentWeight]+1);
				}
			}
		}
		return matrix[rowLength-1][colLength-1];
	}

	private int countRecursive(int[] weightArr, int weight, int currentCount, int currentIndex) {
		if (weight  == 0){
			return currentCount;
		}
		if (currentIndex == weightArr.length) {
			return Integer.MIN_VALUE;
		}

		if (weightArr[currentIndex] > weight ) {
			// this is heavier. don't consider this.
			return countRecursive(weightArr, weight, currentCount, currentIndex+1);
		} else {
			int with = countRecursive(weightArr, weight-weightArr[currentIndex], currentCount+1, currentIndex+1);
			int without = countRecursive(weightArr, weight, currentCount, currentIndex+1);
			return Math.max(with, without);
		}
	}


	public static void main(String[] args) {
		int weight = 8;
		int[] arr = {1,1,2,1,5};
		new MaxNumberOfPeopleForWeight().find(arr, weight);
//		String s = "3";
//		char[] arr = s.toCharArray();
//		char i = arr[0];
//		System.out.println(i);
	}
}

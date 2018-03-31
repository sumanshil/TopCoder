package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 2/2/2016.
 */
//http://www.geeksforgeeks.org/dynamic-programming-set-18-partition-problem/
public class PartitionProblem {

	public void findPartition(int[] arr){
		int sum = 0;
		for ( int i = 0 ; i < arr.length ; i++){
			sum += arr[i];
		}
		if (sum % 2 != 0 ){
			System.out.println("Partition is possible");
			return;
		}

		boolean result = findDynamic(arr, sum/2);  //findRecursive(arr, 0, sum/2, 0);
		System.out.println(result);
	}

	private boolean findDynamic(int[] arr, int sum){
		int rowLength = arr.length+1;
		int colLength = sum+1;
		boolean[][] matrix = new boolean[rowLength][colLength];
		matrix[0][0] = true;
		for ( int i = 1 ; i < colLength ; i++){
			matrix[0][i] = false;
		}

		for ( int i = 1 ; i < rowLength ; i++){
			matrix[i][0] = true;
		}

		for ( int i = 1 ; i < rowLength ; i++) {
			for ( int j = 1 ; j < colLength ; j++) {
				int sumPartial = j;
				int elementIndex = i-1;
				int element = arr[elementIndex];
				if (element <= sumPartial) {
					if (matrix[i-1][sumPartial-element]){
						matrix[i][sumPartial] = true;
					} else if (matrix[i-1][sumPartial]) {
						matrix[i][sumPartial] = true;
					} else {
						matrix[i][sumPartial] = false;
					}
				} else {
					matrix[i][j] = matrix[i-1][sumPartial];
				}

			}
		}

		return matrix[rowLength-1][colLength-1];

	}
	private boolean findRecursive(int[] arr,
								  int currentIndex,
								  int targetSum,
								  int currentSum) {
		if (currentSum == targetSum){
			return true;
		}
		if (currentIndex == arr.length){
			return false;
		}
		if (currentSum + arr[currentIndex] > targetSum){
			// don't consider this element
			return findRecursive(arr, currentIndex+1, targetSum, currentSum);
		} else {
			// don't consider this element
			if (findRecursive(arr, currentIndex+1, targetSum, currentSum)){
				return true;
			} else {
				// consider this element
				return findRecursive(arr, currentIndex + 1, targetSum, currentSum + arr[currentIndex]);
			}
		}
	}

	public static void main(String[] args) {
		int[] arr = {1, 5, 11, 5};
		new PartitionProblem().findPartition(arr);
	}
}

package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 1/23/2016.
 */
//http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
public class KnapsackProblem1 {

	public void calculate(int[] weight, int[] arr, int totalWeight){
		//calculateRecursive(weight, arr, totalWeight, 0, 0);
		calculateDynamic(weight, arr, totalWeight);
		//System.out.println(maxValue);
	}

	private int maxValue = Integer.MIN_VALUE;

	private void calculateDynamic(int[] weight, int[] arr, int totalWeight) {
		int colLength = totalWeight+1;
		int rowLength  = arr.length+1;
		int[][] matrix = new int[rowLength][colLength];

		for ( int i = 0 ; i < rowLength ; i++ ) {
			for ( int j = 0 ; j < colLength; j++ ) {
				if (i == 0 || j == 0) {
					matrix[i][j] = 0;
				} else {
					if ( weight[i-1] <= j) {
						matrix[i][j] = Math.max(matrix[i-1][j], matrix[i-1][j-weight[i-1]]+arr[i-1]);
					} else {
						matrix[i][j] = matrix[i-1][j];
					}
				}
			}
		}
		System.out.println(matrix[rowLength-1][colLength-1]);
	}

	private void calculateRecursive(int[] weight,
									int[] arr,
									int totalWeight,
									int currentValue,
									int currentIndex) {
		if (totalWeight < 0){
			return;
		}
		if (currentIndex == arr.length) {
			if( currentValue > maxValue) {
				maxValue = currentValue;
			}
			return;
		}

		if (weight[currentIndex] > totalWeight){
			//don't consider this element
			calculateRecursive(weight, arr, totalWeight, currentValue, currentIndex+1);
		} else {
			//don't consider
			calculateRecursive(weight, arr, totalWeight, currentValue, currentIndex+1);
			// consider
			calculateRecursive(weight, arr, totalWeight-weight[currentIndex], currentValue+arr[currentIndex], currentIndex+1);
		}

	}

	public static void main(String[] args) {
		int[] val = {60, 100, 120};
		int[] weight = {10, 20, 30};
		int maxWeight = 50;
		new KnapsackProblem1().calculate(weight, val, maxWeight);

	}
}

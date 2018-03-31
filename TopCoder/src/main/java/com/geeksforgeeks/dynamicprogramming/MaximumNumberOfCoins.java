package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 1/18/2016.
 */
public class MaximumNumberOfCoins {

	public void maxCountOfCoins(int[] arr, int sum) {
		//int result = maxCountRecursive(arr, sum, 0, 0);
		dynamicSolution2(arr, sum);
		//System.out.println(result);
	}

	private int maxCountDynamic(int[] arr, int sum) {
		int elementCount = arr.length;
		int[][] matrix = new int[elementCount+1][sum+1];
		int rowLength = matrix.length;
		int colLength = matrix[0].length;
		for ( int i = 0 ; i < rowLength ; i++) {
			matrix[i][0] = 0;
		}

		for ( int i = 0 ; i < colLength ; i++){
			matrix[0][i] = 0;
		}

		for ( int i = 1 ; i < rowLength ; i++) {
			for ( int currentSum = 1; currentSum < colLength ; currentSum++) {
				if ( arr[i-1] <= currentSum ) {
					matrix[i][currentSum] = Math.max(matrix[i-1][currentSum-arr[i-1]]+1,
													 matrix[i-1][currentSum]);
				} else {
					matrix[i][currentSum] = matrix[i-1][currentSum];
				}
			}
		}

		return matrix[rowLength-1][colLength-1];
	}


	public void dynamicSolution2(int[] arr, int sum) {
		int[] table = new int[sum+1];
		table[0] = 0;
		for ( int i = 1 ; i < table.length ; i++){
			table[i] = Integer.MIN_VALUE;
		}
		for ( int i = 1 ; i < table.length ; i++ ) {
			for ( int j = 0 ; j < arr.length ; j++) {
				if (arr[j] <= i) {
					int number1 = arr[j];
					int number2 = i - arr[j];
					if (table[i] <= Math.max(table[number1],1+ table[number2])){
						table[i] = Math.max(table[number1],1+ table[number2]);
					}
				}
			}
		}
		System.out.println(table[table.length-1]);
	}




	private int maxCountRecursive(int[] arr, int sum, int index, int count) {
		if (index >= arr.length){
			return count;
		}
		if (sum <= 0){
			return count;
		}

		int numberOfCoinsWithoutThisCoin = maxCountRecursive(arr, sum, index+1, count);
		int numberOfCoinsWithThisCoin = 0;
		if (sum > arr[index]) {
			numberOfCoinsWithThisCoin = maxCountRecursive(arr, sum - arr[index], index + 1, count + 1);
		}
		return Math.max(numberOfCoinsWithoutThisCoin, numberOfCoinsWithThisCoin);
	}

	public static void main(String[] args) {
		int[] arr = {1,3,4,5};
		new MaximumNumberOfCoins().maxCountOfCoins(arr, 5);
	}
}

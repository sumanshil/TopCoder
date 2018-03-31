package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 1/22/2016.
 */
public class MatrixChainMultiplication {

	public int calculateMin(int[] arr) {
		int result = calculateDynamic(arr);//calculateRecursive(arr,1 , arr.length-1);
		return result;
	}

	private int calculateRecursive(int[] arr, int start, int end) {
		if (start == end) {
			return 0;
		}
		int result = Integer.MAX_VALUE;
		for( int i = start ; i < end ; i++) {
			int count = calculateRecursive(arr,start, i)+calculateRecursive(arr,i+1, end)+
				arr[start-1]*arr[i]*arr[end];
			if (count < result){
				result = count;
			}
		}
		return result;
	}


	private int calculateDynamic(int[] arr) {
		int rowLength = arr.length;
		int colLength = arr.length;

		int[][] matrix = new int[rowLength+1][colLength+1];
		int arrLength = arr.length;
		for ( int L = 2; L < arrLength ; L++) {
			for (int i = 1 ; i < arrLength-L+1 ; i++) {
				int j = i+L-1;
				int min = Integer.MAX_VALUE;
				for (int k = i ; k < j ; k++){
					int current = matrix[i][k]+matrix[k+1][j]+(arr[i-1]*arr[k]*arr[j]);
					if (current < min) {
						min = current;
					}
				}
				matrix[i][j] = min;
			}
		}
		return matrix[1][arrLength-1];
	}

	public static void main(String[] args) {
		int[] arr = {40, 20, 30, 10, 30};
		int result = new MatrixChainMultiplication().calculateMin(arr);
		System.out.println(result);
	}
}

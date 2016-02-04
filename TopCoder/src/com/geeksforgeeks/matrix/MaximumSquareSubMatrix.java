package com.geeksforgeeks.matrix;

/**
 * Created by sshil on 1/9/2016.
 */
//http://www.geeksforgeeks.org/maximum-size-sub-matrix-with-all-1s-in-a-binary-matrix/
public class MaximumSquareSubMatrix {

	public void calculate(int[][] matrix){
		int rowLength = matrix.length;
		int colLength = matrix[0].length;
		int[][] maxMatrixSize = new int[rowLength][colLength];

		for ( int i = 0 ; i < colLength ; i++){
			maxMatrixSize[0][i] = 0;
		}

		for ( int i = 0 ;i < rowLength ; i++){
			maxMatrixSize[i][0] = 0;
		}

		for ( int i = 1 ; i < rowLength; i++) {
			for ( int j = 1 ; j < colLength ; j++) {
				int min = Math.min(matrix[i][j-1],Math.min(matrix[i-1][j-1], matrix[i-1][j]));
				if (min != 0){
					maxMatrixSize[i][j] = maxMatrixSize[i-1][j-1]+1;
				}
			}
		}

		int maxValue = Integer.MIN_VALUE;
		for ( int i = 1 ; i < rowLength ; i++){
			for ( int j = 1; j < colLength ; j++) {
				maxValue = Math.max(maxMatrixSize[i][j], maxValue);
			}
		}
		System.out.println(maxMatrixSize);
	}
	public static void main(String[] args) {

	}
}

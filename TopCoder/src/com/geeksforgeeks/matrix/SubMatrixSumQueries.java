package com.geeksforgeeks.matrix;

/**
 * Created by sshil on 1/31/2016.
 */
//
public class SubMatrixSumQueries {

	int[][] matrix = {
		{1, 2, 3, 4, 6},
		{5, 3, 8, 1, 2},
		{4, 6, 7, 5, 5},
		{2, 4, 8, 9, 4} };

	public void calculateSum(int x1, int y1, int x2, int y2) {
		int[][] subMatrixSumMatrix = new int[matrix.length][matrix[0].length];
		subMatrixSumMatrix[0][0] = matrix[0][0];
		int rowLength = matrix.length;
		int colLength = matrix[0].length;
		for (int i = 1 ; i < colLength; i++ ) {
			subMatrixSumMatrix[0][i] = matrix[0][i] + subMatrixSumMatrix[0][i-1];
		}

		for ( int i = 1 ; i < rowLength ; i++) {
			subMatrixSumMatrix[i][0] = subMatrixSumMatrix[i-1][0] + matrix[i][0];
		}

		for (int i = 1 ; i < rowLength ; i++) {
			for (int j = 1 ; j < colLength ; j++){
					int sumOfSubMatrix =  (subMatrixSumMatrix[i][j-1]+ subMatrixSumMatrix[i-1][j])
						                   - (2* subMatrixSumMatrix[i-1][j-1]);
				subMatrixSumMatrix[i][j] = sumOfSubMatrix + matrix[i][j];
			}
		}

		int x0 = x1;
		int y0 = y2;

		int x3 = x2;
		int y3 = y1;

		int result = subMatrixSumMatrix[x1][y1] - (subMatrixSumMatrix[x0][y0]+subMatrixSumMatrix[x3][y3]) + subMatrixSumMatrix[x1][y1];
		System.out.println(result);



	}
	public static void main(String[] args) {
		new SubMatrixSumQueries().calculateSum(2, 3, 1, 1);
	}
}

package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 1/23/2016.
 */
//http://www.geeksforgeeks.org/dynamic-programming-set-9-binomial-coefficient/
public class BinominalCoefficient {
    public int calculate( int n , int k){
		int result = calculateRecursive(n, k);
		return result;
	}

	private int calculateRecursive( int nCurrent, int kCurrent) {
		if ((nCurrent == kCurrent) || (kCurrent == 0)){
			return 1;
		}
		return calculateRecursive( nCurrent-1, kCurrent-1)
			+ calculateRecursive(nCurrent-1, kCurrent);
	}

	private int calculateDynamic(int n, int k) {
		int rowLength = n+1;
		int colLength = k+1;
		int[][] matrix = new int[rowLength][colLength];

		for ( int i = 0 ; i < rowLength ; i++) {
			for ( int j = 0 ; j < Math.min(i, k) ; j++) {
				if (i == j || j == 0){
					matrix[i][j] = 1;
				} else {
					matrix[i][j] = matrix[i-1][j-1]+matrix[i-1][j];
				}
			}
		}
		return matrix[rowLength][colLength];
	}

	public static void main(String[] args) {
		int n = 5;
		int k = 2;
		int result = new BinominalCoefficient().calculate(n, k);
		System.out.println(result);
	}
}

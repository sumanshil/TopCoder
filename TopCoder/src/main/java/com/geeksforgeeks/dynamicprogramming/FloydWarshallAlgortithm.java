package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 1/29/2016.
 */
//http://www.geeksforgeeks.org/dynamic-programming-set-16-floyd-warshall-algorithm/
public class FloydWarshallAlgortithm {


	private int[][] matrix ={
		{0, 5, Integer.MAX_VALUE, 10},
		{Integer.MAX_VALUE, 0, 3, Integer.MAX_VALUE},
		{Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 1},
		{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,0}
	};

	public void calculate(){
		int rowLength = matrix.length;
		int colLength = matrix[0].length;

		for (int i = 0 ; i < rowLength ;i++) {
			for ( int j = 0 ; j < colLength; j++) {
				if ( i == j) {
					matrix[i][j] = 0;
				} else {
					for ( int k = i ; k < j ; k++) {
						if (matrix[i][k] != Integer.MAX_VALUE && matrix[k][j] != Integer.MAX_VALUE) {
							if (matrix[i][j] > matrix[i][k]+ matrix[k][j]){
								matrix[i][j] = matrix[i][k]+matrix[k][j];
							}
						}
					}
				}
			}
		}

		for( int i = 0 ; i < rowLength ; i++) {
			for ( int j = 0; j < colLength ; j++) {
				if (matrix[i][j] == Integer.MAX_VALUE){
					System.out.print("INF ");
				} else {
					System.out.print(matrix[i][j] + " ");
				}
			}
			System.out.println();
		}



	}
	public static void main(String[] args) {
		new FloydWarshallAlgortithm().calculate();
	}

}

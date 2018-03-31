package com.geeksforgeeks.dynamicprogramming;

public class DiceThrow {
	// The main function that returns number of ways to get sum 'x'
	// with 'n' dice and 'm' with m faces.
	
	public static int findWays(int m, int n, int x){
		
		int[][] table = new int[n+1][x+1];
		
		for(int i = 1; i <=x&&i <=m; i++){
			table[1][i] = 1;
		}
		// Fill rest of the entries in table using recursive relation
	    // i: number of dice, j: sum
		
		for(int i = 2 ; i <= n; i++){
			for(int j = 1; j <=x; j++){
				for(int k = 1; k <=m && k <j; k++){
					table[i][j] += table[i-1][j-k];
				}
			}
		}
		
		System.out.println(table[n][x]);
		return table[n][x];
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//System.out.println(findWays(4, 2, 1));
		//System.out.println(findWays(2, 2, 3));
		System.out.println(findWays(6, 3, 8));

	}

}

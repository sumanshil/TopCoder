package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 1/23/2016.
 */
//http://www.geeksforgeeks.org/dynamic-programming-set-11-egg-dropping-puzzle/
public class EggDropPuzzle {

	/**
	 * Find the minimum number of trials needed to inOrder optimal floor
	 * Case 1 : The egg breaks at nth floor. so we must go down one floor with k-1 eggs
	 * Case 2 : The eggs does not break. We must go up with k eggs.
	 * @param numberOfFloors
	 * @param numberOfEggs
	 */
	public void calculate(int numberOfFloors, int numberOfEggs){
		//int result = calculateRecursive(numberOfFloors, numberOfEggs);
		int result = calculateDynamic(numberOfFloors, numberOfEggs);
		System.out.println(result);
	}

	private int calculateDynamic(int numberOfFloors, int numberOfEggs) {
		int eggs = numberOfEggs+1;
		int floors = numberOfFloors+1;
		int[][] matrix = new int[eggs][floors];
		// row eggs
		// col floors
		for ( int i = 1 ; i < eggs ; i++) {
			matrix[i][0] = 1;
			matrix[i][1] = 1;
		}

		for ( int i = 1 ; i < floors ; i++){
			matrix[1][i] = i;
		}

		for ( int i = 2 ; i < eggs ; i++ ) {
			for ( int j = 2 ; j < floors ; j++ ) {
				int result = Integer.MAX_VALUE;
				for ( int k = 1; k <= j ; k++ ) {
					int current = Math.max(matrix[i-1][k-1], matrix[i][j-k]);
					if (current < result){
						result = current;
					}
				}
				matrix[i][j] = result+1;
			}
		}

		return matrix[eggs-1][floors-1];
	}

	private int calculateRecursive(int numberOfFloors, int numberOfEggs) {
		if (numberOfFloors == 1 || numberOfFloors == 0) {
			return numberOfFloors;
		}
		if (numberOfEggs == 1){
			return numberOfFloors;
		}

		int minCount = Integer.MAX_VALUE;
		// consider all
		for ( int k = 1 ; k <= numberOfFloors ; k++){
			int result = Math.max(calculateRecursive(k-1, numberOfEggs-1),// egg drops
					              calculateRecursive(numberOfFloors-k,numberOfEggs)); // egg does not drop
			if (result < minCount ){
				minCount = result;
			}
		}
		return minCount+1;
	}

	public static void main(String[] args) {
		int numberOfEggs = 2;
		int numberOfFloors = 10;
		new EggDropPuzzle().calculate(numberOfFloors, numberOfEggs);
	}
}

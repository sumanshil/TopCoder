package com.geeksforgeeks.matrix;

/**
 * Created by sshil on 3/23/2016.
 */
public class MatrixRotation {
	static int[][] matrix = {{1,1},
						     {1,1}};

	private static final int[] xDiff = {0, 1, 0, -1};
	private static final int[] yDiff = {1, 0,-1,  0};
	private static int INDEX = 0;
	static boolean[][] visited = null;
	public static void rotate(int numberOfRotation){
		for ( int i = 0 ; i < numberOfRotation ; i++) {
			visited = new boolean[matrix.length][matrix[0].length];
			int currentX = 0;
			int currentY = 0;
			INDEX = 0;
			while(true){
				visited[currentX][currentY] = true;
				int nextPosX = currentX + xDiff[INDEX];
				int nextPosY = currentY + yDiff[INDEX];
				if (isValidPosition(nextPosX, nextPosY)){
					swapPosition(currentX, currentY, nextPosX, nextPosY);
					currentX = nextPosX ;
					currentY = nextPosY ;
				} else {
					INDEX = (INDEX+1)%4;
					int nextPosXTemp = currentX + xDiff[INDEX];
					int nextPosYTemp = currentY + yDiff[INDEX];
					if (isValidPosition(nextPosXTemp, nextPosYTemp)){
						if (INDEX == 0){
							// start over
							System.out.println();
							printMatrix();
							currentX = nextPosXTemp;
							currentY = nextPosYTemp;
						} else {

						}
					} else {
						break;
					}
				}
			}
			System.out.println();
			printMatrix();
		}
	}

	private static void printMatrix() {
		for ( int j = 0 ; j < matrix.length ; j++){
			for ( int k = 0 ; k < matrix[0].length ; k++){
				System.out.print(matrix[j][k]+" ");
			}
			System.out.println();
		}
	}

	private static void swapPosition(int currentX, int currentY, int nextPosX, int nextPosY) {
		int tempX = matrix[currentX][currentY];
		matrix[currentX][currentY] = matrix[nextPosX][nextPosY];
		matrix[nextPosX][nextPosY] = tempX;

	}

	private static boolean isValidPosition(int nextPosX, int nextPosY) {
		return nextPosX >= 0 && nextPosX < matrix.length
			   && nextPosY >= 0 && nextPosY < matrix[0].length
			   && !visited[nextPosX][nextPosY];
	}

	public static void main(String[] args) {
		MatrixRotation.rotate(3);
		System.out.println();
		printMatrix();
	}
}

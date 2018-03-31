package com.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by sshil on 3/27/2016.
 */
public class PrintMatrixConcurrently {

	private static volatile int[][] matrix = null;
	private static int ROWS = 5;
	private static int COLUMNS = 4;
	private static int ROTATIONS = 7;
	private static int[] xDiff = {0,   1,  0, -1};
	private static  int[] yDiff = {1,  0, -1,  0};

	static class MatrixRotator implements Runnable {
		int startX = 0;
		int startY = 0;

		MatrixRotator(int startX, int startY){
			this.startX = startX;
			this.startY = startY;
		}

		@Override
		public void run() {
			int maxRow = ROWS - startX;
			int maxCol = COLUMNS - startY;
			int diffIndex = 0;
			int currentX = startX;
			int currentY = startY;
			while(true){
				int nextX = currentX + xDiff[diffIndex];
				int nextY = currentY + yDiff[diffIndex];
				if (isValid(nextX, nextY, maxRow, maxCol)){
					if (nextX == startX && nextY == startY){
						break;
					} else {
						swap(currentX, currentY, nextX, nextY);
						currentX = nextX;
						currentY = nextY;
					}
				} else {
					diffIndex = (diffIndex+1)%4;
					int nextXTemp = currentX + xDiff[diffIndex];
					int nextYTemp = currentY + yDiff[diffIndex];
					if (!isValid(nextXTemp, nextYTemp, maxRow, maxCol)){
						break;
					}
				}
			}

		}

		private void swap(int currentX, int currentY, int nextX, int nextY) {
			int temp = PrintMatrixConcurrently.matrix[currentX][currentY];
			PrintMatrixConcurrently.matrix[currentX][currentY] = PrintMatrixConcurrently.matrix[nextX][nextY];
			PrintMatrixConcurrently.matrix[nextX][nextY] = temp;		}

		private boolean isValid(int nextX, int nextY, int maxRow, int maxCol) {
			return nextX >= startX && nextX < maxRow && nextY >= startY && nextY < maxCol;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(1000);
		int[][] matrix = {{1, 2, 3, 4},
						  {7, 8, 9, 10},
						  {13, 14, 15, 16},
						  {19, 20, 21, 22},
						  {25, 26, 27, 28}};
		PrintMatrixConcurrently.matrix = matrix;
		for ( int i = 0 ; i < ROTATIONS ; i++) {
			int startX = 0;
			int startY = 0;
			for (int j = 0; j < ROWS / 2; j++) {
				MatrixRotator matrixRotator = new MatrixRotator(startX, startY);
				startX = startX + 1;
				startY = startY + 1;
				executorService.submit(matrixRotator);
			}
			System.out.println("After rotation "+i);
			printMatrix();
		}
		executorService.shutdown();
		executorService.awaitTermination(100000, TimeUnit.MILLISECONDS);
		System.out.println("Final====");
		printMatrix();
	}
//	 1   2  3  4  5
//	 6   7  8  9 10
//	 11 12 13 14 15
//	 16 17 18 19 20
//	 21 22 23 24 25

	private static void printMatrix() {
		for ( int  i  = 0 ; i < ROWS ; i++){
			for ( int j = 0 ; j < COLUMNS ; j++) {
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
	}


}

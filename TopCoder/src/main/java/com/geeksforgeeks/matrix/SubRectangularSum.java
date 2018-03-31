package com.geeksforgeeks.matrix;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sshil on 12/21/2015.
 */
public class SubRectangularSum {

	static class RectangularSumHolder {
		private int leftHorizontalSum;
		private int upVerticalSum;
		private int diagonalSum;

		public RectangularSumHolder(int leftHorizontalSum, int upVerticalSum, int diagonalSum){
			this.upVerticalSum = upVerticalSum;
			this.leftHorizontalSum = leftHorizontalSum;
			this.diagonalSum = diagonalSum;
		}

		public RectangularSumHolder() {

		}

		public int getLeftHorizontalSum() {
			return leftHorizontalSum;
		}

		public void setLeftHorizontalSum(int leftHorizontalSum) {
			this.leftHorizontalSum = leftHorizontalSum;
		}

		public int getUpVerticalSum() {
			return upVerticalSum;
		}

		public void setUpVerticalSum(int upVerticalSum) {
			this.upVerticalSum = upVerticalSum;
		}

		public int getDiagonalSum() {
			return diagonalSum;
		}

		public void setDiagonalSum(int diagonalSum) {
			this.diagonalSum = diagonalSum;
		}
	}

	static class MapKey {
		private int x;
		private int y;
		public  MapKey(int x, int y){
			this.x = x;
			this.y = y;
		}

		public int hashCode(){
			return this.x* 17+ this.y;
		}

		public boolean equals(Object object){
			MapKey mapKey = (MapKey)object;
			return  mapKey.x == this.x && mapKey.y == this.y;
		}
	}
	private int[][] matrix = null;
	private int rowLength = 0;
	private int colLength = 0;

	Map<MapKey, RectangularSumHolder> map = new HashMap<>();

	public void calculateMatrix(int[][] matrix){
		this.matrix = matrix;
		this.rowLength = matrix.length;
		this.colLength = matrix[0].length;

		for ( int i = 0 ; i < matrix.length ; i++) {
			for ( int j = 0 ; j < matrix[0].length ; j++) {
				int leftHorizontalSum = getLeftHorizontalSum(i, j)+matrix[i][j];
				int upVerticalSum = getUpVerticalSum(i, j)+matrix[i][j];
				int diagonalSum = getDiagonalSum(i, j)+leftHorizontalSum+upVerticalSum-matrix[i][j];
				RectangularSumHolder rectangularSumHolder = new RectangularSumHolder();
				rectangularSumHolder.setDiagonalSum(diagonalSum);
				rectangularSumHolder.setLeftHorizontalSum(leftHorizontalSum);
				rectangularSumHolder.setUpVerticalSum(upVerticalSum);
				MapKey mapKey = new MapKey(i, j);
				map.put(mapKey, rectangularSumHolder);
			}
		}

		printDiagonalSum();
	}

	private void printDiagonalSum(){
		for ( int i = 0 ; i < rowLength ; i++){
			for ( int j = 0 ; j < colLength ; j++){
				System.out.print(map.get(new MapKey(i, j)).diagonalSum+" ");
			}
			System.out.println();
		}
	}

	private int getDiagonalSum(int row, int col) {
		if (row-1 >= 0 && col-1 >= 0) {
			return  map.get(new MapKey(row-1, col-1)).diagonalSum;
		}
		return 0;
	}

	private int getUpVerticalSum(int row, int col) {
		if ( row -1 >= 0) {
			return map.get(new MapKey(row-1, col)).upVerticalSum;
		}
		return  0;
	}

	private int getLeftHorizontalSum(int row, int col) {
		if (col-1 >=  0){
			return map.get(new MapKey(row, col-1)).leftHorizontalSum;
		}
		return  0;
	}

	public static void main(String[] args) {
		int[][] matrix= {
			{1,2,3,4},
			{5,6,7,8},
			{9, 10, 11, 12}
		};
		new SubRectangularSum().calculateMatrix(matrix);
	}
}

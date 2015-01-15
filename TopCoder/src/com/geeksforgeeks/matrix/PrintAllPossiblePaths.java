package com.geeksforgeeks.matrix;

import java.util.ArrayList;
import java.util.List;
//http://www.geeksforgeeks.org/print-all-possible-paths-from-top-left-to-bottom-right-of-a-mxn-matrix/
public class PrintAllPossiblePaths {
	int[] x = {0,1};
	int[] y = {1, 0};
	boolean[][] visited;
	public void print(int nRows, int nCols){
		int[][] matrix = new int[nRows][nCols];
		visited = new boolean[nRows][nCols];
		int x = 1;
		for(int i = 0 ; i < nRows ; i++){
			for(int j = 0 ; j < nCols ; j++){
				 matrix[i][j] = x++;
			}
		}
		
		printPathUtil(0, 0, nRows, nCols, matrix, new ArrayList<Integer>());
	}
	private void printPathUtil(int row, int column, int nRows, int nCols,
			int[][] matrix, List<Integer> arrayList) {
		if (!isValid(row, column, nRows, nCols)){
			return;
		}
		arrayList.add(matrix[row][column]);
		if (row == nRows-1 && column == nCols-1){
			print(arrayList);
		}
		
		visited[row][column] = true;
		for(int i = 0 ; i < 2 ; i++){
			int newX = row + x[i];
			int newY = column + y[i];
		    printPathUtil(newX, newY, nRows, nCols, matrix, arrayList);
		}
		arrayList.remove(arrayList.size()-1);
		visited[row][column] = false;
		
	}
	private boolean isValid(int newX, int newY, int nRows, int nCols) {
		if (newX >= 0 && newY >=0 && newX < nRows&& newY < nCols && !visited[newX][newY]){
			return true;
		}
		return false;
	}
	private void print(List<Integer> arrayList) {
		for(int i : arrayList){
			System.out.print(i +" ");
		}
		System.out.println();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new PrintAllPossiblePaths().print(4, 4);
	}

}

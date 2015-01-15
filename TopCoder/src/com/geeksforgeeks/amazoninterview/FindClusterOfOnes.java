package com.geeksforgeeks.amazoninterview;

//http://www.geeksforgeeks.org/amazon-interview-set-113-campus-internship/
public class FindClusterOfOnes
{

	int[][] matrix = null;
	boolean[][] visited = null;
	int rowSize;
	int colSize;
	public int find(int[][] matrix)
	{
		int colSize = matrix[0].length;
		int rowSize = matrix.length;
		this.rowSize = rowSize;
		this.colSize = colSize;
		this.matrix = matrix;
		this.visited = new boolean[rowSize][colSize];
		int count = 0;
		for(int i = 0 ; i < rowSize ; i++)
		{
			for(int j = 0 ; j < colSize ; j++)
			{
				if (!visited[i][j] && this.matrix[i][j] == 1)
				{
					recur(i , j);
					count++;
					printVisitedMatrix();
				}
			}
		}
		return count;
	}
	
	
	private void recur(int i, int j) 
	{
		if (!isValid(i, j))
		{
			return;
		}
		
		this.visited[i][j] = true;
		recur(i+1, j);
		recur(i, j+1);
		recur(i-1, j);
		recur(i, j-1);
		
		recur(i-1, j-1);
		recur(i+1, j+1);
		recur(i-1, j+1);
		recur(i+1, j-1);
	}


	private boolean isValid(int i, int j) 
	{		
		return ( i >=0 && i  < rowSize)
		      && ( j >=0 && j  < colSize)
		      && (!visited[i][j])
		      && this.matrix[i][j] == 1;
	}

	private void printVisitedMatrix()
	{
		for(int i = 0 ; i < this.rowSize ; i++)
		{
			for(int j = 0 ; j < this.colSize ; j++)
			{
				System.out.print(this.visited[i][j]+" ");
			}
			System.out.println();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		int[][] matrix = {
				{1, 1, 0, 0, 0},
		        {0, 1, 0, 0, 1},
		        {1, 0, 0, 1, 1},
		        {0, 0, 0, 0, 1},
		        {0, 0, 0, 0, 1}
		};
        int result = new FindClusterOfOnes().find(matrix);
        System.out.println(result);
	}

}

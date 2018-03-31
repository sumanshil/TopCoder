package com.geeksforgeeks.matrix;
//http://www.geeksforgeeks.org/create-a-matrix-with-alternating-rectangles-of-0-and-x/
public class PrintAlternateXsAndZeros 
{
	char[][] matrix = null;
	int m;
	int n;
	public void print(int m, int n)
	{
		matrix = new char[m][n];
		this.m = m;
		this.n = n;
		for(int i = 0 ; i < m ; i++)
		{
			for(int j = 0; j < n ; j++)
			{
				matrix[i][j] = '$';
			}
		}
		
		int[] x = {0,  1, 0, -1};
		int[] y = {1,  0,-1, 0};
		
		char c = 'x';
		int startX = 0;
		int startY = 0;
		int index = 0;
		matrix[startX][startY] = c;
		while(true)
		{
			startX += x[index];
			startY += y[index];
			
			if (!isValid(startX, startY, m, n, matrix))
			{
				startX -= x[index];
				startY -= y[index];				
				index++;
				if (index == 4)
				{
					index = 0;					
					if (!isValid(startX, startY+1, m, n, matrix))
					{
						break;
					}
					else
					{
						if (c == 'x')
						{
							c = '0';
						}
						else
						{
							c = 'x';
						}
					}
				}
			}	
			else
			{
				matrix[startX][startY] = c;
			}
		}		
		printMatrix();
	}

	private void printMatrix()
	{
		for(int i = 0 ; i < this.m ; i++)
		{
			for(int j =0 ; j < this.n ; j++)
			{
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	
	private boolean isValid(int x, int y, int m , int n, char[][] matrix)
	{
		return x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] == '$';
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		new PrintAlternateXsAndZeros().print(5, 6);
	}

}

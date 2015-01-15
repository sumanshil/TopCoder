package com.topcoder.problems.Invitational2001.semifinalC;

//http://community.topcoder.com/stat?c=problem_statement&pm=204&rd=55
//Incomplete. Check the TopCoder code.

public class Checkers {

	private static final int BLOCK = 9;
	private static final int EMPTY = 8;
	private int minDistance = Integer.MAX_VALUE;
	int[][] matrix = new int[8][8];
	public int compute(String startPos, String[] pieces)
	{
		for(int i = 0 ; i < 8; i++)
		{
		    for(int j = 0 ; j < 8 ; j++)
		    {
		    	matrix[i][j] = EMPTY;
		    }
		}
	    for(String piece : pieces)
	    {
	    	String[] strArr = piece.split(",");
	    	int row = Integer.parseInt(strArr[1]);
	    	int col = Integer.parseInt(strArr[0]);
	    	matrix[row][col] = BLOCK;	    		    	
	    }
	    
	    String[] arr = startPos.split(",");
	    int      x   = Integer.parseInt(arr[1]);
	    int      y   = Integer.parseInt(arr[0]);
	    matrix[x][y] = 0;
	    //getMinPathRecursive(x, y, 0, false);
	    int result = getMinPathDynamic(x, y);
	    
        return result;   
	}
	
	private int getMinPathDynamic(int x, int y)
	{
		for(int i = 1 ; i < 8 ; i++)
		{
			for(int j = 0 ; j < 8 ; j++)
			{
				System.out.println("Considering "+i +" "+j);
				if (matrix[i][j] == EMPTY)
				{
					int a = 0;
					if (canMoveRight(i, j))
					{
						a = matrix[i-1][j+1]+1;
					}
					else if (canJumpRight(i, j))
					{
						a = matrix[i-2][j+2]==0 ? 1 : matrix[i-2][j+2];
					}
					
					int b = 0;
					if (canMoveLeft(i, j))
					{
						b = matrix[i-1][j-1]+1;
					}
					else if (canJumpLeft(i, j))
					{
						b = matrix[i-2][j-2]==0 ? 1 : matrix[i-2][j-2];;
					}
					
					
					if (a != 0 && b != 0)
					{
						matrix[i][j] = Math.min(a, b);
					}
					else if ( a != 0)
					{
						matrix[i][j] = a;
					}
					else if ( b != 0)
					{
						matrix[i][j] = b;
					}
					if ( a!= 0 || b != 0)
					{
					    printMatrix();
					}
				}
			}
		}
		int min = Integer.MAX_VALUE;
		for(int i = 0 ; i < 8 ; i++)
		{
			if (matrix[7][i] != EMPTY && matrix[7][i] != BLOCK && matrix[7][i] < min)
			{
				min = matrix[7][i];
			}
		}
		// this is a problem
		return min ;
	}
	
	
	private boolean canJumpLeft(int i, int j) 
	{
		return isValidPos(i-1,j-1) && matrix[i-1][j-1]==BLOCK && isValidPos(i-2, j-2) &&  matrix[i-2][j-2] !=BLOCK;
	}

	
	private boolean canMoveLeft(int i, int j) 
	{
		return isValidPos(i-1,j-1) && matrix[i-1][j-1]!=BLOCK; 
	}

	
	private void printMatrix() 
	{
		for(int i = 0 ; i < 8 ; i++)
		{
			for(int j = 0 ; j < 8 ; j++)
			{
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
		
	}

	
	private boolean canMoveRight(int x, int y)
	{
		return isValidPos(x-1,y+1)&& matrix[x-1][y+1]!=BLOCK;
	}
	
	
	private boolean canJumpRight(int i, int j)
	{
		return isValidPos(i-1,j+1) && matrix[i-1][j+1]==BLOCK && isValidPos(i-2, j+2) &&  matrix[i-2][j+2] !=BLOCK;
	}
	
	
	private boolean isValidPos(int i, int j) 
	{	
		return i>=0 && i <8 && j >=0 && j < 8 && matrix[i][j] != EMPTY;
	}

	private void getMinPathRecursive(int x, int y, int count, boolean isJump)
	{
		System.out.println("Considering path "+x+" "+y);
		if ( x == 6 || y == 6 )
		{
			if ( count+1 < minDistance)
			{
				minDistance = count+1;				
			}
			return;
		}
		
		if (isValid(x+1, y-1))
		{
		    getMinPathRecursive(x+1, y-1, count+1, false);
		}
		else if (isValid(x+2, y-2))
		{
			if (isJump)
			{
				getMinPathRecursive(x+2, y-2, count, true);
			}
			else
			{
				getMinPathRecursive(x+2, y-2, count+1, true);
			}
		}
		
		if (isValid(x+1, y+1))
		{
		    getMinPathRecursive(x+1, y+1, count+1, false);
		}
		else if (isValid(x+2, y+2))
		{
			if (isJump)
			{
			    getMinPathRecursive(x+2, y+2, count, true);
			}
			else
			{
				getMinPathRecursive(x+2, y+2, count+1, true);
			}
		}
		
	}
	
	
	
	
	private boolean isValid(int i, int j) 
	{		
		return i>=0 && i <8 && j >=0 && j < 8 && matrix[i][j] != 0&& matrix[i][j] != BLOCK;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
        int result = new Checkers().compute("1,1", new String[]   {"2,2", "0,4", "1,4", "2,4", "5,5", "4,6", "7,7"});
        System.out.println(result);
        
	}

}

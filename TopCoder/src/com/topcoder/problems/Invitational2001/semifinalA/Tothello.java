package com.topcoder.problems.Invitational2001.semifinalA;

import java.util.ArrayList;
import java.util.List;

//http://community.topcoder.com/stat?c=problem_statement&pm=203&rd=53
public class Tothello {
	private int[][] matrix = new int[8][8];
	// red is represented by 1
	// black is represented by 2
	static class MatrixCoordinate
	{
		int row;
		int column;
		boolean visited;
		
		public MatrixCoordinate(int row, int column)
		{
			this.row = row;
			this.column = column;
		}
		
	}
	
    public int bestMove(String[] redPieces,
                        String[] blackPieces,
                        String whoseTurn)
    {
    	List<MatrixCoordinate> redPositions = new ArrayList<MatrixCoordinate>();
    	List<MatrixCoordinate> blackPositions = new ArrayList<MatrixCoordinate>();
    	for(String red : redPieces)
    	{
    		char column = red.charAt(0);
    		char row    = red.charAt(1);
    		int c = getColumnNumber(column);
    		int r = getRowNumber(row);
    		matrix[r][c] = 1;
    		redPositions.add(new MatrixCoordinate(r, c));
    	}

    	printMatrix(matrix);
    	for(String blackPiece : blackPieces)
    	{
    		char column = blackPiece.charAt(0);
    		char row    = blackPiece.charAt(1);
    		int c = getColumnNumber(column);
    		int r = getRowNumber(row);
    		matrix[r][c] = 2;
    		blackPositions.add(new MatrixCoordinate(r, c));
    	}
    	printMatrix(matrix);    	
    	int playerColor = getPlayerColor(whoseTurn);
    	int result = Integer.MIN_VALUE;
        for(int i = 0 ; i < 8 ; i++)
        {
        	for(int j = 0 ; j < 8 ; j++)
        	{
        		int[][] dupMatrix = getMatrixClone();
        		if (matrix[i][j] == 0)
        		{
        			System.out.println("Putting color i = "+i+" j = "+j);
        			dupMatrix[i][j] = playerColor;
        			int r = getMax1(i, j, playerColor, dupMatrix);
        			if ( r > result)
        			{
        				result = r;
        			}
        			//matrix[i][j] = 0;
        		}
        	}
        }
    	
    	
    	return result;
    }
    
    private void printMatrix(int[][] matrix)
    {
    	for(int i = 0 ; i < 8; i++)
    	{
    		for(int j = 0 ; j < 8 ; j++)
    		{
    			System.out.print(matrix[i][j]+" ");
    		}
    		System.out.println();
    	}
		
		System.out.println("======================");
	}

	private int[][] getMatrixClone() {
    	int[][] retVal = new int[8][8];
		for(int i = 0 ; i < 8 ; i++)
		{
			for(int j = 0 ; j < 8 ; j++)
			{
				retVal[i][j] = matrix[i][j];
			}
		}
		return retVal;
	}

	private int getMax(int row, int column, int playerColor, int[][] matrix) 
    {
		// check down
		int anotherPlayer = 0;
		if (playerColor == 1)
			anotherPlayer = 2;
		else
			anotherPlayer = 1;
    	for(int i = 0 ; i < 8 ; i++)
    	{
             for(int j = 0 ; j< 8 ; j++)
             {
            	 if (matrix[i][j] == playerColor)
            	 {
            		 System.out.println("Found player color at "+i +" j "+j);
            		 List<MatrixCoordinate> list = new ArrayList<MatrixCoordinate>();
            		 // check down
            		 boolean flag = false;
            		 for(int k = i+1 ; k <8 ; k++)
            		 {
            			 if (matrix[k][j] == 0)
            			 {
            				 list = new ArrayList<MatrixCoordinate>();
            				 break;
            			 }
            			 else if (matrix[k][j] == anotherPlayer)
            			 {
            				 list.add(new MatrixCoordinate(k, j));
            			 }
            			 else if (matrix[k][j] == playerColor)
            			 {
            				 flag = true;
            				 break;
            			 }
            		 }
            		 if (flag)
            		 {
		        		 for (MatrixCoordinate coordinate : list)
		        		 {
		        			 matrix[coordinate.row][coordinate.column] = playerColor;
		        		 }
            		 }
            		 // check up
            		 flag = false;
            		 list = new ArrayList<MatrixCoordinate>();
            		 for(int k = i-1 ; k >=0 ; k--)
            		 {
            			 if (matrix[k][j] == 0)
            			 {
            				 list = new ArrayList<MatrixCoordinate>();
            				 break;
            			 }
            			 else if (matrix[k][j] == anotherPlayer)
            			 {
            				 list.add(new MatrixCoordinate(k, j));
            			 }
            			 else if (matrix[k][j] == playerColor)
            			 {
            				 flag = true;
            				 break;
            			 }
            		 }
            		 if (flag)
            		 {
	            		 for (MatrixCoordinate coordinate : list)
	            		 {
	            			 matrix[coordinate.row][coordinate.column] = playerColor;
	            		 }
            		 }
            		 // check left
            		 list = new ArrayList<MatrixCoordinate>();
            		 flag = false;
            		 for(int k = j+1 ; k <8 ; k++)
            		 {
            			 if (matrix[i][k] == 0)
            			 {
            				 list = new ArrayList<MatrixCoordinate>();
            				 break;
            			 }
            			 else if (matrix[i][k] == anotherPlayer)
            			 {
            				 list.add(new MatrixCoordinate(i, k));
            			 }
            			 else if (matrix[i][k] == playerColor)
            			 {
            				 flag = true;
            				 break;
            			 }
            		 }
            		 
            		 if (flag)
            		 {
	            		 for (MatrixCoordinate coordinate : list)
	            		 {
	            			 matrix[coordinate.row][coordinate.column] = playerColor;
	            		 }
            		 }
            		 // check right
            		 flag = false;
            		 list = new ArrayList<MatrixCoordinate>();
            		 for(int k = j-1 ; k >=0 ; k--)
            		 {
            			 if (matrix[i][k] == 0)
            			 {
            				 list = new ArrayList<MatrixCoordinate>();
            				 break;
            			 }
            			 else if (matrix[i][k] == anotherPlayer)
            			 {
            				 list.add(new MatrixCoordinate(i, k));
            			 }
            			 else if (matrix[i][k] == playerColor)
            			 {
            				 flag = true;
            				 break;
            			 }
            		 }
            		 
            		 if (flag)
            		 {
	            		 for (MatrixCoordinate coordinate : list)
	            		 {
	            			 matrix[coordinate.row][coordinate.column] = playerColor;
	            		 }
            		 }
            		 // check down left
            		 
            		 int k = i+1;
            		 int l = j+1;
            		 flag = false;
            		 list = new ArrayList<MatrixCoordinate>();
            		 while(k < 8 && l < 8)
            		 {
            			 if (matrix[k][l] == 0)
            			 {
            				 list = new ArrayList<MatrixCoordinate>();
            				 break;
            			 }
            			 else if (matrix[k][l] == anotherPlayer)
            			 {
            				 list.add(new MatrixCoordinate(k, l));
            			 }
            			 else if (matrix[k][l] == playerColor)
            			 {
            				 flag = true;
            				 break;
            			 }
            			 k++;
            			 l++;
            		 }
            		 
            		 if (flag)
            		 for (MatrixCoordinate coordinate : list)
            		 {
            			 matrix[coordinate.row][coordinate.column] = playerColor;
            		 }
                     // check down right
            		 list = new ArrayList<MatrixCoordinate>();
            		 k = i+1;
            		 l = j-1;
            		 flag = false;
            		 while(k < 8 && l >=0 )
            		 {
            			 if (matrix[k][l] == 0)
            			 {
            				 list = new ArrayList<MatrixCoordinate>();
            				 break;
            			 }
            			 else if (matrix[k][l] == anotherPlayer)
            			 {
            				 list.add(new MatrixCoordinate(k, l));
            			 }
            			 else if (matrix[k][l] == playerColor)
            			 {
            				 flag = true;
            				 break;
            			 }
            			 k++;
            			 l--;
            		 }
            		 if (flag)
            		 for (MatrixCoordinate coordinate : list)
            		 {
            			 matrix[coordinate.row][coordinate.column] = playerColor;
            		 }
            		 
            		 //check up left
            		 k = i-1;
            		 l = j+1;
            		 flag = false;
            		 list = new ArrayList<MatrixCoordinate>();
            		 while(k >= 0 && l < 8)
            		 {
            			 if (matrix[k][l] == 0)
            			 {
            				 list = new ArrayList<MatrixCoordinate>();
            				 break;
            			 }
            			 else if (matrix[k][l] == anotherPlayer)
            			 {
            				 list.add(new MatrixCoordinate(k, l));
            			 }
            			 else if (matrix[k][l] == playerColor)
            			 {
            				 flag = true;
            				 break;
            			 }
            			 k--;
            			 l++;
            		 }
            		 if (flag)
            		 for (MatrixCoordinate coordinate : list)
            		 {
            			 matrix[coordinate.row][coordinate.column] = playerColor;
            		 }

            		 //check up right
            		 k = i-1;
            		 l = j-1;
            		 flag = false;
            		 list = new ArrayList<MatrixCoordinate>();
            		 while(k >= 0 && l >= 0)
            		 {
            			 if (matrix[k][l] == 0)
            			 {
            				 list = new ArrayList<MatrixCoordinate>();
            				 break;
            			 }
            			 else if (matrix[k][l] == anotherPlayer)
            			 {
            				 list.add(new MatrixCoordinate(k, l));
            			 }
            			 else if (matrix[k][l] == playerColor)
            			 {
            				 flag = true;
            				 break;
            			 }
            			 k--;
            			 l--;
            		 }
            		 if(flag)
            		 for (MatrixCoordinate coordinate : list)
            		 {
            			 matrix[coordinate.row][coordinate.column] = playerColor;
            		 }
            		 printMatrix(matrix);
            	 }            	 
             }
    	}
    	
    	int count = 0;
    	for(int i = 0 ; i < 8 ; i++)
    	{
    		for(int j = 0 ; j < 8 ; j++)
    		{
    			if (matrix[i][j] == playerColor)
    			{
    				count++;
    			}
    		}
    	}
    	
		return count;
	}

	private int getPlayerColor(String whoseTurn) {
		if ("Red".equals(whoseTurn))
		{
			return 1;
		}
		else if ("Black".equals(whoseTurn))
		{
			return 2;
		}
		return 0;
	}

	private int getColumnNumber(char s1)
    {   
    	int retVal = 0;
    	switch(s1)
    	{
    		case 'A':
    			retVal = 0;
    			break;
    		case 'B':
    			retVal = 1;
    			break;
    		case 'C':
    			retVal = 2;
    			break;
    		case 'D':
    			retVal = 3;
    			break;
    		case 'E':
    			retVal = 4;
    			break;
    		case 'F':
    			retVal = 5;
    			break;
    		case 'G':
    			retVal = 6;
    			break;
    		case 'H':
    			retVal = 7;
    			break;    			
    	}
    	return retVal;
    }
    
    private int getRowNumber(char s1)
    {
    	int retVal = 0;
    	switch(s1)
    	{
    		case '1':
    			retVal = 0;
    			break;
    		case '2':
    			retVal = 1;
    			break;
    		case '3':
    			retVal = 2;
    			break;
    		case '4':
    			retVal = 3;
    			break;
    		case '5':
    			retVal = 4;
    			break;
    		case '6':
    			retVal = 5;
    			break;
    		case '7':
    			retVal = 6;
    			break;
    		case '8':
    			retVal = 7;
    			break;    			
    	}
    	return retVal;
    }
    
    private int[] xAdv = {1,-1, 0, 0, 1, -1, -1,  1};
    private int[] yAdv = {0, 0, 1,-1, 1,  1, -1, -1};
    
	private int getMax1(int row, int column, int playerColor, int[][] matrix) 
    {
		// check down
		int anotherPlayer = 0;
		if (playerColor == 1)
			anotherPlayer = 2;
		else
			anotherPlayer = 1;
		for(int i = 0 ; i < 8 ; i++)
		{
			for(int j = 0 ; j < 8 ; j++)
			{
				  int d = 0;
	              if (matrix[i][j] == playerColor)
	              {
	            	  System.out.println("Found player color at i = "+i+" j = "+j);
	            	  int k = i + xAdv[d];
	            	  int l = j + yAdv[d];
	            	  List<MatrixCoordinate> list = new ArrayList<MatrixCoordinate>();
	            	  
	            	  while(true)
	            	  {
	            		  boolean shouldBreakLoop = false;
	            		  boolean shouldRecolor   = false;
		            	  while(isValid(k, l))
		            	  {		            		  
		            		  if (matrix[k][l]== anotherPlayer)
		            		  {
		            			 // System.out.println("Found opposite player at i = "+k+" j = "+l);
		            			  list.add(new MatrixCoordinate(k, l));
		    	            	  k = k + xAdv[d];
		    	            	  l = l + yAdv[d];		    	            	
		            		  }
		            		  else if (matrix[k][l]== 0)
		            		  {
		            			//  System.out.println("Found Empty space at i = "+k+" j = "+l);
		            			  d++;
		            			  if (d >= 8)
		            			  {		            				  
		            				  shouldBreakLoop = true;
		            				  break;
		            			  }
		    	            	  k = i + xAdv[d];
		    	            	  l = j + yAdv[d];		            			  
		    	            	  shouldRecolor= false;
		            			  break;
		            		  }
		            		  else if (matrix[k][l]== playerColor)
		            		  {
		            			//  System.out.println("Found player color at i = "+k+" j = "+l);
		            			  d++;
		            			  if (d >= 8)
		            			  {
		            				  shouldBreakLoop = true;
		            				  break;
		            			  }		            			  
		    	            	  k = i + xAdv[d];
		    	            	  l = j + yAdv[d];		            			  
		    	            	  shouldRecolor = true;
		            			  break;
		            		  }
		            		  
		            	  }// end while(isValid(k, l))
		            	  if (!isValid(k, l))
		            	  {
		            		  if (shouldRecolor)
		            		  {
		            			  System.out.println("Coloring");
			             	      colorMatrix(list, matrix, playerColor);
			            		  list = new ArrayList<MatrixCoordinate>();		            			  
		            		  }
		            		  else
		            		  {
		            			  list = new ArrayList<MatrixCoordinate>();
		            		  }
		            		  d++;
	            			  if (d >= 8)
	            			  {
	            				  shouldBreakLoop = true;
	            				  break;
	            			  }		            			  
	    	            	  k = i + xAdv[d];
	    	            	  l = j + yAdv[d];		            			  
	    	            	  continue;
		            	  }
		            	  if (shouldRecolor)
		            	  {
		            		 System.out.println("Coloring");
		            		 colorMatrix(list, matrix, playerColor);
		            		 list = new ArrayList<MatrixCoordinate>();
		            	  }
		            	  else
		            	  {
		            		  list = new ArrayList<MatrixCoordinate>();
		            	  }
		            	  if (shouldBreakLoop)
		            	  {
		            		  break;
		            	  }
	            	  }//while(true)
	              }
			}
		}
		
    	int count = 0;
    	for(int i = 0 ; i < 8 ; i++)
    	{
    		for(int j = 0 ; j < 8 ; j++)
    		{
    			if (matrix[i][j] == playerColor)
    			{
    				count++;
    			}
    		}
    	}
        return count;		
    }    
    
	private boolean isValid(int k, int l)
	{
		return (k>=0 && k < 8) && (l>=0 && l< 8);
	}

	private void colorMatrix(List<MatrixCoordinate> list, int[][] matrix, int playerColor)
	{
		 for (MatrixCoordinate coordinate : list)
		 {
			 matrix[coordinate.row][coordinate.column] = playerColor;
		 }
		 printMatrix(matrix);		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
//		  A B C D E F G H
//		  1 - B - - B - B -
//		  2 - - R - - R - -
//		  3 - - R - - R - -
//		  4 - - R R R R B -  
//		  5 - - R - - R - -
//		  6 - - B - - - R - 
//		  7 - - - - - - - B
//		  8 - - - - - - - -
//		String[] redPieces   = {"C2","C3","C4","C5","D4","E4","F2","F3","F4","F5","G6"};
//		String[] blackPieces = {"B1","E1","G1","C6","H7","G4"};
//		String whoseTurn     = "Black";
		
//		String[] redPieces   = {"A1","B8","C6","C8","D8"};
//		String[] blackPieces = {"B2","C2","C3","C4","C5"};
//		String whoseTurn     = "Red";
        int result = new Tothello().bestMove(	new String[]	{"E1", "F2", "G2", "H2"}, new String[]{"A2", "B2", "C2", "D2", "E2"}, "Black");
        System.out.println(result);
	}

}
	
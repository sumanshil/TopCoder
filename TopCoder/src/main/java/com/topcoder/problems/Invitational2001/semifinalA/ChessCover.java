package com.topcoder.problems.Invitational2001.semifinalA;


//http://community.topcoder.com/stat?c=problem_statement&pm=202&rd=53
public class ChessCover
{
    private int columnLength;
    private int rowLength;
    
    private int queenXDiff[]  = {-1,-1, 1, 1, 0, 0, 1,-1};
    private int queenYDiff[]  = { 1,-1,-1, 1,-1, 1, 0, 0};
    
    private int knightXDiff[] = { 1,1,-1,-1,2, 2,-2,-2};
    private int knightYDiff[] = {-2,2, 2,-2,1,-1, 1,-1};
    
    private int[][]  matrix;
    
	public int getSafe (String[] boardLayout)
	{
	   this.columnLength = boardLayout[0].length();
	   this.rowLength    = boardLayout.length;
	   this.matrix       = new int[rowLength][columnLength];

	   for(int i = 0 ; i < rowLength ; i++)
	   {
		   for( int j = 0 ; j < columnLength ; j++)
		   {
			   if (boardLayout[i].charAt(j) == 'U')
			   {
				   matrix[i][j] = 0;
			   }
			   else if (boardLayout[i].charAt(j) == 'Q')
			   {
				   matrix[i][j] = 1;
			   }
			   else if (boardLayout[i].charAt(j) == 'R')
			   {
				   matrix[i][j] = 2;
			   }
			   else if (boardLayout[i].charAt(j) == 'B')
			   {
				   matrix[i][j] = 3;
			   }
			   else if (boardLayout[i].charAt(j) == 'K')
			   {
				   matrix[i][j] = 4;
			   }
			   else if (boardLayout[i].charAt(j) == 'P')
			   {
				   matrix[i][j] = 5;
			   }			   
		   }
	   }
	   
	   
	   for(int i = 0 ; i < rowLength ; i++)
	   {
		   for(int j = 0 ; j < columnLength ; j++)
		   {
			   if (matrix[i][j] == 1)
			   {
				   System.out.println("Found queen at "+i +" "+ j);
				   processQueens(i, j, 0, 8);
				   printMatrix();
			   }
			   else if (matrix[i][j] == 2)
			   {
				   processQueens(i, j, 4, 8);
				   System.out.println("Found queen at "+i +" "+ j);
				   printMatrix();
			   }
			   else if (matrix[i][j] == 3)
			   {
				   processQueens(i, j, 0, 4);
				   System.out.println("Found Bishop at "+i +" "+ j);
				   printMatrix();				   
			   }
			   else if (matrix[i][j] == 4)
			   {
				   processKnights(i, j);
				   System.out.println("Found Knight at "+i +" "+ j);
				   printMatrix();				   
				   
			   }
			   else if (matrix[i][j] == 5)
			   {
				   processPawns(i, j);
				   System.out.println("Found Pawn at "+i +" "+ j);
				   printMatrix();				   
				   
			   }
		   }
	   }
	   
	   int count = 0;
	   for(int i = 0 ; i < rowLength ; i++)
	   {
		   for(int j = 0 ; j < columnLength ; j++)
		   {
			   if (matrix[i][j] == 0)
			   {
				   count ++;
			   }
		   }
	   }
	   
	   return count;
	}
	private void printMatrix()
	{
	    for(int i = 0 ; i < rowLength ; i++)
	    {
	    	for(int j = 0 ; j < columnLength ; j++)
	    	{
	    		System.out.print(matrix[i][j]+" ");
	    	}
	    	System.out.println();
	    }
		
	}
	private void processPawns(int x, int y)
	{
		for(int i = 0 ; i < 4 ; i++)
		{
			int x1 = x + queenXDiff[i];
			int y1 = y + queenYDiff[i];
			if (valid(x1, y1))
			{
				matrix[x1][y1] = 9;
			}
		}
		
	}
	private void processKnights(int x, int y)
	{
		for(int i = 0 ; i< knightXDiff.length ; i++)
		{
			int x1 = x + knightXDiff[i];
			int y1 = y + knightYDiff[i];
			if (valid(x1, y1))
			{
				matrix[x1][y1] = 9;
			}
		}
		
	}
	private void processQueens(int x, int y, int dr, int end)
	{
		int x1 = x + queenXDiff[dr];
		int y1 = y + queenYDiff[dr];
	    while(true)
	    {
		     if (!valid(x1, y1))
		     {
		    	dr++;
		    	if (dr == end)
		    	{
		    		break;
		    	}
		    	x1 = x + queenXDiff[dr];
		    	y1 = y + queenYDiff[dr];
		     }
		     else
		     {
		    	 matrix[x1][y1] = 9;
			     x1 = x1 + queenXDiff[dr];
			     y1 = y1 + queenYDiff[dr];
		     }
	    }
		
	}
	
	
	private boolean valid(int x1, int y1)
	{		
		return x1>=0 && x1< rowLength && y1 >= 0 && y1 < columnLength && (matrix[x1][y1] ==0 || matrix[x1][y1] ==9);
	}
	
//	private boolean validForKnight(int x1, int y1)
//	{		
//		return x1>=0 && x1< rowLength && y1 >= 0 && y1 < columnLength;
//	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		//String[] str = {"UPUPU", "PUPUP", "UPKPU", "PUPUP", "UPUPU"};
		//String[] str = {"UUUUUQB", "UUUUUPQ", "UUUUUKU", "BUUUUUU", "UUUUUUU", "UUUUUUU", "RUUUUUU"};
		//String[] str = 	{"QPUUUUU", "UUUUUUU", "UUUUUUU", "UUUUUUU", "UUUUUUU"};
		//String[] str = 	{"UUUUUUUUUU", "UUUUUUUUUB", "UUUUUUUUUU", "UUUUUUUPUU", "UUUUUUUUUU", "UUUUUUUUUU", "UUUUUUUUUU", "UUUUUUUUUU"}	;
		//String[] str = {"UUUUU", "UQUQU", "UUUUU"};
		//String[] str = {"UUU", "UPU", "UUU"};
		//String[] str = {"UUUUUQ", "UUUUUU", "BURUUU", "UUKUUU", "UUUUUU"};
		String[] str = {"UUPUPUUUUB", "UUUKUUUPBU", "UPPUPUUUUU", "UPUQQUPUUU", "UPUQQUUUUU", "UPUUUQUPUU", "QUQUPUPRPU", "UUUUUUUUBB", "URRRRUPRPP", "UUUUUUUBUU"}	;
		int result = new ChessCover().getSafe(str);
        System.out.println(result);
	}

}

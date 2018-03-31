package com.topcoder.problems.round160;

import java.util.concurrent.ForkJoinWorkerThread;

//http://community.topcoder.com/stat?c=problem_statement&pm=1608&rd=4605
public class Quilting
{
	int[] dx = {-1,  0, 1, 0};
	int[] dy = { 0, -1, 0, 1};
	 
    public String lastPatch(int length, int width, String[] colorList)
    {
    	String[][] matrix = new String[200][200];
    	
    	for(int i = 0; i < 200 ; i++)
    	{
    		for(int j = 0 ; j < 200; j++)
    		{
    			matrix[i][j] = "$";
    		}
    	}
    	
    	matrix[100][100] = colorList[0];
    	int[] totalFrequency = new int[colorList.length];
    	totalFrequency[0] = 1;
    	int currentX = 100;
    	int currentY = 100;
    	int diffIndex = -1;
    	int maxRow = length;
    	int maxCol = width;
    	int currentMove = 1;
    	boolean moveRowWise = false;
    	boolean moveColWise = true;
    	String retVal = null;    
    	//System.out.println("Selected color "+ colorList[0]);
    	while(true)
    	{
    		int currentDiffIndex = diffIndex;
    		diffIndex = (diffIndex+1) % 4;
    		if (isValid(diffIndex,
    				    matrix,
    				    currentX,
    				    currentY)
    		    && ( (moveRowWise &&
    		    	 currentMove <= maxCol
    		    	)
    		    	||
    		    	(
    		    	 moveColWise &&
    	    		 currentMove <= maxRow    		    			
    		    	))
    		    )
    		{
    			currentMove = 1;
    			currentDiffIndex = diffIndex;
    			moveColWise = !moveColWise;
    			moveRowWise = !moveRowWise;
    		}
    		else if (moveRowWise &&
   		    	     currentMove+1 < maxRow
             )
    		{
    			diffIndex = currentDiffIndex;
    			currentMove++;
    		}
    		else if (moveColWise &&
    				currentMove+1 < maxCol
            )
   		   {
   			   diffIndex = currentDiffIndex;
   			   currentMove++;
   		   }
    	   else
    	   {
    			break;
    	   }
    	   currentX = currentX + dx[currentDiffIndex];
    	   currentY = currentY + dy[currentDiffIndex];
    	  // System.out.println("currentX" + currentX);
    	  // System.out.println("currentY" + currentY);
    	   int[] neighborArray = getNeighborArray(matrix,
    			                                  colorList,
    			                                  currentX,
    			                                  currentY);
    	    int neighborMinCount = 0;
    	    int colorIndex = -1;
    	    for(int i = 0 ; i < neighborArray.length ; i++)
    	    {
    	    	if (neighborArray[i] != 0
    	    			&& colorIndex == -1)
    	    	{
    	    		neighborMinCount++;
    	    		colorIndex=i;
    	    	}
    	    	else if (neighborArray[i] != 0)
    	    	{
    	    		neighborMinCount++;
    	    	}
    	    }
    		
    	    if(neighborMinCount > 1)
    	    {
    	    	int min = Integer.MAX_VALUE;
    	    	for(int i = 0 ; i < neighborArray.length ; i++)
    	    	{
    	    		if (neighborArray[i] != 0)
    	    		{
    	    			if (min > totalFrequency[i])
    	    			{
    	    				min = totalFrequency[i];
    	    			}
    	    		}
    	    	}

    	    	for(int i = 0 ; i < totalFrequency.length ; i++)
    	    	{
    	    		if (totalFrequency[i] == min
    	    				&& neighborArray[i] != 0)
    	    		{
    	    			colorIndex = i;
    	    			break;
    	    		}    	    			
    	    	}

    	    }
    	    matrix[currentX][currentY] = colorList[colorIndex];
    	    totalFrequency[colorIndex] = totalFrequency[colorIndex]+1; 
    	    retVal = colorList[colorIndex];
    	    //System.out.println("Selected color "+ retVal);
    	    //print(matrix);
    	    //System.out.println("=============================");
    	}
        return retVal;	
    }
    
    
	private void print(String[][] matrix)
	{
		for(int i = 0 ; i < 200 ; i++)
		{
			boolean hasPrinted = false;
			for(int j = 0 ; j < 200 ; j++)
			{
				if (!"$".equals(matrix[i][j]))
				{
					System.out.print(matrix[i][j]+" ");
					hasPrinted = true;
				}
			}
			if (hasPrinted)
			System.out.println();
		}
		
	}


	private int[] getNeighborArray(String[][] matrix,
			                       String[] colorList,
			                       int currentX,
			                       int currentY)
	{		
		int[] retVal = new int[colorList.length];
		int[] ret = new int[retVal.length];
		for(int x = -1 ; x <=1 ; x++ )
		{
			for(int y = -1 ; y <=1 ; y++)
			{
				if (x == 0 && y == 0)
				{
					continue;
				}
				int sX = currentX + x;
				int sY = currentY + y;
				if (sX >= 0 
					&& sX < 200
					&& sY >=0 
					&& sY < 200)
				{
					int index = 0;
					for(String color : colorList)
					{
						if (color.equals(matrix[sX][sY]))
						{
							retVal[index] = retVal[index]+1;
						}
						index++;
					}
				}
			}
		}	
		int min = Integer.MAX_VALUE;
		for(int i = 0 ; i < retVal.length ; i++)
		{
			if (retVal[i] < min)
			{
				min = retVal[i];
			}
		}

		for(int i = 0 ; i < retVal.length ; i++)
		{
			if (min == retVal[i])
			{
				ret[i] = 1;
			}
		}
		return ret;
	}
	
	private boolean isValid(int diffIndex,
			                String[][] matrix,
			                int currentX,
			                int currentY)
	{		
		return (currentX+dx[diffIndex]) >=0 
			  && (currentX+dx[diffIndex]) < 200
			  && (currentY+dy[diffIndex]) >=0 
			  && (currentY+dy[diffIndex]) < 200
			  && "$".equals(matrix[currentX+dx[diffIndex]][currentY+dy[diffIndex]]);
			  
				
	}
	public static void main(String[] args)
	{
		String[] colorList =  {"A", "B", "C", "D", "E"};
		String result = new Quilting().lastPatch(100, 100, colorList);
        System.out.println(result);
	}

}

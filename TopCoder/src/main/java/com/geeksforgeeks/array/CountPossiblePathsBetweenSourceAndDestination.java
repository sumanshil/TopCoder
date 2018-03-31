package com.geeksforgeeks.array;

import java.util.LinkedList;
import java.util.List;

public class CountPossiblePathsBetweenSourceAndDestination 
{

	int[][] matrix = {
			{0, 1, 0,0,0,0},
			{0,0,1,0,0,0},
			{0,0,0,1,0,0},
			{0,0,0,0,1,0},
			{1,0,0,0,0,0},
			{0,0,0,0,0,0}
			};
	
	List<Integer> list = new LinkedList<Integer>();
	public void findAllPossibleWays(int source,
			                       int destination,
			                       int count)
	{
		list.add(source);
		recursiveUtil(source,
				      destination,
				      count);
	}
	
	private void recursiveUtil(int source,
            				   int destination,
                               int count)
	{
	   if (source == destination
			   && count == 0)
	   {
		   for(Integer i : list)
		   {
			   System.out.print(i +" -> ");
		   }
		   System.out.println();
		   return;
	   }
	   else if (count <= 0)
	   {
		   return;
	   }
	   
	   int[] adjacency = matrix[source];
	   
	   for(int j = 0 ; j < adjacency.length ; j++)
	   {
		   if (adjacency[j] == 1)
		   {
			   list.add(j);
			   recursiveUtil(j, destination, count-1);
			   list.remove(list.size()-1);
		   }
	   }	   
	}
	
	
	public int dynamicSolution(int source,
			                   int destination,
			                   int k)
	{
		int[][][] count = new int[matrix.length][matrix.length][k+1];
		
		int v = matrix.length;
		for(int e = 0 ; e <= k ; e++)
		{
			for(int i = 0 ; i < v ; i++)
			{
				for(int j = 0 ; j < v ; j++)
				{
					if (e == 0 
						&& i == j	)
					{
						count[i][j][e] = 1;
					}
					if (e == 1 
						&& matrix[i][j] != 0)
					{
						count[i][j][e] = 1;
					}
					
					if ( e > 1)
					{
						
						for(int a = 0 ; a < v; a++)
						{
							if (matrix[i][a] == 1)
							{
								count[i][j][e] += count[a][j][e-1];
							}
						}						
					}
				}
			}		
		}
        return count[source][destination][k];
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		new CountPossiblePathsBetweenSourceAndDestination().
		    findAllPossibleWays(0,
			     	            5,
				                3);

	}

}

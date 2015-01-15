package com.geeksforgeeks.array;

import java.util.Arrays;

//http://www.geeksforgeeks.org/sort-array-according-order-defined-another-array/
public class SortArrayAccordingToOrderDefinedByAnotherArray 
{

	public int[] arrange(int[] arr1,
			             int[] arr2)
	{
		int placeToInsert = 0;
		
		for(int i = 0 ; i < arr2.length ; i++)
		{
			for(int j = placeToInsert ; j < arr1.length ; j++)
			{
				if (arr2[i] == arr1[j])
				{
					if (j != placeToInsert)
					{
					    swap(arr1, j, placeToInsert);
					    placeToInsert++;
					}
					else
					{
						placeToInsert++;
					}
				}
			}
		}
		if (placeToInsert < arr1.length)
		{
			Arrays.sort(arr1, placeToInsert, arr1.length);
		}
		return arr1;
	}
	
	
	private void swap(int[] arr1,
			          int j,
			          int placeToInsert)
	{
		int temp = arr1[j];
		arr1[j]  = arr1[placeToInsert];
		arr1[placeToInsert] = temp;
		
	}


	public int[] arrangeUsingCountSort(int[] arr1,
			                         int[] arr2)
	{
		int max = Integer.MIN_VALUE;
		for(int i = 0 ; i < arr1.length ; i++)
		{
			if (arr1[i] > max)
			{
				max = arr1[i];
			}
		}
		
		int count[] = new int[max+1];
		
		for(int i = 0 ; i < arr1.length ; i++)
		{
			count[arr1[i]]++;
		}
		
		int index = 0;
		boolean[] visited = new boolean[max+1];
		
		for(int j = 0 ; j < arr2.length ; j++)
		{
			visited[arr2[j]] = true;
			for(int k = 0 ; k < count[arr2[j]] ; k++)
			{
				arr1[index++] = arr2[j];
			}
		}
		
		for(int j = 0 ; j <= max ; j++)
		{
			if ( ! visited[j]
			      && count[j] > 0)
			{
				for(int k = 0 ; k < count[j]; k++)
				{
					arr1[index++] = j;	
				}
			}
		}
		return arr1;
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
	    int[] arr1 = {2,4,7,8,4,3,2,6};
	    int[] arr2 = {2,4};
        int[] result = new SortArrayAccordingToOrderDefinedByAnotherArray().
        arrangeUsingCountSort(arr1, arr2);
        for(int j = 0 ; j < result.length; j++)
        {
        	System.out.println(result[j]);
        }
	}

}

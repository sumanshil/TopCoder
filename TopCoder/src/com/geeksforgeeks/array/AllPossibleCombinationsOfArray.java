package com.geeksforgeeks.array;

public class AllPossibleCombinationsOfArray 
{
	public void printAllCombinations(int[] arr)
	{
		int[] data = new int[arr.length];
		boolean[] taken = new boolean[arr.length];
		//recursion(arr, data, taken, 0);
		recursion1(arr, data, 0, 0);
	}
	
	
	private void recursion(int[] arr,
			               int[] data,
			               boolean[] taken,
			               int index)
	{
		if (index == arr.length)
		{
			for(int i = 0 ; i < data.length ; i++)
			{
				System.out.print(data[i]+" ");
			}
			System.out.println();
			return;
		}
		
		for(int i= 0 ; i < arr.length ; i++)
		{
		    if (!taken[i])
			{
                data[index] = arr[i];
          	    taken[i] = true;
			    recursion(arr,
						  data,
						  taken,
						  index+1);

			    taken[i] = false;
			}
		}
	}
	
	private void recursion1(int[] arr,int[] data, int index, int i)
	{
		if (index == arr.length)
		{
			for(int j = 0 ; j < data.length ; j++)
			{
				System.out.print(data[j]+" ");
			}
			System.out.println();
			return;
		}
		if (i >= arr.length)
			return;
		
		data[index] = arr[i];
		recursion1(arr, data, index+1, i+1);
		recursion1(arr, data, index, i+1);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int[] arr = {1,2,3};
		new AllPossibleCombinationsOfArray().
		    printAllCombinations(arr);
	}

}

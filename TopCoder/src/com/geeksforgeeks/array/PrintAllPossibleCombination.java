package com.geeksforgeeks.array;

public class PrintAllPossibleCombination 
{
	public static void printAllCombinations(int[] arr, int r)
	{
		int[] data = new int[r];
		recursiveUtil(arr,data, 0, arr.length-1, 0, r);
	}

	private static void recursiveUtil(int[] arr,
			                   int[] data,
			                   int start,
			                   int end,
			                   int index,
			                   int r) 
	{
	    if (index == r)
	    {
	    	for(int i = 0 ; i < data.length ; i++)
	    	{
	    		System.out.println(data[i]);
	    	}
	    	System.out.println("=========");
	    	return ;
	    }
		
	    for(int i = start ; i<= end ; i++)
	    {
	    	data[index] = arr[i];
	    	recursiveUtil(arr,
	    			      data,
	    			      i+1,
	    			      end,
	    			      index+1,
	    			      r);
	    }
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		int[] arr = {1,2,3,4,5};
		PrintAllPossibleCombination.
		       printAllCombinations(arr,
				                    3);

	}

}

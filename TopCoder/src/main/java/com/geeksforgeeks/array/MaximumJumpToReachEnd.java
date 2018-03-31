package com.geeksforgeeks.array;

public class MaximumJumpToReachEnd 
{

	public int getMaxJump(int[] arr)
	{
		int max = dynamic(arr);
		return max;
	}
	
	public int dynamic(int[] arr)
	{
		int[] jump = new int[arr.length];
		jump[arr.length-1] = 0;
		for(int i = arr.length-2 ; i >=0 ; i--)
		{
			int min = Integer.MAX_VALUE;
			for(int j = i+1 ; j < arr.length &&  j <= i + arr[i]; j++)
			{
				if (jump[j]+1 < min)
				{
					min = jump[j] + 1;
				}
			}
			jump[i] = min;
		}
		return jump[0];
	}
	
	private int recursiveUtil(int[] arr, int index)
	{
		if (index + arr[index] >= (arr.length-1))
		{
			return 1;
		}
		if (arr[index] == 0)
		{
			return Integer.MAX_VALUE;
		}
		int min = Integer.MAX_VALUE;
		
		for(int k = index+1 ; k<= index + arr[index]; k++)
		{
			int r = recursiveUtil(arr, k);
			if ( r< min)
			{
				min = r;
			}
		}
		return min+1;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		//int[] arr ={1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
		//int[] arr ={1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		int[] arr = {1, 3, 6, 3, 2, 3, 6, 8, 9, 5};
		int result = new MaximumJumpToReachEnd().getMaxJump(arr);
		System.out.println(result);

	}

}

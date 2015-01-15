package com.geeksforgeeks.array;
//http://www.geeksforgeeks.org/maximum-sum-path-across-two-arrays/
public class MaximumSumPathInTwoArrays 
{

	
	public int findMaximum(int[] arr1,
			               int[] arr2)
	{
		int lastMatchFound = 0;
		int[] matchIndexes1= new int[arr1.length];
		int[] matchIndexes2= new int[arr2.length];
		for(int i = 0 ; i < arr1.length ; i++)
		{
			for(int j = lastMatchFound ; j < arr2.length ; j++)
			{
				if (arr1[i] == arr2[j])
				{
					matchIndexes1[lastMatchFound] = i;
					matchIndexes2[lastMatchFound] = j;
					lastMatchFound++;
				}
			}
		}
		
		//lastMatchFound--;
		
		int lastIndex1 = 0;
		int lastIndex2 = 0;
		int result = 0;
		for (int i = 0 ; i < lastMatchFound ; i++)
		{
			int currentMatchIndex1 = matchIndexes1[i];
			int currentMatchIndex2 = matchIndexes2[i];
			
			int sum1 = getSum(arr1, lastIndex1, currentMatchIndex1);
			int sum2 = getSum(arr2, lastIndex2, currentMatchIndex2);
		    result += Math.max(sum1, sum2);
		    lastIndex1 = currentMatchIndex1+1;
		    lastIndex2 = currentMatchIndex2+1;
		}
		
		int sum1 = getSum(arr1, lastIndex1, arr1.length-1);
		int sum2 = getSum(arr2, lastIndex2, arr2.length-1);
		result += Math.max(sum1, sum2);
		return result;
		
	}
	
	
	public int getMaximum1(int[] arr1,
                           int[] arr2)
	{
		int sum1 = 0;
		int sum2 = 0;
		int i = 0;
		int j = 0;
		int result = 0;
		while(i < arr1.length 
		   && j < arr2.length)
		{
			if (arr1[i] < arr2[j])
			{
				sum1+= arr1[i++];
			}
			else if (arr1[i] > arr2[j])
			{
				sum2+= arr2[j++];
			}
			else
			{
				result += Math.max(sum1, sum2)+arr1[i];
				sum1 = 0;
				sum2 = 0;
				i++;
				j++;				
			}
			
		}
		
		if (i < arr1.length)
		{
			while( i < arr1.length)
			{
				sum1 += arr1[i++];
			}
		}
		
		if ( j < arr2.length)
		{
			while( j < arr2.length)
			{
				sum2 += arr2[i++];
			}			
		}
		
		result += Math.max(sum1, sum2);				
		return result;
	}
	
	
	private int getSum(int[] arr,
			           int start,
			           int end)
	{
		if (start > end)
		{
			return 0;
		}
		int result = 0;
		for(int i = start ; i <= end; i++)
		{
			result += arr[i];
		}
		return result;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
	    int[] arr1 = {2, 3, 7, 10, 12};
	    int[] arr2 = {1, 5, 7, 8};
        int result = new MaximumSumPathInTwoArrays().getMaximum1(arr1, arr2);
        System.out.println(result);
	}

}

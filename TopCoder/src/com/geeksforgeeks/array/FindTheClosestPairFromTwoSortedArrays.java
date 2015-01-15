package com.geeksforgeeks.array;

public class FindTheClosestPairFromTwoSortedArrays
{

	
	public void find(int[] arr1,
			         int[] arr2,
			         int n)
	{
		int a = 0;
		int b = 0;
		int min = Integer.MAX_VALUE;
		for(int i = 0 ; i < arr1.length ; i++)
		{
			for(int j = 0; j < arr2.length ; j++)
			{
				if (Math.abs((arr1[i] + arr2[j])-n) < min)
				{
					min = Math.abs((arr1[i] + arr2[j])-n);
					a = arr1[i];
					b = arr2[j];
				}
			}
		}
		System.out.println(a);
		System.out.println(b);
	}

	
	public void find1(int[] arr1,
			          int[] arr2,
			          int n)
	{
		int[] mergedArray = new int[arr1.length + arr2.length];
		boolean[] mergedBooleanArray = new boolean[arr1.length + arr2.length];
		int arr1Index = 0;
		int arr2Index = 0;
		int mergeArrayindex = 0;
	    while(arr1Index < arr1.length
	    	  && arr2Index < arr2.length)
	    {
	        if (arr1[arr1Index] < arr2[arr2Index])
	        {
	        	mergedArray[mergeArrayindex] = arr1[arr1Index++];
	        	mergedBooleanArray[mergeArrayindex++] = true;
	        }
	        else if (arr1[arr1Index] > arr2[arr2Index])
	        {
	        	mergedArray[mergeArrayindex] = arr1[arr2Index++];
	        	mergedBooleanArray[mergeArrayindex++] = false;
	        }
	        	
	    }
	    
	    while (arr1Index < arr1.length)
	    {
	    	mergedArray[mergeArrayindex] = arr1[arr1Index++];
	    	mergedBooleanArray[mergeArrayindex++] = true;
	    }
	
	    while (arr2Index < arr2.length)
	    {
	    	mergedArray[mergeArrayindex] = arr2[arr2Index++];
	    	mergedBooleanArray[mergeArrayindex++] = false;
	    }
	    
	    boolean firstIndexType = mergedBooleanArray[0];
	    boolean secondindexType = mergedBooleanArray[mergedBooleanArray.length-1];
	    int firstIndex = 0;
	    int lastIndex = mergedArray.length-1;
	    while(firstIndexType == secondindexType)
	    {
	    	if (mergedArray[firstIndex] + mergedArray[lastIndex] > n)
	    	{
	    		lastIndex--;
	    	}
	    	else if (mergedArray[firstIndex] + mergedArray[lastIndex] < n)
	    	{
	    		firstIndex++;
	    	}
	    	firstIndexType = mergedBooleanArray[firstIndex];
	    	secondindexType = mergedBooleanArray[lastIndex];
	    }
	    
	    int min = Integer.MAX_VALUE;
	    int a = 0;
	    int b = 0;
	    while(firstIndex < lastIndex)
	    {
	    	int diff = Math.abs(n- (mergedArray[firstIndex]+ mergedArray[lastIndex]));
	    	if ( diff < min)
	    	{
	    		min = diff;
	    		a = mergedArray[firstIndex];
	    		b = mergedArray[lastIndex];
		    	firstIndexType = mergedBooleanArray[firstIndex];
		    	secondindexType = mergedBooleanArray[lastIndex];
	    	}
	    	else if (min != Integer.MIN_VALUE && diff > min)
	    	{
	    		break;
	    	}
	    	
	    	if (mergedArray[firstIndex] + mergedArray[lastIndex] > n)
	    	{
	    		lastIndex--;
	    		secondindexType = mergedBooleanArray[lastIndex];
	    		while(firstIndexType == secondindexType
	    				&& firstIndex < lastIndex)
	    		{
	    			secondindexType = mergedBooleanArray[lastIndex--];
	    		}
	    	}
	    	else if (mergedArray[firstIndex] + mergedArray[lastIndex] < n)
	    	{
	    		firstIndex++;
	    		firstIndexType = mergedBooleanArray[firstIndex];
	    		while(firstIndexType == secondindexType
	    				&& firstIndex < lastIndex)
	    		{
	    			firstIndexType = mergedBooleanArray[firstIndex++];
	    		}
	    	}
	    	
	    }
	    System.out.println(a);
	    System.out.println(b);
	}
	
	public static void main(String[] args)
	{
		int[] arr1 = {1, 4, 5, 7};
		int[] arr2 = {10, 20, 30, 40};
		int n = 32;
		new FindTheClosestPairFromTwoSortedArrays().find1(arr1, arr2, n);
	}

}

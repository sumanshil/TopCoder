package com.geeksforgeeks.array;

import java.util.Arrays;

public class FindLargestContiguousArray
{
    public int find(int[] arr)
    {
    	Arrays.sort(arr);
    	int startIndex= 0;
    	int lastIndex = 1;
    	int maxLength = Integer.MIN_VALUE;
    	while(lastIndex < arr.length)
    	{
    		if (arr[lastIndex]-arr[lastIndex-1] > 1)
    		{
    			if (maxLength < (lastIndex-startIndex))
    			{
    				maxLength = (lastIndex-startIndex);
    				startIndex = lastIndex;
    				lastIndex  = startIndex+1;
    			}
    			else
    			{
    				startIndex = lastIndex;
    				lastIndex  = startIndex+1;    				
    			}
    		}
    		else
    		{
    			lastIndex++;
    		}
    	}
		if (maxLength < (lastIndex-startIndex))
		{
			maxLength = (lastIndex-startIndex);
		}

    	return maxLength;
    }
    
    // without changing order of the elements
    public int find1(int[] arr)
    {
    	int n = arr.length;
    	int maxLength = Integer.MIN_VALUE;
    	for(int i = 0 ; i < n-1 ; i++)
    	{
    		int min = arr[i];
    		int max = arr[i];
    		for(int j = i+1 ; j < n; j++)
    		{
    			if (arr[j] < min)
    			{
    				min = arr[j];
    			}
    			else if (max < arr[j])
    			{
    				max = arr[j];
    			}
    			
    			if ((max - min) == (j-i))
    			{
    				if ((max-min) +1> maxLength)
    				{
    					maxLength = (max-min)+1;
    				}
    			}
    		}    				
    	}
    	return maxLength;
    }
    
    
	public static void main(String[] args)
	{
		int[] arr = {1, 56, 58, 57, 90, 92, 94, 93, 91, 45};
		int result = new FindLargestContiguousArray().find1(arr);
		System.out.println(result);

	}
}

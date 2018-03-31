package com.geeksforgeeks.array;

import java.util.HashMap;
import java.util.Map;

//http://www.geeksforgeeks.org/find-if-there-is-a-subarray-with-0-sum/
public class FindSubArrayWithZeroSum
{
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	public void printZeroSumSubArray(int[] arr)
	{
		int sum = 0;
		for(int i = 0 ; i < arr.length ; i++)
		{
			sum += arr[i];
			if (map.containsKey(sum))
			{
				int index = map.get(sum);
				System.out.println("Result "+ (index+1)+" "+i);
			}
			else
			{
				map.put(sum, i);
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
        int[] arr= {4, 2, 0, 1, 6};
        new FindSubArrayWithZeroSum().printZeroSumSubArray(arr);
	}

}

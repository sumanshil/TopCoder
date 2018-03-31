package com.geeksforgeeks.dynamicprogramming;

public class CoinChangeRecursion {

	public void find(int[] coins, int sum)
	{
		util(coins,
				          sum,
				          coins.length-1,
				          0);
		System.out.println(min);
	}
	
	private int min = Integer.MAX_VALUE;
	
	private void util( int[] arr, 
			          int   sum,
			          int   length,
			          int   count)
	{
		if (length < 0 )
		{
			if (sum == 0)
			{
				if (count < min)
				{
					min = count;
				}
			}
			return ;
		}
		if ( sum == 0)
		{
			if (count < min)
			{
				min = count;
			}
			return;
		}
		
		if (arr[length] <= sum)
		{
			util(arr,
			     sum-arr[length],
			     length-1,
			     count+1);
			util(arr,
			     sum,
			     length-1,
			     count);

		}
		else
		{
			util(arr,
			     sum,
			     length-1,
			     count);

		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] coins = {1,2,3};
		int   sum = 6;
        new CoinChangeRecursion().find(coins, sum);
	}

}

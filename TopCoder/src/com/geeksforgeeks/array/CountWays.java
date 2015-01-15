package com.geeksforgeeks.array;

public class CountWays
{

	
	public int countWaysUtil(int n,
			                 int m)
	{
		if ( n <= 1)
		{
			return n;			
		}
		int res = 0;
		for(int i = 1 ; i <=m && i<= n ; i++ )
		{
			res += countWaysUtil(n-i, m);
		}
		return res;
	}
	
	public int dynamic(int n, int m)
	{
		int[] res = new int[n];
		res[0] = 1;
		res[1] = 1;
		
		for(int i = 2 ; i <n ; i++)
		{
			for(int j = 1 ; j <= m && j <=i ; j++ )
			{
				res[i] += res[i-j];
			}
		}
		return res[n-1];
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int s = 4;
		int m = 2;
		
		int result = new CountWays().dynamic(s+1, m);
		System.out.println(result);

	}

}

package com.geeksforgeeks.dynamicprogramming;

public class KnapSackRecursion {

	public int getKnapSack(int[] wt,
			               int[] val,
			               int weight)
	{
	    int result = recursive(wt,
	    		               val,
	    		               weight,
	    		               val.length-1);
	    return result;
	}
	
	
	private int recursive(int[] wt,
			              int[] val,
			              int weight,
			              int length)
	{
		if (length == 0 
			|| weight == 0)
		{
			return 0;
		}
		if (wt[length] > weight)
		{
			// can not be included
			return recursive(wt,
					         val,
					         weight,
					         length-1);
		}
		else
		{
			// include this weight
			int n1 = recursive(wt,
					           val,
					           weight-wt[length-1],
					           length-1);
			// don't include this weight
			int n2 = recursive(wt,
			                   val,
			                   weight,
			                   length-1);
			return Math.min(val[length]+n1, n2);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		int[] val    = {60, 100, 120};
		int[] weight = {10, 20, 30};

	}

}

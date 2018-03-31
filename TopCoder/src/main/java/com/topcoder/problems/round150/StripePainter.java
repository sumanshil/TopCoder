package com.topcoder.problems.round150;
//http://community.topcoder.com/stat?c=problem_statement&pm=1215&rd=4555
public class StripePainter
{
	public int recursive(String str, int i1 , int j1)
	{
		if (i1 == j1)
		{
			return 1;
		}
		
		int min = Integer.MAX_VALUE;
		for(int j = 0 ; j <= j1 ; j++)
		{
			int x = recursive(str,i1, i1+j);
			int y = recursive(str,i1+j+1, j1);
			int l = x+y;
			if (str.charAt(i1) == str.charAt(j1))
			{
				l = l -1;
			}
			if ( l < min)
			{
				min = l;
			}
		}
		return min;
	}
	
	
	public int minStrokes(String stripes)
	{
		// dynamic programming
		char[] arr = stripes.toCharArray();
		int[][] dp = new int[stripes.length()][stripes.length()];
		for(int i = 0 ; i < stripes.length() ;i++)
		{
			for(int j = 0 ; j < stripes.length() ; j++)
			{
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		for(int i = 0 ; i < stripes.length() ; i++)
		{
			dp[i][i] = 1;
		}
		
        for(int i = 1; i < stripes.length() ; i++)
        {
           for(int j = 0 ; j+i < stripes.length() ;j++)
           {
        	   for(int k = j ; k < j+i ; k++)
        	   {
        		   int x = (dp[j][k]+dp[k+1][i]);
        		   if (arr[j] == arr[j+i])
        		   {
        			   x = x-1;
        		   }
        	       dp[j][j+i] = Math.min(dp[j][j+i], x );
        	   }
           }
        }
		return dp[0][stripes.length()-1];
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
	    //int result = new StripePainter().minStrokes("RGB");
		int result = new StripePainter().recursive("RGB",0, 2);
	    System.out.println(result);

	}

}

package com.geeksforgeeks.dynamicprogramming;

public class LongestCommonSubSequence_1 {
    int[][] lcs ;
	public int findLCS(String x, String y)
	{
		//int result = recursive(x, y, x.length(), y.length());
		int result = dynamic(x, y);
		String lcs = getLCS(x, y);
		System.out.println(lcs);
		return result;
	}
	
	private String getLCS(String x, String y)
	{
		int a1 = x.length();
		int a2 = y.length();
		String s = "";
		int i = a1;
		int j = a2;
		while(i > 0 && j > 0)
		{
			if (x.charAt(i-1) == y.charAt(j-1))
			{
				s = x.charAt(i-1)+s;
				i--;
				j--;
			}
			else if ( lcs[i-1][j] > lcs[i][j-1])
			{
				i--;
			}
			else
			{
				j--;
			}
		}
		return s;
	}

	public int dynamic(String x, String y)
	{
		lcs = new int[x.length()+1][y.length()+1];
		lcs[0][0]   = 0;
		
		for (int i = 0 ; i <= x.length() ; i++)
		{
			for(int j = 0 ; j <= y.length() ; j++)
			{
				if (i == 0 || j ==0)
				{
					lcs[i][j] = 0;
				}
				else if (x.charAt(i-1) == y.charAt(j-1))
				{
					lcs[i][j] = lcs[i-1][j-1]+1;
				}
				else
				{
					lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
				}
			}
		}
		return lcs[x.length()][y.length()];
	}
	
	
	
	private int recursive(String x, String y, int i, int j)
	{
		if (i == 0 || j ==0 )
		{
			return 0;
		}
		if (x.charAt(i-1) == y.charAt(j-1))
		{
			return 1+ recursive(x, y, i-1, j-1);
		}
		else
		{
		    int x1 = recursive(x, y, i-1, j);
		    int x2 = recursive(x, y, i  ,j-1);
		    return Math.max(x1, x2);
		}
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
	    String x = "AGGTAB";
	    String y = "GXTXAYB";
	    int result = new LongestCommonSubSequence_1().findLCS(x, y);
        System.out.println(result);
	}

}

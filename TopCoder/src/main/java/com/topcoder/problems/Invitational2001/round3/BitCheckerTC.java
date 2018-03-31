package com.topcoder.problems.Invitational2001.round3;

public class BitCheckerTC 
{
	public String getResidue(String M , String K)
	{
		while(M.length()> 0 && M.charAt(0) == '0')
		{
			M = M.substring(1);
		}
		
		if (M.length() < K.length())
		{
			while(M.length() < K.length()-1)
			{
				M = "0"+M;
			}
			return M;
		}
		
		String r ="";
		for(int i = 0 ; i< M.length() ; i++)
		{
			if (i < K.length())
			{
				if ( M.charAt(i) == K.charAt(i))
				{
					r += '0';
				}
				else
				{
					r += '1';
				}
			}
			else
			{
				r += M.charAt(i);
			}
		}
		
		return getResidue(r, K);		
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String result = new BitCheckerTC().getResidue("11111", "1111");
		System.out.println(result);
	}

}

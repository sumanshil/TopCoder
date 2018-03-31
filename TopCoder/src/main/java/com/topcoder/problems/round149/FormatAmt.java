package com.topcoder.problems.round149;

public class FormatAmt 
{
	public String amount(int dollars, int cents)
	{
		String result = recursiveUtil(dollars);
		if (cents < 10)
		{
			return "$"+result+".0"+cents;
		}
		else
		{
			return "$"+result+"."+cents;
		}
	}

	private String recursiveUtil(int dollars) 
	{
	    if (dollars < 1000)
	    {
	    	return ""+dollars;
	    }
	    
	    int res = dollars %1000;
	    String str = recursiveUtil(dollars/1000);
		return str+","+res;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
	    String result = new FormatAmt().amount(49734321,9);
	    System.out.println(result);

	}

}

package com.topcoder.problems.round159;
//http://community.topcoder.com/stat?c=problem_statement&pm=1517&rd=4600
public class FryingHamburgers
{
	public 	int howLong(int panSize, int hamburgers)
	{
		if (hamburgers == 0)
		{
			return 0;
		}
		else if (hamburgers < panSize)
		{
			return 10;
		}
		else if (hamburgers % panSize == 0)
		{
			return (hamburgers / panSize)*10;
		}
		else if (hamburgers % panSize <= panSize/2)
		{
			return (hamburgers/panSize+1)*10-5;
		}
		else
		{
			return (hamburgers/panSize+1)*10;
		}
		
	}

	public static void main(String[] args)
	{
		int result = new FryingHamburgers().howLong(3, 8);
		System.out.println(result);

	}

}

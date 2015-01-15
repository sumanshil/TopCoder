package com.topcoder.problems.round150;
//http://community.topcoder.com/stat?c=problem_statement&pm=1346&rd=4555
public class WidgetRepairs 
{

	public int days(int[] arrivals, int numPerDay)
	{
		int carry = 0;
		int index = 0;
		int result = 0;
		while(true)
		{
			if (index < arrivals.length)
			{
				if (carry+arrivals[index] > numPerDay)
				{
					carry = (carry+arrivals[index])-numPerDay;
					result++;
				}
				else if (carry+arrivals[index] > 0)
				{
					carry = 0;
					result++;
				}
				index++;
			}
			else if (carry > 0)
			{
				carry -= numPerDay;
				result++;
			}
			else if (carry <= 0)
			{
				break;
			}
		}
		return result;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int result = new WidgetRepairs().days(new int[]{6, 5, 4, 3, 2, 1, 0, 0, 1, 2, 3, 4, 5, 6 }, 3);
		System.out.println(result);

	}

}

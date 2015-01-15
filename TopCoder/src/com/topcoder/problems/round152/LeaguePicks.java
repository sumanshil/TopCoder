package com.topcoder.problems.round152;

import java.util.ArrayList;
import java.util.List;

//http://community.topcoder.com/stat?c=problem_statement&pm=1716&rd=4565
public class LeaguePicks
{

	public int[] returnPicks(int position,
			                 int friends,
			                 int picks)
	{
		boolean increasing = true;
		int index = 0;
		List<Integer> list = new ArrayList<Integer>();
		int count = 0;
		while(picks > 0)
		{			
			if (increasing)
			{
				index++;			
			}
			else
			{
				index--;
			}
			if (index == friends+1)
			{
				if (increasing)
				{
					increasing = false;
					index--;
				}
			}
			else if (index <= 0)
			{
				if (!increasing)
				{
					increasing = true;
					index++;
				}
			}
			
			picks--;
			count++;
			if (index == position)
			{
				list.add(count);
			}
			
		}
		
		int[] result = new int[list.size()];
		for(int i = 0 ; i < list.size() ; i++)
		{
			result[i] = list.get(i);
		}
		return result;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		int[] result = new LeaguePicks().returnPicks(5,11,100);
        for(int j = 0 ; j< result.length ; j++)
        {
        	System.out.println(result[j]);
        }
	}

}

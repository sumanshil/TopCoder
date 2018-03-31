package com.topcoder.problems.round151;
//http://community.topcoder.com/stat?c=problem_statement&pm=1745&rd=4560
import java.util.ArrayList;
import java.util.List;

public class Gauss 
{
	public String[] whichSums(String target)
	{
//		Long number = Long.parseLong(target);
//		List<String> resultList = new ArrayList<String>();
//		for(Long i = 1L ; i <= number/2 ; i++)
//		{
//			List<Long> list = new ArrayList<Long>();
//			findRecursive(i, number, list);
//			if (list.size() > 0)
//			{
//				for(Long j : list)
//				{
//					System.out.println(j);
//				}
//				resultList.add("["+list.get(0)+", "+list.get(list.size()-1)+"]");
//			}
//		}
//		return (String[])resultList.toArray(new String[0]);
		return findNonRecursive(target);
	}
	
	private String[] findNonRecursive(String str)
	{
		Long number = Long.parseLong(str);
		List<String> resultList = new ArrayList<String>();
		for(Long i = 1L ; i <= number/2 ; i++)
		{
			List<Long> list = new ArrayList<Long>();
            Long num = number;
            for(Long j = i ; j < number ; j++ )
            {
            	if (num == 0)
            	{
            		break;
            	}
            	else if (num < 0)
            	{
            		list.clear();
            		break;
            	}
            	list.add(j);
            	num -= j;
            	
            }

			if (list.size() > 0)
			{
				for(Long j : list)
				{
					System.out.println(j);
				}
				resultList.add("["+list.get(0)+", "+list.get(list.size()-1)+"]");
			}
		}
		return (String[])resultList.toArray(new String[0]);
		
	}
	
	
	private void findRecursive(Long start,
			                   Long number,
			                   List<Long> list)
	{
		if (number == 0)
		{
			return ;
		}
		else if (number < 0)
		{
			list.clear();
			return;
		}
		
		list.add(start);
		findRecursive(start+1, number-start, list);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String[] result = new Gauss().whichSums("55");
        for(String str : result)
        {
        	System.out.println(str);
        }
	}

}

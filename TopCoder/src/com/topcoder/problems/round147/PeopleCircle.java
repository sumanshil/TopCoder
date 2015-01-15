package com.topcoder.problems.round147;

import java.util.ArrayList;
import java.util.List;

//http://community.topcoder.com/stat?c=problem_statement&pm=1225&rd=4540
public class PeopleCircle 
{
	public String order(int numMales, int numFemales, int K)
	{
		List<Integer> list = new ArrayList<Integer>(numFemales+numMales);
		StringBuffer sb = new StringBuffer(numMales + numFemales);
		for(int i = 0 ; i < numFemales + numMales ; i++)
		{
			sb.append("M");
			list.add(i);
		}
		int pos = 0;
		for(int i = 0 ; i < numFemales ; i++)
		{
		    pos = (pos+K-1)%list.size();
			int originalIndex = list.get(pos);
			sb.setCharAt(originalIndex, 'F');			
			list.remove(pos);
		}
		
		return sb.toString();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String result = new PeopleCircle().order(3, 2, 7);
		System.out.println(result);

	}

}

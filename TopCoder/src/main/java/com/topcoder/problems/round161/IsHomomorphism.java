package com.topcoder.problems.round161;

import java.util.ArrayList;
import java.util.List;

//http://community.topcoder.com/stat?c=problem_statement&pm=1807&rd=4610
public class IsHomomorphism
{
    public 	String[] numBad(String[] source,
    		                String[] target,
    		                int[] mapping)
    {
    	int n = mapping.length;
    	List<String> list = new ArrayList<String>();
    	for(int i = 0 ; i < n ; i++)
    	{
    		for(int j = 0 ; j < n ; j++)
    		{
    			// calculate a@b
    			int x = mapping[Integer.parseInt(""+source[i].charAt(j))];
    			int y = Integer.parseInt(""+target[mapping[i]].charAt(mapping[j]));
    			if (x != y)
    			{
    				list.add("("+i+","+j+")");
    			}
    		}
    	}
    	return (String[])list.toArray(new String[0]);
    }
	public static void main(String[] args)
	{
		String[] source = {"012", "120", "210"};
		String[] target = {"012", "120", "110"};
		int[] mapping = {0, 1, 2};
		String[] list = new IsHomomorphism().numBad(source, target, mapping);
		for(String str : list)
		{
			System.out.println(str);
		}

	}

}

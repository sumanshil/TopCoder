package com.topcoder.problems.wed091013;

import java.util.ArrayList;
import java.util.List;

public class StringPermutation {

	public void permute(String str)
	{
		ArrayList<String> list = new ArrayList<String>();
		permutaionUtil("", str, list);
		for(String str1 : list )
		{
			System.out.println(str1);
		}
	}
	private void permutaionUtil(String current,
								String left,
								ArrayList<String> list)
	{
		if (left.length() == 0)
		{
			list.add(current);
			return;
		}
		else
		{
			
			for(int i = 0 ; i < left.length() ; i++)
			{
				String a = current;
				String b = "";
				char c = left.charAt(i);
				a = a+c;
				
				for(int j = 0 ; j< i ; j++)
				{
					b += left.charAt(j);
				}
				
				for(int j = i+1 ; j< left.length() ; j++)
				{
					b += left.charAt(j);
				}
				permutaionUtil(a, b, list);
			}
			
			
		}
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
        new StringPermutation().permute("abcd");

	}

}

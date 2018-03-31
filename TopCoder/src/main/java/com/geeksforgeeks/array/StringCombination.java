package com.geeksforgeeks.array;

public class StringCombination
{
	public void permute(String str)
	{
		recursion("", str, str.length());
	}

	public void recursion(String remaining, String original, int length)
	{
		if (remaining.length() == length)
		{
			System.out.println(remaining);
			return;
		}
		
		for(int i = 0 ; i < original.length() ; i++)
		{
			String s1 = remaining;
			String s2 = "";
			
			s1 = s1+ original.charAt(i);
			for(int j = 0 ; j < i ; j++)
			{
				s2 += original.charAt(j);
			}
			
			for(int j = i+1 ; j < original.length() ; j++)
			{
				s2 += original.charAt(j);
			}
			recursion(s1, s2, length);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		new StringCombination().permute("abc");

	}

}

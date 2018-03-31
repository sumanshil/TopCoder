package com.topcoder.problems.round151;

public class PrefixCode {

	public String isOne(String[] words)
	{
		for(int i = 0 ; i < words.length ; i++)
		{
			for(int j = 0 ; j < words.length ; j++)
			{
				if ( i != j)
				{
					if (words[j].startsWith(words[i]))
					{
						return "No, "+i;
					}
				}
			}
		}
		return "Yes";
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
	    System.out.println(new PrefixCode().isOne(new String[]{"6G9Lnpzw", "kA", "SyW9fFaF", "k", "SyW9fFa", "6G", "6", "SyW9f"}));
	}

}

package com.topcoder.problems.round160;
//http://community.topcoder.com/stat?c=problem_statement&pm=1333&rd=4605
public class Substitute
{

	public int getValue(String key, String code)
	{
		int result = 0;
		for(int i = 0 ; i < code.length() ; i++)
		{
			int index = key.indexOf(code.charAt(i))+1;
			if ( index >= 1)
			{
				index = (index == 10 ? 0 : index);
				result = (result*10)+index;
			}
		}
		return result;
	}
	public static void main(String[] args)
	{
		int result = new Substitute().getValue("CRYSTALBUM", "MMA");
		System.out.println(result);
	}

}

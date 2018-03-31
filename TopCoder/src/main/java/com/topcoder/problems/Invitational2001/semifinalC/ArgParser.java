package com.topcoder.problems.Invitational2001.semifinalC;
//http://community.topcoder.com/stat?c=problem_statement&pm=205&rd=55
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArgParser {

	
	 private boolean isNotSpecialCharacter(char c)
	 {
		 return c != '$' && c != ',' && c!= '{' && c != '}'&& c != ' ';
	 }
	 
	 
	 public String[] parse(String input)
	 {
		List<String> list = new ArrayList<String>();
		StringBuffer sb   = new StringBuffer();
		boolean escape    = false; 
		boolean newWord   = true;
		for(int i = 1 ; i< input.length() ; i++)
		{
			char c = input.charAt(i);
			if(isNotSpecialCharacter(c))
			{
				if (newWord)
					newWord = false;
				sb.append(c);
			}
			else if (c == ' ')
			{
				if (newWord)
				{
					newWord = false;
				}
				else
				{
					sb.append(c);
				}
			}
			else if (c == ',')
			{
				if (newWord)
					newWord = false;
				if (escape)
				{
					sb.append(c);
					escape = false;
				}
				else
				{
				    list.add(sb.toString());
				    newWord = true;
				    sb      = new StringBuffer();
				}
			}
			else if (c == '{')
			{
				if (escape)
				{
					sb.append(c);
					escape = false;
				}
				else
				{
					list.add(sb.toString());
					sb = new StringBuffer();
				}
			}
			else if (c == '}')
			{
				if (escape)
				{
				    sb.append(c);
				    escape = false;
				}
				else
				{
					if (i == input.length()-1)
					{
					    list.add(sb.toString());
					    sb = new StringBuffer();
					    break;
					}
					else
					{
						list = new ArrayList<String>();
						list.add("INVALID");
						break;
					}
				}				
			}
			else
			{
				if (newWord)
					newWord = false;

				if (i+1 < input.length())
				{
					if (input.charAt(i+1) == ','
						||input.charAt(i+1) == '{'
						||input.charAt(i+1) == '}')
					{
						escape = true;
					}
					else
					{
						sb.append(c);
					}
				}
				else
				{
					sb.append(c);
				}
			}
		}
		// NEED to think about this
		if (sb.toString().length() > 0)
		{
			list = new ArrayList<String>();
			list.add("INVALID");
		}
		return (String[])list.toArray(new String[0]);
	 }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//	    String str = "{$$, $,$, }";
//        String[] strArr = new ArgParser().parse(str);
//        for(String str1 : strArr)
//        {
//        	System.out.println(str1);
//        }
        int[] arr = {1,2,3};
        int[] arr1 = {1,2,3};
        System.out.println(Arrays.equals(arr, arr1));
        //Set
        System.out.println(arr instanceof int[]);
	}

}

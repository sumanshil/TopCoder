package com.topcoder.problems.Invitational2001.round2;

import java.util.Stack;

public class TCMLParser {

	public String replaceTag(String tagString, int code, String toParse)
	{
		if ( !isValid( tagString ) )
		{
			return toParse;
		}
		boolean t =  false;
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i < toParse.length() ; i++)
		{
			
			char c = toParse.charAt(i);
			if (c  == '<')
			{
				t = true;
				sb.append(c);
				continue;
			}
			else if ( c == '>')
			{
				t = false;
				sb.append(c);
				continue;
			}
			if ( t && toParse.substring(i).toLowerCase().indexOf(tagString.toLowerCase()) == 0)
			{
				sb.append(code);
				i = i+ (tagString.length()-1);
			}
			else 
			{
				sb.append(c);
			}
		}
		return sb.toString();
	}
	private boolean isValid(String tagString)
	{
		Stack<Character> stack = new Stack<Character>();
		for( int i = 0 ; i < tagString.length() ; i++)
		{
			char c = tagString.charAt(i);
			if ( c == '<' || c == '>')
			{
				if (!stack.empty() && stack.peek() == c)
				{
					return false;
				}
				if ( c == '>')
				{
					if ( !stack.empty() && stack.peek() == '<')
					{
						stack.pop();
					}
					else if (stack.empty())
					{
						return false;
					}
				}
				else 
				{
					stack.push(c);
				}
			}
		}
		return stack.empty() ? true : false;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//String str = ">HI";
		//String str = "<a<b>c>";
		//String str = "<a b c><";
		//String str = "<a<b>";
		//String str = "/=<>HI";
		//String str = "/<>H=I<>/";
		//String str = "<><><><>";
		//String str = "<a=/><b==//bb><c223>";
		//String str = "<a b c>";
		
		String result = new TCMLParser().replaceTag(	"aBcdE", 123, "<aBcdE>abcde<eABCDEABCDEde>"	);
		System.out.println(result);

	}

}

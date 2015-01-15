package com.topcoder.problems.round149;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MessageMess 
{

	Set<String> set = new HashSet<String>();
	public String restore(String[] dictionary, String message)
	{		
		recursiveUtil(dictionary,
				      message,
				      new LinkedList<String>());
		if (set.size() > 1)
		{
			return "AMBIGUOUS!";
		}
		else if (set.size() == 0)
		{
			return "IMPOSSIBLE!";
		}
		else
		{
			String str = set.iterator().next();
			return str.substring(0, str.length()-1);
		}
	}
	
	
	private void recursiveUtil(String[] dictionary,
			                   String message,
			                   List<String> linkedList) 
	{
		if (message.length() == 0)
		{
			StringBuffer sb = new StringBuffer();
			for(String str : linkedList)
			{
				sb.append(str+" ");
			}
			set.add(sb.toString().intern());
			return;
		}
		if (set.size() > 1)
		{
			return;
		}
		for(String str : dictionary)
		{
			if (message.startsWith(str))
			{
				linkedList.add(str);
				recursiveUtil(dictionary,
						      message.substring(str.length()),
						      linkedList);
				linkedList.remove(linkedList.size()-1);
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String result = new MessageMess().restore(new String[]{"IMPOSS", "SIBLE", "S", "IMPOSSIBLE"}, "IMPOSSIBLE");
		System.out.println(result);

	}

}

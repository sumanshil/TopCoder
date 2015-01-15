package com.geeksforgeeks.strings;

import java.util.ArrayList;
import java.util.List;

//http://www.geeksforgeeks.org/count-possible-decodings-given-digit-sequence/
public class CountPossibleSequenceOfDigitSequence 
{

	public void count(String input)
	{
//		List<String> result = recursive(input);
//		System.out.println(result.size());
//		for(String str : result)
//		{
//			System.out.println(str);
//		}
		int count = dynamic(input, input.length());
		System.out.println(count);
	}
	
	public int dynamic(String input, int n)
	{
		int[] count = new int[n+1];
		count[0] = 1;
		count[1] = 1;
		
		for(int i = 2 ; i <=n ; i++)
		{
			count[i] = 0;
			if (input.charAt(i-1) > '0')
			{
				count[i] = count[i-1];
			}
			
			if (input.charAt(i-2) < '2' ||
					(input.charAt(i-2)== '2' && input.charAt(i-1) <'7'))
			{
				count[i] += count[i-2];
			}
			
		}
		return count[n];
		
	}
	
	
	
	public List<String> recursive(String input)
	{
		if (input.length() == 1)
		{
			if (getChar(input) != ' ')
			{
				List<String> list = new ArrayList<String>();
				list.add(""+getChar(input));
				return list;
			}
			else
			{
				return null;
			}
		}
		else if (input.length() == 0)
		{
			return new ArrayList<String>();
		}
			
		
		List<String> retVal = new ArrayList<String>();
		for(int i = 1 ; i <= input.length() ;i++)
		{
			String a = input.substring(0, i);
			
			List<String> bList  = null;
			{
			    bList = recursive(input.substring(i, input.length()));
			    //i++;
			}
			char c = getChar(a);
			if (c != ' ')
			{
				if (bList!= null && bList.size() > 0)
				{
					for(String str : bList)
					{
						retVal.add(c+str);
					}
					
				}
				else if (bList!= null && bList.size() == 0)
				{
					retVal.add(""+c);
				}
			}
			else
			{
				return retVal;
			}
		}		
		return retVal;
	}
	
	private char getChar(String a)
	{
	    int i = Integer.parseInt(a);
	    if (i < 27)
	    {
	    	return (char)(96+i);
	    }
		return ' ';
	} 


	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		//System.out.println((char)(96+26));
		new CountPossibleSequenceOfDigitSequence().count("1234");

	}

}

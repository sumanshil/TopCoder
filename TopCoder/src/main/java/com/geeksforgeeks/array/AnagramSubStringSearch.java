package com.geeksforgeeks.array;

import java.util.HashMap;
import java.util.Map;

//http://www.geeksforgeeks.org/anagram-substring-search-search-permutations/
public class AnagramSubStringSearch 
{
	Map<Character, Integer> map = new HashMap<Character, Integer>();
	public void find(String pattern,
			         String text)
	{
		int count = 0;
		for(int i = 0; i < pattern.length() ; i++)
		{
			if (map.containsKey(pattern.charAt(i)))
			{
				int value = map.get(pattern.charAt(i));
				map.put(pattern.charAt(i), value+1);
			}
			else
			{
				map.put(pattern.charAt(i), 1);
			}
		}
		Map<Character, Integer> tempMap = new HashMap<Character, Integer>(map);

		int startIndex = 0;
		int currentindex = startIndex;
		while(currentindex < text.length())
		{
			if (!tempMap.containsKey(text.charAt(currentindex))
					|| tempMap.get(text.charAt(currentindex)) == 0)
			{
				startIndex++;
				currentindex = startIndex;
				count = 0;
				tempMap = new HashMap<Character, Integer>(map);
				continue;
			}
			else
			{
			    count++;
			    int val = tempMap.get(text.charAt(currentindex));
			    tempMap.put(text.charAt(currentindex), val-1);
			    if (count == pattern.length())
			    {
			    	System.out.println("Found at "+ startIndex);
			    	startIndex++;
			    	currentindex = startIndex;
			    	count = 0;
			    	tempMap = new HashMap<Character, Integer>(map);
			    }
			    else
			    {
			    	currentindex++;
			    }
			}
		}		
	}
	
	
	public void robinKarp(String pattern,
			              String text)
	{
		int[] countP = new int[256];
		int[] countTW = new int[256];
		
		for(int i = 0 ; i < pattern.length() ; i++)
		{
			countP[pattern.charAt(i)]++;
			countTW[text.charAt(i)]++;
		}
		
		int j = pattern.length()-1;
		while(j < text.length())
		{
		    if (compare(countP, countTW))
		    {
		    	System.out.println("Found at :"+(j-(pattern.length()-1)));
		    }
		    
		    j++;
		    if (j >= text.length())
		    {
		    	return;
		    }
		    countTW[text.charAt(j)]++;
		    int k = j-(pattern.length());
			countTW[text.charAt(k)]--;		    
		}
		
	}
	
	private boolean compare(int[] countP, int[] countTW)
	{
	    for(int i = 0 ; i < countP.length ; i++)
	    {
	    	if (countP[i] != countTW[i])
	    	{
	    		return false;
	    	}
	    }
		return true;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
	    String text = "AAABABAA";
	    String pattern = "AABA";
	    new AnagramSubStringSearch().robinKarp(pattern, text);	         
	}

}

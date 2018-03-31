package com.topcoder.problems.round161;

import java.util.HashMap;
import java.util.Map;

//http://community.topcoder.com/stat?c=problem_statement&pm=1802&rd=4610
public class TennisRallies
{
    int result = 0;
	public int howMany(int numLength,
			           String[] forbidden,
			           int allowed)
	{
		 char[] arr = new char[numLength];
	     recursiveUtil(0,
	    		       numLength,
	    		       forbidden,
	    		       allowed,
	    		       0,
	    		       arr);
	     return result;
	}

	
	private void recursiveUtil(int currentIndex,
			                   int numLength,
			                   String[] forbidden,
			                   int allowed,
			                   int currentCount,
			                   char[] arr)
	{
		 int newCount = getNewCount(arr,
                                    currentIndex,
                                    forbidden);
   		 if (newCount+currentCount >= allowed)
		 {
		    return;
		 }
		
		 if (currentIndex > numLength)
		 {
			 return;
		 }
		 else if (currentIndex == numLength)
		 {
			 result++;
			 return;
		 }
		 arr[currentIndex] = 'c';
		 recursiveUtil(currentIndex+1,
				       numLength,
				       forbidden,
				       allowed,
				       newCount+currentCount,
				       arr);
		 arr[currentIndex] = 'd';
		 recursiveUtil(currentIndex+1,
				       numLength,
				       forbidden,
				       allowed,
				       newCount+currentCount,
				       arr);
		 
	}


	private int getNewCount(char[] arr,
			                int currentIndex,
			                String[] forbidden)
	{
		
		Map<Integer, String> map = new HashMap<Integer, String>();
		int retVal = 0;
		for(String word : forbidden)
		{
			int length = word.length();
			int start = currentIndex - length;
			if(start < 0)
			{
				continue;
			}
			if (!map.containsKey(length))
			{
				StringBuilder sb = new StringBuilder();
				for(int i=start ; i < currentIndex ; i++)
				{
					sb.append(arr[i]);
				}
				map.put(length, sb.toString());
				if (sb.toString().intern().equals(word.intern()))
				{
					retVal++;
				}
			}
			else
			{
				String  storedValue = map.get(length);
				if (storedValue.intern().equals(word.intern()))
				{
					retVal++;
				}
			}
		}
		return retVal;
	}


	public static void main(String[] args)
	{
		int result = new TennisRallies().howMany(18,
				                    new String[]{"c","cc","ccc","cccc","ccccc","cccccc","ccccccc",
				                    	 "cccccccc","ccccccccc","cccccccccc"},
				                    100);
        System.out.println(result);
	}

}

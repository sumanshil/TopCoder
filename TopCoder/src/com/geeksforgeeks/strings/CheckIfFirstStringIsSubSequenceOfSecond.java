package com.geeksforgeeks.strings;
//http://www.geeksforgeeks.org/given-two-strings-find-first-string-subsequence-second/
//O(n)
public class CheckIfFirstStringIsSubSequenceOfSecond
{
	public boolean find(String str1,
			            String str2)
	{
		boolean result = searchUtil(str1,
				                    str2,
				                    0,
				                    0);
		return result;
	}
	

	private boolean searchUtil(String firstString,
			                   String secondString,
			                   int firstStringIndex,
			                   int secondStringIndex)
	{
		if (bothStringsCompleted(firstString,
				                 secondString,
				                 firstStringIndex,
				                 secondStringIndex))
		{
			return true;
		}
		else if (onlyFirstStringCompleted(firstString,
                                          secondString,
                                          firstStringIndex,
                                          secondStringIndex))
		{
			return true;
		}
		else if (onlySecondStringCompleted(firstString,
                                           secondString,
                                           firstStringIndex,
                                           secondStringIndex))
		{
			return false;
		}
		
		// first character does not match, so proceed to next character
		// of second string. 
        if (firstString.charAt(0) != secondString.charAt(0))
        {
        	return searchUtil(firstString,
        			          secondString,
        			          firstStringIndex,
        			          secondStringIndex+1);
        }
        else
        {
        	// first character matches in both the string.
        	// so proceed to next characters 
        	return searchUtil(firstString,
			                  secondString,
			                  firstStringIndex+1,
			                  secondStringIndex+1);        	
        }
	}


	private boolean onlySecondStringCompleted(String firstString,
			String secondString, int firstStringIndex, int secondStringIndex)
	{
		return firstStringIndex < firstString.length()
				&& secondStringIndex == secondString.length();
	}


	private boolean onlyFirstStringCompleted(String firstString,
			                                 String secondString,
			                                 int firstStringIndex,
			                                 int secondStringIndex)
	{
		return firstStringIndex == firstString.length() 
				&& secondStringIndex < secondString.length();
	}


	private boolean bothStringsCompleted(String firstString,
			                             String secondString,
			                             int firstStringIndex,
			                             int secondStringIndex)
	{
		return firstStringIndex == firstString.length()
				&& secondStringIndex == secondString.length();
	}


	public static void main(String[] args)
	{
		String str1 = "gksrek";
		String str2 = "geeksforgeeks";
		boolean result = new CheckIfFirstStringIsSubSequenceOfSecond().
				         find(str1, str2);
		System.out.println(result);

	}

}

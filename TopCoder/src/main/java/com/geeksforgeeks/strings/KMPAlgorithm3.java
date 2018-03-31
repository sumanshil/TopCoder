package com.geeksforgeeks.strings;

public class KMPAlgorithm3
{
	
	public int calculate(String pattern,
			             String text)
	{
		int[] lps = calculateLPS(pattern);
		for(int i = 0 ; i< lps.length ; i++)
		{
			System.out.println(lps[i]);
		}
		
		int textIndex = 0;
		int patternIndex = 0;
		
		while(true)
		{
			if (textIndex == text.length())
				break;
			if (text.charAt(textIndex) == pattern.charAt(patternIndex))
			{
				textIndex ++;
				patternIndex++;
				if (patternIndex == pattern.length()-1)
				{
					System.out.println("Pattern found at "+ (textIndex-(pattern.length()-1)));
					patternIndex = 0;
				}
				
			}
			else
			{
				if (patternIndex > 0)
				{
					patternIndex = lps[patternIndex-1];
				}
				else
				{
					textIndex++;
				}
			}
			
		}
				
		
		
		return -1;
	}
	

	private int[] calculateLPS(String pattern)
	{
		int[] retVal = new int[pattern.length()];
		retVal[0]  = 0;
		int count = 0;
		int firstIndex = 0;
		int lastIndex = 1;
		while(lastIndex < pattern.length())
		{
			if (pattern.charAt(firstIndex) == pattern.charAt(lastIndex))
			{
				count++;
				retVal[lastIndex] = count;
				firstIndex++;
				lastIndex++;
			}
			else
			{
				count = 0;
				if (firstIndex > 0)
				{
					firstIndex = retVal[firstIndex-1];
					
				}
				else
				{
					lastIndex++;
				}
			}
		}
		return retVal;
	}


	public static void main(String[] args)
	{
		String pattern = "ABAB";
		String text    = "ABABADDDDABABA";
		
		new KMPAlgorithm3().calculate(pattern, text);

	}

}

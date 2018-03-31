package com.geeksforgeeks.array;

public class ExcelColumnNameGivenNumber 
{

	public String getColumn(int number)
	{
	    String result = recursive(number);	
	    return result;
	}
	
	
	
	
	private String recursive(int number)
	{
		if (number == 0)
		{
			return "";
		}
		
		int remainder = number % 26;
		int result    = number / 26;
		
		if (remainder > 0)
		{
			result++;
		}
		char c = ' ';
		if (remainder == 0)
			c = 'Z';
		else
			c = (char)('A'+(remainder-1));
		String retVal = recursive(result-1);
		retVal += c;
		return retVal;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
	    String result = new ExcelColumnNameGivenNumber().getColumn(7000);
        System.out.println(result);
	}

}

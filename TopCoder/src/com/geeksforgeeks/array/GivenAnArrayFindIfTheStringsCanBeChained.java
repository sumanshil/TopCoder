package com.geeksforgeeks.array;
//http://www.geeksforgeeks.org/given-array-strings-find-strings-can-chained-form-circle/
public class GivenAnArrayFindIfTheStringsCanBeChained 
{

	int[] frontChar = new int[26];
	int[] rearChar = new int[26];
	
	public boolean findIfChainCanBeFormed(String[] array)
	{
		if (array.length == 1)
		{
			return array[0].charAt(0) == array[0].charAt(array[0].length()-1);
		}
		for(String str : array)
		{
			char firstChar = str.charAt(0);
			char lastChar  = str.charAt(str.length()-1);
			frontChar[firstChar-'a']++;
			rearChar[firstChar-'a']--;
			
			frontChar[lastChar-'a']--;
			rearChar[lastChar-'a']++;			
		}
		
		return ifAllZeros(frontChar)&&ifAllZeros(rearChar);
	}
	private boolean ifAllZeros(int[] array)
	{
        for(int i = 0 ; i < array.length ; i++)
        {
        	return !(array[i]!=0);
        }
        return true;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String[] arr = new String[]{"aaa", "bbb"};
		boolean result = new GivenAnArrayFindIfTheStringsCanBeChained().findIfChainCanBeFormed(arr);
        System.out.println(result);
	}

}

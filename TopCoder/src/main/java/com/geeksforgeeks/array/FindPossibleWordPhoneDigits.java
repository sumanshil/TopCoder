package com.geeksforgeeks.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindPossibleWordPhoneDigits 
{
    private String[] arr = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    
    public void printString(int[] arr)
    {
    	String[] result = recursiveUtil(arr, 0);
    	for(String r : result)
    	{
    		System.out.println(r);
    	}
    }
    
	private String[] recursiveUtil(int[] intArr, int index)
	{
	    if ( index == (intArr.length-1))
	    {
	    	String s = arr[intArr[index]];
	    	String[] sArr = new String[s.length()];
	    	for(int i = 0 ; i < s.length() ; i++)
	    	{
	    		sArr[i] = ""+s.charAt(i);
	    	}
	    	return sArr;
	    }
	    
	    String[] stringArr = recursiveUtil(intArr, index+1);
	    String s1 = arr[intArr[index]];
	    String[] sArr1 = new String[s1.length()];
	    for(int j = 0 ; j < sArr1.length ; j++)
	    {
	    	sArr1[j] = ""+s1.charAt(j);
	    }
	    
	    List<String> list = new ArrayList<String>();
		for(int k = 0 ; k < sArr1.length ; k++)
		{
			for(int l = 0 ; l <stringArr.length ; l++ )
			{
			    list.add(sArr1[k]+stringArr[l]);	
			}
		}
		String[] result = (String[])list.toArray(new String[0]);
		Arrays.sort(result);
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		new FindPossibleWordPhoneDigits().printString(new int[]{7,8,9});
	}

}

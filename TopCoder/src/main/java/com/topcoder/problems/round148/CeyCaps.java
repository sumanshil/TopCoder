package com.topcoder.problems.round148;
//http://community.topcoder.com/stat?c=problem_statement&pm=1740&rd=4545
public class CeyCaps 
{
    private int[] array1 = new int[26];
    private int[] array2 = new int[26];
    
	public String decipher(String typed, String[] switches)
	{
		for(int i = 0 ; i < 26 ; i++)
		{
			array1[i] = i;
			array2[i] = i;
		}
		
		for(String swich : switches)
		{			
			char c = swich.charAt(0);
			char d = swich.charAt(2);
			
			int i = c - 'A';
			int j = d - 'A';
			
			int index1 = getIndex(i, array1);
			int index2 = getIndex(j, array1);
			
			
			int temp = array1[index1];
			array1[index1] = array1[index2];
			array1[index2] = temp;
		}
		
		StringBuffer result = new StringBuffer();
		for(int i = 0 ; i < typed.length() ; i++)
		{
			char c = typed.charAt(i);
			int j = c-'A';
			int k = array1[j];
			result.append((char)(k+'A'));
		}
		return result.toString();
	}
	
	private int getIndex(int index, int[] arr)
	{
		int retVal = -1;
		for(int i = 0 ; i < arr.length ; i++)
		{
			if (arr[i] == index)
			{
				retVal = i;
				break;
			}
		}
		return retVal;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
	    String result = new CeyCaps().decipher("IHWSIOTCHEDMYKEYCAPSARWUND", new String[]{"W:O","W:I"});
	    System.out.println(result);

	}

}

package com.topcoder.problems.round146;

public class TestPermutation {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
	   int count = 0;
	   for(int i = 0 ; i < 7; i++)
	   {
		   for(int j = 0 ; j < 7 ; j++)
		   {
			   System.out.println(""+i+""+j);
			   count++;
		   }
	   }
       System.out.println("==> "+count);
	}

}

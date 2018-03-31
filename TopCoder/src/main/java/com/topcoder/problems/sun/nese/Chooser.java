package com.topcoder.problems.sun.nese;
//http://community.topcoder.com/stat?c=problem_statement&pm=407&rd=60
public class Chooser {

	public int numSets(int[] easy, int[] middle, int[] hard)
	{
		int min   = 60;
		int max   = 75;
		int count = 0;
		for( int i = 0; i < easy.length ; i++)
		{
			for( int j = 0 ; j < middle.length ; j++)
			{
				for( int k = 0 ; k < hard.length ; k++)
				{
					int result = easy[i]+middle[j]+hard[k]; 
					if ( result >= min 
						 && result <= max)
					{
						count++;
					}
				}
			}
		}
		return count;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
	    int result = new Chooser().numSets(new int[]{5,5,5},new int[]{15,15,15},new int[]{45,45,45});
	    System.out.println(result);

	}

}

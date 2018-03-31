package com.topcoder.problems.round148;

import java.util.HashSet;
import java.util.Set;

public class MNS 
{
	Set<String> set = new HashSet<String>();
	public int combos(int[] numbers)
	{
		int[] current = new int[numbers.length];
		boolean[] taken = new boolean[numbers.length];
		recursionUtil(current,
				      taken,
				      numbers,
				      0);
		return set.size();
	}

	private void recursionUtil(int[] current,
			                   boolean[] taken,
			                   int[] numbers,
			                   int index)
	{
		if (index == numbers.length)
		{
			int sum1 = current[0]+current[1]+current[2];
			int sum2 = current[3]+current[4]+current[5];
			int sum3 = current[6]+current[7]+current[8];
			int sum4 = current[0]+current[3]+current[6];
			int sum5 = current[1]+current[4]+current[7];
			int sum6 = current[2]+current[5]+current[8];
			if ((sum1 == sum2)&& (sum2 == sum3) &&(sum3 == sum4)&&
				(sum4 == sum5)&& (sum5 == sum6))
			{
				StringBuffer sb = new StringBuffer();
				for(int i = 0 ; i< taken.length ; i++)
				{
					sb.append(current[i]);
				}
				set.add(sb.toString().intern());
			}
			return;
		}
		
		for(int i = 0 ; i < taken.length ; i++)
		{
			if(!taken[i])
			{
				taken[i] = true;
				current[index] = numbers[i];
				recursionUtil(current, taken, numbers, index+1);
				taken[i] = false;
			}
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
	    int[] arr = {1,2,3,3,2,1,2,2,2};
	    int r = new MNS().combos(arr);
        System.out.println(r);
	}

}

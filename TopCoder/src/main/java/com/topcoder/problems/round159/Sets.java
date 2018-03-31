package com.topcoder.problems.round159;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//http://community.topcoder.com/stat?c=problem_statement&pm=1685&rd=4600
public class Sets
{
	public int[] operate(int[] A, int[] B, String operation)
	{
		int[] result = null;
		if ("INTERSECTION".equals(operation))
		{
            result = intersection(A, B);			
		}
		else if ("UNION".equals(operation))
		{
			result = union(A, B);
		}
		else if ("SYMMETRIC DIFFERENCE".equals(operation))
		{
		    result = symmetricDifference(A, B);	
		}
		return result;
	
	}
	
	
	private int[] symmetricDifference(int[] a, int[] b)
	{
		int[] intersection = intersection(a, b);
		Set<Integer> intersectionSet = new HashSet<Integer>();
		for(int i = 0 ; i < intersection.length ; i++)
		{
			intersectionSet.add(new Integer(intersection[i]));
		}
		Set<Integer> result = new HashSet<Integer>();
		for(int i = 0 ; i < a.length ; i++)
		{
			Integer iN = new Integer(a[i]);
			if (!intersectionSet.contains(iN)
					&& ! result.contains(iN))
			{
				result.add(iN);
			}
		}
		
		for(int i = 0 ; i < b.length ; i++)
		{
			Integer iN = new Integer(b[i]);
			if (!intersectionSet.contains(iN)
					&& ! result.contains(iN))
			{
				result.add(iN);
			}
		}
		
		Integer[] iNArr = (Integer[])result.toArray(new Integer[0]);		
		int[] retVal = new int[iNArr.length];
		for(int i = 0 ; i < iNArr.length ; i++)
		{
			retVal[i] = iNArr[i];
		}
		Arrays.sort(retVal);
		return retVal;
	}


	private int[] union(int[] a, int[] b)
	{
		Set<Integer> set = new HashSet<Integer>();
        for(int i = 0 ; i< a.length ; i++)
        {
        	Integer iN = new Integer(a[i]);
        	if (!set.contains(iN))
        	{
        		set.add(iN);
        	}
        }
        
        for(int j = 0; j < b.length ; j++)
        {
        	Integer iN = new Integer(b[j]);
        	if (!set.contains(iN))
        	{
        		set.add(iN);
        	}
        }
        Integer[] arr = (Integer[])set.toArray(new Integer[0]);
		int[] retVal = new int[set.size()];
		for(int i = 0 ; i < retVal.length ; i++)
		{
			retVal[i] = arr[i];
		}
		Arrays.sort(retVal);;
		return retVal;
	}
	
	
	private int[] intersection(int[] a, int[] b)
	{
		Set<Integer> set = new HashSet<Integer>();
		
		
		for(int i = 0; i < a.length ; i++)
		{
			for(int j = 0 ; j < b.length; j++)
			{
				if (a[i] == b[j])
				{
					Integer iN = new Integer(a[i]);
					if (!set.contains(iN))
					{
					    set.add(new Integer(iN));
					}
				}
			}
		}
		Integer[] arr =  (Integer[])set.toArray(new Integer[0]);
		int[] retVal = new int[arr.length];
		for(int i = 0 ; i < arr.length ; i++)
		{
			retVal[i] = arr[i];
		}
		Arrays.sort(retVal);
		return retVal;
	}
	public static void main(String[] args)
	{
		int[] a = {342,654,897,312,76,23,78};
		int[] b = {21,43,87,98,23,756,897,234,645,876,123};
		int[] result = new Sets().operate(a, b, "SYMMETRIC DIFFERENCE");
		for(int i = 0 ; i < result.length ; i++)
		{
			System.out.println(result[i]);
		}

	}

}

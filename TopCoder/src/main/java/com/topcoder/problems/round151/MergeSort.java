package com.topcoder.problems.round151;
//http://community.topcoder.com/stat?c=problem_statement&pm=1705&rd=4560
import java.util.ArrayList;
import java.util.List;

public class MergeSort
{
	private int count = 0;
	
	public int howManyComparisons(int[] numbers)
	{
		List<Integer> list = new ArrayList<Integer>(numbers.length);
		for(int i = 0 ; i < numbers.length ; i++)
		{
			list.add(numbers[i]);
		}
		List<Integer> res = mergeSort(list);
		for(Integer i : res)
		{
			System.out.println(i);
		}
		return count;
	}
	
	private  List<Integer> mergeSort(List<Integer> list)
	{
		if (list.size() <= 1)
		{
			return list;
		}
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();		
		int l = list.size()/2;
		for(int i = 0 ; i < l ; i++)
		{
			list1.add(list.get(i));
		}
		
		for(int i = l ; i < list.size() ; i++)
		{
			list2.add(list.get(i));
		}
		
		List<Integer> a = mergeSort(list1);
		List<Integer> b = mergeSort(list2);
		List<Integer> c = merge(a, b);
		return c;
	}
	
	
	private List<Integer> merge(List<Integer> a, List<Integer> b)
	{
		List<Integer> c = new ArrayList<Integer>();
		while(!a.isEmpty() && !b.isEmpty())
		{
			if (a.get(0) < b.get(0))
			{
				count++;
				c.add(a.get(0));
				a.remove(0);
			}
			else if (a.get(0) > b.get(0))
			{
				count++;
				c.add(b.get(0));
				b.remove(0);				
			}
			else 
			{
				count++;
				c.add(a.get(0));
				c.add(b.get(0));
				a.remove(0);
				b.remove(0);
			}
		}
		if (!a.isEmpty())
		{
			c.addAll(a);
		}
		else if (!b.isEmpty())
		{
			c.addAll(b);
		}
		return c;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		int[] numbers = {-2000000000,2000000000,0,0,0,-2000000000,2000000000,0,0,0};
        int result = new MergeSort().howManyComparisons(numbers);       
        {
        	System.out.println(result);
        }       
	}

}

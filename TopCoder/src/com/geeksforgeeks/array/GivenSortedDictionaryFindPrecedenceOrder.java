package com.geeksforgeeks.array;

import java.util.HashMap;
import java.util.Map;

public class GivenSortedDictionaryFindPrecedenceOrder 
{

	Map<Character, Node> map = new HashMap<Character, Node>();
	Node      start = null;
	static class Node
	{
		char c;
		Node next;
		
		Node(char c)
		{
			this.c = c;
		}
		
		@Override
		public int hashCode() 
		{
		    return c;
		}
		
		@Override
		public boolean equals(Object obj) 
		{
			return c == ((Node)obj).c;
		}
	}
	
	private void print()
	{
		System.out.println();
		Node temp = start;
		while(temp != null)
		{
			System.out.print(temp.c +" -> ");
			temp = temp.next;
		}
	}
	
	public void findOrder(String[] input)
	{
		int max = Integer.MIN_VALUE;
		for(int i =0 ; i < input.length ; i++)
		{
			if (input[i].length() > max)
			{
				max = input[i].length();
			}
		}

		int i = 1;
		while( i <= max)
		{
			for(int j = 0 ; j < input.length-1; j++)
			{
				if (input[j].length() < i
						|| input[j+1].length() <i)
				{
					continue;
				}
				String str1 = input[j].substring(0, i);
				String str2 = input[j+1].substring(0, i);
				char c1 = str1.charAt(i-1);
				char c2 = str2.charAt(i-1);
				if (!alreadyPresent(c1, c2))
				{
					insert(c1, c2);
				}
				
			}
			print();
			i++;
		}
		
        print();				
	}
	
	private void insert(char c1, char c2) {
		if (start == null)
		{
			start = new Node(c1);
			start.next = new Node(c2);
			map.put(c1, start);
			map.put(c2, start.next);
		}
		else
		{
			if (map.containsKey(c1))
			{
				Node node = map.get(c1);
				Node temp = new Node(c2);
				temp.next = node.next;
				node.next = temp ;
				map.put(c2, temp);
			}
			else
			{
				Node temp = start;
				while(temp.next != null)
				{
					temp = temp.next;
				}
				temp.next = new Node(c1);
				map.put(c1, temp.next);
				temp.next.next = new Node(c2);
				map.put(c2, temp.next.next);
			}
			
		}
		
	}

	private boolean alreadyPresent(char c1, char c2)
	{		
		return map.containsKey(c1)&& map.containsKey(c2);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
        String[] str = {"uyy", "yuzx", "yuzy", "zyu", "zyx"};
        new GivenSortedDictionaryFindPrecedenceOrder().findOrder(str);
	}

}

package com.geeksforgeeks.bitmagic;

public class SwapIthAndJthBits 
{
	public int swap(int n, int i, int j)
	{
		int a = (1 << i);
		int b = (1 << j);
		
		int x = n & a;
		int y = n & b;
		if ( (x > 0 && y == 0)
			||(x == 0 && y > 0))
		{
		    x = (1<<i);
		    y = (1<<j);
		    n = n ^ (x | y);
		}
		return n;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
	    int n = 29;
	    int r = new SwapIthAndJthBits().swap(n, 1, 4);
	    System.out.println(r);
	}

}

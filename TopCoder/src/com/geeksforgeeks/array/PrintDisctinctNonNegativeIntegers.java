package com.geeksforgeeks.array;
//http://www.geeksforgeeks.org/count-distinct-non-negative-pairs-x-y-satisfy-inequality-xx-yy-n-2/
public class PrintDisctinctNonNegativeIntegers
{

	public void printLessONSolution(int n)
	{
		// calculate yCount for x = 0;
		int res = 0;
		int yCount = 0;
		int x = 0;
		for(int y = 0; y*y < n; y++)
		{
			yCount++;
		}
		
		while(yCount != 0)
		{
			res += yCount;
			x++;
			while(yCount != 0 && (x*x +(yCount-1)*(yCount-1)) >= n)
			{
				yCount--;
			}
		}
		System.out.println(res);
	}
	
	public void printONSolution(int n)
	{
		int res = 0;
		for(int x = 0 ; x*x < n ; x++)
		{
			for(int y = 0 ; (y*y + x*x) < n ; y++)
			{
				System.out.println("x= " +x + " y= "+y);
				res++;
			}
		}
		System.out.println(res);
	}
	
	public void print(int n)
	{
		for(int i = 0 ; i<n ; i++)
		{
		    printRecursive(i, 0, n);
		}
	}
	
	public void printRecursive(int a,
			                   int b,
			                   int n)
	{
		if ((a*a + b*b)< n)
		{
			System.out.println("a= "+a +" b "+b);
		}
		else if ((a*a + b*b)> n)
		{
			return;
		}
		
		printRecursive(a, b+1, n);
		
	}
	
	public static void main(String[] args)
	{
		new PrintDisctinctNonNegativeIntegers().printLessONSolution(5);

	}

}

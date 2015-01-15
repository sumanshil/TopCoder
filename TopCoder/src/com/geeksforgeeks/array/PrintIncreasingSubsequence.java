package com.geeksforgeeks.array;
//http://www.geeksforgeeks.org/print-increasing-sequences-length-k-first-n-natural-numbers/
public class PrintIncreasingSubsequence
{
	public void print(int n,
			          int k)
	{
		int[] temp = new int[k];
		printRecursive(temp,
				       0,
				       1,
				       n);		
	}

	private void printRecursive(int[] temp,
			                    int currentIndex,
			                    int startNumber,
			                    int maxBoundary)
	{
		if (currentIndex == temp.length)
		{
			for(int i = 0 ; i < temp.length ; i++)
			{
				System.out.print(temp[i]+" ");
			}
			System.out.println();
			return;
		}
		else if (startNumber > maxBoundary)
		{
			return;
		}
		
		for(int i = currentIndex , j = startNumber;
		    j <=maxBoundary; 
			j++ )
		{
			temp[i] = j;
			printRecursive(temp,
					       i+1,
					       j+1,
					       maxBoundary);
		}
		
	}

	public static void main(String[] args)
	{
		new PrintIncreasingSubsequence().print(7,3);
	}

}

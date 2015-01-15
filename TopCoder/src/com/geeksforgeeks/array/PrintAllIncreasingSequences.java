package com.geeksforgeeks.array;

public class PrintAllIncreasingSequences
{
    public void print(int n,
    		          int k)
    {
    	int[] arr = new int[k];
    	printRecursive(1,
    			       0,
    			       arr,
    			       n);
    	
    }

	private void printRecursive(int currentElement,
			                    int currentIndex,
			                    int[] arr,
			                    int max)
	{
		if (currentElement > max)
		{
			return;
		}
		else if (currentIndex == arr.length)
		{
			for(int i = 0 ; i < arr.length ; i++)
			{
				System.out.println(arr[i]);
			}
			return;
		}
		arr[currentIndex] = currentElement;
		printRecursive(currentElement+1,
				       currentIndex+1,
				       arr,
				       max);
	}
	
	public static void main(String[] args)
	{
		new PrintAllIncreasingSequences().print(3, 2);
	}
}

package com.geeksforgeeks.array;
//http://www.geeksforgeeks.org/rearrange-array-alternating-positive-negative-items-o1-extra-space/
public class RearrangeArrayInAlternatingPosAndNegItems
{
	int[] arr = null;
	public void find(int[] arr)
	{
		this.arr = arr;
		int lastNegativeIndex = 0;
		int lastPositiveIndex = 0;
		for(int i = 0 ; i < this.arr.length ; i++)
		{
			if (i%2 == 0)
			{
				if (arr[i] > 0)
				{
					lastNegativeIndex = getNextNegativeIndex(i+1);	
				     if (lastNegativeIndex == -1 )
				     {
				    	 break;
				     }
				     else
				     {
				    	 int value = arr[lastNegativeIndex];
				    	 rightShift(i , lastNegativeIndex);
				    	 arr[i] = value;
				     }
				}
			}
			else
			{
				if (arr[i] < 0)
				{
				     lastPositiveIndex = getNextPositiveIndex(i+1);	
				     if (lastPositiveIndex == -1 )
				     {
				    	 break;
				     }
				     else
				     {
				    	 int value = arr[lastPositiveIndex];
				    	 rightShift(i , lastPositiveIndex);
				    	 arr[i] = value;
				     }
				}				
			}
		}
		
		for(int i = 0 ; i < arr.length ; i++)
		{
			System.out.println(arr[i]);
		}
	}
        	
    private int getNextPositiveIndex(int lastPositiveIndex)
    {
		for(int i = lastPositiveIndex ; i < this.arr.length ; i++)
		{
			if (this.arr[i] > 0)
			{
				return i;
			}
		}
		return -1;
	}

	private void rightShift( int first, int nextNegativeIndex) 
    {
        for(int i = nextNegativeIndex ; i > first ; i--)
        {
            this.arr[i] = this.arr[i-1];
        }
		
	}

	private int getNextNegativeIndex(int lastNegativeIndex)
    {
		for(int i = lastNegativeIndex ; i < this.arr.length ; i++)
		{
			if (this.arr[i] < 0)
			{
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) 
    {
		int[] arr = {-5, -2, 5, 2, 4, 7, 1, 8, 0, -8};
	    new RearrangeArrayInAlternatingPosAndNegItems().find(arr);
    }
}

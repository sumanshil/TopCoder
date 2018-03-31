package com.geeksforgeeks.array;
//http://www.geeksforgeeks.org/find-next-greater-number-set-digits/
public class FindNextGreaterNumberWithSameDigits 
{
	
	public int getNextGreaterDigit1(int[] digits)
	{
		int i ;
		for(i = digits.length-2 ; i>=0 ; i--)
		{
			if (digits[i] < digits[i+1])
			{
                break;				
			}
		}
		
		int min = Integer.MAX_VALUE;
		int element = digits[i];
		int j;
		int index=0;
		for(j = i+1 ; j < digits.length ; j++)
		{
			if (digits[j] > element && digits[j] < min)
			{		
				min = digits[j];
				index = j;
			}
		}
		swap(digits, i, index);
		reverse(digits, i+1, digits.length-1);
		
		int result = makeDigit(digits);
		return result;    		
				
	}
	
	
//    public int getNextGreaterDigit(int[] digits)
//    {
//    	boolean isAscending  = false;
//    	boolean isDescending = false;
//    	boolean needSpecialProcessing  = false;
//    	
//    	for(int i = 0 ; i < digits.length-1 ; i++)
//    	{
//    		if (digits[i] < digits[i+1])
//    		{
//    			if (isDescending)
//    			{
//    				needSpecialProcessing = true;
//    				break;
//    			}
//    			else
//    			{
//    				isAscending = true;
//    			}
//    		}
//    		else if (digits[i] > digits[i+1])
//    		{
//    			if (isAscending)
//    			{
//    				needSpecialProcessing = true;
//    				break;
//    			}
//    			else
//    			{
//    				isDescending = true;
//    			}    			
//    		}
//    	}
//    	
//    	if (isAscending)
//    	{
//    		swap(digits,digits.length-1, digits.length-2);
//    		int result = makeDigit(digits);
//    		return result;
//    	}
//    	else if (needSpecialProcessing)
//    	{
//    		int minVal = digits[digits.length-1];
//    		int index  = digits.length-1;
//    		int minIndex = digits.length-1;
//    		for(int i = digits.length-2 ; i >= 0 ; i--)
//    		{
//    			if (digits[i] < minVal)
//    			{
//    				index = i;
//    				break;
//    			}
//    		}
//    		
//    		swap(digits, index, minIndex);
//    		
//    		bubbleSort(digits, index+1, digits.length);
//    		
//    		int result = makeDigit(digits);
//    		return result;    		
//    		
//    	}
//    	return -1;
//    }
	
	private void reverse(int[] digits,
            			 int start,
            			 int end)
	{
		while(start < end)
		{
			swap(digits, start, end);
			start++;
			end--;
		}
	}
	
    
    private void bubbleSort(int[] digits,
    		                int start,
    		                int end) 
    {
		int l = end;
		int s = start;
		while(s < l)
		{
			int pivot = s;
			for(int i = s+1; i < l; i++)
			{
				if (digits[pivot] > digits[i])
				{
					swap(digits, pivot, i);
					pivot = i;
				}
			}
			l--;
		}
		
		
	}


	private int makeDigit(int[] digits)
    {
    	int result = 0;
    	int power  = 0;
        for(int i = digits.length-1 ; i>=0 ; i--)
        {
        	result += digits[i]*Math.pow(10, power++);
        }
        return result;
    }
    
	
	private void swap(int[] digits, int i, int j)
	{
	    int temp  = digits[i];
	    digits[i] = digits[j];
	    digits[j] = temp;		
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		int[] arr = {8,4,9,6,3};
		int result = new FindNextGreaterNumberWithSameDigits().getNextGreaterDigit1(arr);
		System.out.println(result);

	}

}

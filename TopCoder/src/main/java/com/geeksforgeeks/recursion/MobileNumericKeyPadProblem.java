package com.geeksforgeeks.recursion;
//http://www.geeksforgeeks.org/mobile-numeric-keypad-problem/
public class MobileNumericKeyPadProblem
{
	private int count = 0;
	int[][] matrix = {
			{0,8},//0
			{1,2,4},//1
			{1,2,3,5},//2
			{2,3,6},//3
			{4,1,5,7},//4
			{2,4,5,6,8},//5
			{3,5,6,9},//6
			{4,7,8},//7
			{0,5,7,8,9},//8
			{6,8,9},//9
	};

	public int count(int n)
	{
		int res = 0;
		for(int i = 0 ; i <=9 ; i++)
		{
			count = 0;
            countUtil(i, 0, n);
			res += count;
		}
		return res;
	}
	
	
	private void countUtil(int startNumber,
			              int currentIndex,
			              int n)
	{
		if (currentIndex >= n-1)
		{
			count++;
			return;
		}
		// get the neighboring numbers of the current number
		// put the neighboring numbers one by one to the next slot(currentIndex+1)
		int[] arr = matrix[startNumber];
		for(int i = 0 ; i < arr.length ; i++)
		{
			countUtil(arr[i], currentIndex+1, n);
		}
		
	}

	char[][] keypad = {
			{'1','2','3'},
			{'4','5','6'},
			{'7','8','9'},
			{'*','0','#'}
	};
	
	public int getCountDynamic(int n)
	{
	    if (keypad == null && n <= 0)
	        return 0;
	    if (n == 1)
	    	return 10;
	    int[] row = {0,0,-1,0,1};
	    int[] col = {0,-1,0,1,0};
	    
	    int[][] count = new int[10][n+1];
	    
	    int rowIndex = 0;
	    int colIndex = 0;
	    int digitCount = 0;
	    
	    // count numbers starting with digit i and of lengths 0 and 1
	    for(int i = 0 ; i < 10 ; i++)
	    {
	    	count[i][0] = 0;
	    	count[i][1] = 1;
	    }
	    
	    for(digitCount = 2; digitCount <=n ; digitCount++)
	    {
	    	for(rowIndex = 0 ; rowIndex < 4; rowIndex++ )
	    	{
	    		for(colIndex = 0 ; colIndex <3 ; colIndex++)
	    		{
	    			if (keypad[rowIndex][colIndex] != '*' && keypad[rowIndex][colIndex] != '#')
	    			{
	    				int num = keypad[rowIndex][colIndex]-'0';
	    				count[num][digitCount] = 0;
	    				for(int move = 0 ; move < 5 ; move++)
	    				{
	    					int newRow = rowIndex + row[move];
	    					int newCol = colIndex + col[move];
	    					if (newRow>=0 && 
	    						newRow <4 && 
	    						newCol >=0 && 
	    						newCol <3 &&
	    						keypad[newRow][newCol] != '*'&&
	    						keypad[newRow][newCol] != '#')
	    					{
	    						int nextNum = keypad[newRow][newCol]-'0';
	    						count[num][digitCount] += count[nextNum][digitCount-1];
	    					}
	    				}
	    			}
	    		}
	    	}
	    }
	    
	    int result = 0;
	    
	    for(int index = 0 ; index < 10 ; index ++)
	    {
	    	result += count[index][n];
	    }
	    
	    return result;
	}

	
	public int getCountDynamicApproach2(int n)
	{
		if (n <= 0)
			return 0;
		
		if (n == 1)
			return 10;
		
		int[] odd = new int[10];
		int[] even = new int[10];
		boolean useOdd = false;
		
		for(int i = 0 ; i <= 9 ; i++)
		{
			odd[i] = 1;
		}
//		char[][] keypad = {
//				{'1','2','3'},
//				{'4','5','6'},
//				{'7','8','9'},
//				{'*','0','#'}
//		};
		
		for(int digitCount = 2 ; digitCount <= n ; digitCount ++)
		{
			if (!useOdd)
			{
                even[0] = odd[0] + odd[8];
                even[1] = odd[1] + odd[2] + odd[4];
                even[2] = odd[1] + odd[2] + odd[3] + odd[5];
                even[3] = odd[3] + odd[2] + odd[6];
                even[4] = odd[1] + odd[4] + odd[5] + odd[7];
                even[5] = odd[2] + odd[4] + odd[5] + odd[6]+ odd[8];
                even[6] = odd[3] + odd[5] + odd[6] + odd[9];
                even[7] = odd[4] + odd[7] + odd[8];
                even[8] = odd[5] + odd[7] + odd[8] + odd[9]+ odd[0];
                even[9] = odd[6] + odd[8] + odd[9];
			}
			else
			{
                odd[0] = even[0] + even[8];
                odd[1] = even[1] + even[2] + even[4];
                odd[2] = even[1] + even[2] + even[3] + even[5];
                odd[3] = even[3] + even[2] + even[6];
                odd[4] = even[1] + even[4] + even[5] + even[7];
                odd[5] = even[2] + even[4] + even[5] + even[6]+ even[8];
                odd[6] = even[3] + even[5] + even[6] + even[9];
                odd[7] = even[4] + even[7] + even[8];
                odd[8] = even[5] + even[7] + even[8] + even[9]+ even[0];
                odd[9] = even[6] + even[8] + even[9];				
			}
			useOdd = !useOdd;
		}
		
		int result = 0;
		if (useOdd)
		{
			for(int i = 0 ; i <=9 ; i++)
			{
				result += even[i];
			}
		}
		else
		{
			for(int i = 0 ; i <=9 ; i++)
			{
				result += odd[i];
			}			
		}
		return result;
	}
	public static void main(String[] args)
	{
		int result = new MobileNumericKeyPadProblem().getCountDynamicApproach2(3);
		System.out.println(result);
	}

}

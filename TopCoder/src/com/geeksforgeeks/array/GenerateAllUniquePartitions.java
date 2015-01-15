package com.geeksforgeeks.array;
//http://www.geeksforgeeks.org/generate-unique-partitions-of-an-integer/
public class GenerateAllUniquePartitions {

	public void generate(int n){
		int[] arr = new int[n];
		int nonOneInts = 0;
		int front = 0;
		arr[0]= n;
		while(true)
		{
			nonOneInts = 0;
			print(arr);
			int i ;
			for(i =0 ; i< arr.length ;i++)
			{
				if ( arr[i] == 0 || arr[i] == 1 )
				{
					break;
				} 
				else 
				{
					nonOneInts++;
				}
			}
			if ( nonOneInts == 0 )
			{
				break;
			}
			if (arr[i-1] == 2 && arr[i] == 1)
			{
			    arr[i-1] = 1;
			    arr[++front] = 1;
			}
			else
			{
				arr[i-1] = arr[i-1]-1;
				arr[i] = arr[i]+1;
				if (front < i){
					front = i;
				}
			}
//			else if (arr[front+1] == 0)
//			{
//				arr[i-1]=arr[i-1]-1;
//				front++;
//			    arr[front]=1;	
//			}
//			else if (arr[front] == 1) 
//			{
//				arr[i-1]=arr[i-1]-1;
//				arr[i] =arr[i]+1;
//			}
			
		}
	}
	private void print(int[] arr)
	{
	    for(int i = 0 ; i < arr.length ; i++)
	    {
			if (arr[i] == 0)
			{
				break;
			}
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
	    int n = 6;
        new GenerateAllUniquePartitions().generate(n);        
	}

}

package com.geeksforgeeks.array;

public class KStackInSingleArray 
{

	private int free;
	private int[] arr;
	private int[] top;
	private int[] next;
	
	public KStackInSingleArray(int n,
			                   int k)
	{
		free = 0;
		top  = new int[k];
		arr  = new int[n];
		next = new int[n];
		for(int i = 0; i < k ; i++)
		{
			top[i] = -1;
		}
		
		for(int i = 0 ; i < n-1 ; i++)
		{
			next[i] = i+1;
		}
		next[n-1] = -1;
	}
	
	
	public void push(int sn,
			         int element)
	                 throws Exception
	{
		if (isFull())
		{
			throw new Exception("Stack is full");
		}
		int i= free;
		free = next[i];
		int k = top[sn];
		next[i] = k;
		top[sn] = i;
		arr[i]  = element;
		
	}
	
	
	public int pop(int sn) throws Exception
	{
		if (isEmpty(sn))
		{
			throw new Exception("Stack is Empty");
		}
		
		int i = top[sn];		
		top[sn]= next[i];
		next[i] = free;
		free = i;
		int retVal = arr[i];
		return retVal ;
	}
	
	
	
	private boolean isEmpty(int sn)
	{
		return top[sn] == -1;
	}


	private boolean isFull()
	{		
		return free == -1;
	}


	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception 
	{
		KStackInSingleArray kStack = new KStackInSingleArray(10, 3);
		kStack.push(0, 15);
		kStack.push(0, 25);
		kStack.push(0, 35);

		kStack.push(1, 45);
		kStack.push(1, 55);
		kStack.push(1, 65);

		kStack.push(2, 75);
		kStack.push(2, 85);
		kStack.push(2, 95);

		int i = 4;
		while(i-- > 0)
		{
			System.out.println(kStack.pop(0));
		}
		
	}

}

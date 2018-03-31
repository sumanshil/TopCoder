package com.concurrency;

import java.util.concurrent.Callable;

public class OrderedExecutor
{
	private int currentAllowedOrder = 0;
	private int maxLength = 0;
	public OrderedExecutor(int n)
	{
	    this.maxLength = n;
	}
	
	
	public synchronized Object execCriticalSectionInOrder(
			                                 int order,
			                                 Callable<Object> callable)
			                               throws Exception
	{
		if (order >= maxLength)
		{
			throw new Exception("Exceeds maximum order "+ maxLength);
		}
		while(order != currentAllowedOrder)
		{
			wait();
		}
		
		try
		{
			currentAllowedOrder = currentAllowedOrder+1;
			return callable.call();
		}
		finally
		{
			notifyAll();
		}
	}


	public static void main(String[] args)
	{
		int num = 50;
		OrderedExecutor1 executor = new OrderedExecutor1(num);
		for(int i = num-1 ; i >=0 ; i--)
		{
			final int fInt = i;
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					try
					{
						executor.execCriticalSectionInOrder(fInt, new Callable<Object>()
						{

							@Override
							public Object call() throws Exception
							{
								System.out.println("Hello I am "+fInt);
								return null;
							}
							
						});
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					
				}
			}, "Thread "+i).start();
		}

	}

}

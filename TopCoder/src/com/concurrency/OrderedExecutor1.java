package com.concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;

public class OrderedExecutor1
{
	private int maxOrder;
	private int currentAllowedOrder;
	private Map<Integer, Object> map = new HashMap<Integer, Object>();
    public OrderedExecutor1(int n)
    {
    	this.maxOrder = n;
    	this.currentAllowedOrder = 0;
    	for(int i = 0 ; i < this.maxOrder ; i++)
    	{
    		map.put(i, new Object());
    	}
    }
    
    public  Object execCriticalSectionInOrder(int order,
                                              Callable<Object> callable)
                                              throws Exception
    {
		if (order >= this.maxOrder)
		{
			throw new Exception("Exceeds maximum order "+ maxOrder);
		}
		synchronized (this.map.get(order))
        {
	        while(order != currentAllowedOrder)
	        {
	            synchronized (this.map.get(order))
	            {
	                this.map.get(order).wait(); 
	            }
	        }            
        }
		
		try
		{		
			return callable.call();			
		}
        finally
		{
            synchronized (this.map.get(order))
            {
                currentAllowedOrder = currentAllowedOrder+1;
                synchronized (this.map.get(order+1))
                {
                    this.map.get(order+1).notify();
                }                
            }
	    }
    }

	public static void main(String[] args)
	{
		int num = 100;
		OrderedExecutor executor = new OrderedExecutor(num);
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
							    Thread.sleep(new Random().nextInt(100));
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

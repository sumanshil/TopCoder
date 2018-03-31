package com.topcoder.problems.round152;

import java.util.Stack;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

//http://community.topcoder.com/stat?c=problem_statement&pm=1764&rd=4565
public class QuiningTopCoder 
{
	public String testCode(String source)
	{
		Stack<Integer> stack = new Stack<Integer>();
		int IP = 0;
		int D  = 1;
		for(int i = 0 ; i < source.length() ; i++)
		{
			char c  = source.charAt(i);
			if ( c >= '0' || c <= '9')
			{
				stack.push(c-'0');
			}
			else if ( c == '$')
			{
				stack.pop();
			}
			else if (c == ':')
			{
				
			}
		}
		return null;
	}

	public static void method1()
	{
		method2();
	}
	
	public static void method2()
	{
		try
		{
		    throw new NullPointerException();
		}
		catch(NullPointerException e)
		{

			StackTraceElement[] elements = 	e.getStackTrace();
			for(StackTraceElement element : elements)
			{
				System.out.println(element.toString());
			}
			
		}
	}

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException 
	{
//		// TODO Auto-generated method stub
//        Semaphore semaphore = new Semaphore(50);
//        try {
//			semaphore.acquire();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//        
//        semaphore.release();
//		
//		AtomicInteger integ = new AtomicInteger(0);
//		
//		while(true)
//		{
//			integ.incrementAndGet();
//			Thread.sleep(1000);
//			System.out.println(integ.intValue());
//			integ.decrementAndGet();
//		}
//		
//
		method1();
	}

}

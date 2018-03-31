package com.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintEvenAndOddInSequence
{
    private int current = 0;
    private Lock lock = new ReentrantLock();
    private Condition printOdd = lock.newCondition();
    private Condition printEven = lock.newCondition();
    
    public void printOdd()
    {
        lock.lock();
        try
        {
            while(current % 2 == 0)
            {
                printOdd.awaitUninterruptibly();
            }
            System.out.println("Hello "+current);
            current++;
            printEven.signal();
        }
        finally
        {
            lock.unlock();
        }
    }
    
    public void printEven()
    {
        lock.lock();
        try
        {
            while(current % 2 != 0)
            {
                printEven.awaitUninterruptibly();
            }
            System.out.println("Hello "+current);
            current++;
            printOdd.signal();
        }
        finally
        {
            lock.unlock();
        }
    }
    
    public static void main(String[] args)
    {
        PrintEvenAndOddInSequence obj = new PrintEvenAndOddInSequence();
       new Thread(new Runnable()
        {
            
            @Override
            public void run()
            {
                while(true)
                {
                    try
                    {
                        Thread.sleep(1000);
                    } catch (InterruptedException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    obj.printEven();
                }
                
            }
        }).start();    
        
        new Thread(new Runnable()
        {
            
            @Override
            public void run()
            {
                // TODO Auto-generated method stub
                while(true)
                {
                    try
                    {
                        Thread.sleep(1000);
                    } catch (InterruptedException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    obj.printOdd();
                }
            }
        }).start();
    }

}

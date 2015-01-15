package com.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosphersProblem
{
    private int count;
    private Lock[] forks;
    public DiningPhilosphersProblem(int size)
    {
        this.count = size;
        forks = new ReentrantLock[size];
        for(int i = 0 ; i < size ; i++)
        {
            forks[i] = new ReentrantLock();
        }        
    }
    
    public void eat(int index)
    {
        if (forks[index % this.count].tryLock())
        {
            System.out.println("Philospher "+index+" has acquired"+(index % this.count)+" fork");
            try
            {
                if (forks[(index+1)% this.count].tryLock())
                {
                    System.out.println("Philospher "+index+" has acquired"+((index+1) % this.count)+" fork");
                    try
                    {
                       System.out.println("Philospher "+index+" is eating"); 
                    }
                    finally
                    {
                        System.out.println("Philospher "+index+" is releasing"+((index+1) % this.count)+" fork");
                        forks[(index+1)% this.count].unlock();
                    }
                }
                else
                {
                    System.out.println("Philosoper "+index+" could not get "+((index+1)%count)+" fork");
                }
            }
            finally
            {
                System.out.println("Philospher "+index+" is releasing"+(index % this.count)+" fork");
                forks[index % this.count].unlock();
            }
        }
        else
        {
            System.out.println("Philosoper "+index+" could not get "+index+" fork");
        }
    }
    
    public static void main(String[] args)
    {
        int size = 5;
        DiningPhilosphersProblem table = new DiningPhilosphersProblem(size);
        for(int i = 0 ; i < size ; i++)
        {
            new PhilosopherThread(i, table).start();
        }

    }

}

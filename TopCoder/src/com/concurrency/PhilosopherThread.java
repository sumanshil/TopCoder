package com.concurrency;

import java.util.Random;

public class PhilosopherThread extends Thread
{
    int id;
    DiningPhilosphersProblem table;
    public PhilosopherThread(int id,
                             DiningPhilosphersProblem table)
    {
        this.id = id;
        this.table = table;
    }
    
    @Override
    public void run()
    {            
        while(true)
        {
            table.eat(id);
            try
            {
                Thread.sleep((long)new Random().nextInt(10000));
            } 
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

}

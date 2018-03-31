package com.concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairLock
{
    static class WaitRequest
    {
        public WaitRequest(Thread currentThread,
                           int lockRequest,
                           Condition newCondition)
        {
            this.thisThread = currentThread;
            this.lockRequest = lockRequest;
            this.condition = newCondition;
        }
        private Thread thisThread;
        private int lockRequest;
        private Condition condition;
    }
    private Queue<WaitRequest> queue = new LinkedList<WaitRequest>();
    private Lock lock = new ReentrantLock();
    
    public void lock() throws Exception
    {
        Condition condition = lock.newCondition();
        try
        {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+" is enqueueing request");
            if (!queue.isEmpty()
                    && queue.peek().thisThread == Thread.currentThread())
            {
                queue.peek().lockRequest++;
            }
            else
            {
                queue.add(new WaitRequest(Thread.currentThread(),1,
                    condition));
            }
            while(!queue.isEmpty() &&
                   queue.peek().thisThread != Thread.currentThread())
            {       
                System.out.println(Thread.currentThread().getName()+" will wait");
                condition.awaitUninterruptibly();
            }
        }
        finally
        {
            lock.unlock();
        }
    }
    
    
    public void unlock() throws Exception
    {
        try
        {
            lock.lock();
            //System.out.println(Thread.currentThread().getName()+" is unlocking");
            if (!queue.isEmpty())
                queue.peek().lockRequest--;
            
            
        }
        finally
        {
            if (!queue.isEmpty()
                    && queue.peek().lockRequest == 0)
            {
                queue.remove();
                System.out.println(Thread.currentThread().getName()+" leaving the scene");
                if (!queue.isEmpty())
                {
                    queue.peek().condition.signal();
                    System.out.println(queue.peek().thisThread+" is notified");
                }
                
            }
            
            lock.unlock();
        }
    }
    
    public static void main(String[] args)
    {
        int num = 50;
        FairLock fairLock= new FairLock();
        for(int i = num-1 ; i >=0 ; i--)
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        fairLock.lock();
                        test1();
                        System.out.println("I am executing  "+ Thread.currentThread().getName());
                        fairLock.unlock();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    
                }

                private void test1() throws Exception
                {
                    fairLock.lock();
                    System.out.println("I am executing inner lock "+ Thread.currentThread().getName());
                    fairLock.unlock();
                }
                
            }, "Thread "+i).start();
        }
       ForkJoinPool pool = new ForkJoinPool();
    }

}

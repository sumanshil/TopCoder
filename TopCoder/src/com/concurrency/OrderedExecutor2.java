package com.concurrency;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OrderedExecutor2<T>
{
    static class Waiter implements Comparable<Waiter>
    {
        private int order;
        private Condition condition;
        
        public Waiter(int order,
                      Condition condition)
        {
            this.order = order ;
            this.condition = condition;
        }
         
        @Override
        public int compareTo(Waiter o)
        {
            return this.order - o.order;
        }
        
    }
    
    private int nextOrder = 0;
    private Queue<Waiter> queue = new PriorityQueue<Waiter>();
    private final Lock  lock = new ReentrantLock();
    
    public T execCallableInOrder(int order,
                                 Callable<T> callable) throws Exception
    {
        System.out.println("Current thread" + Thread.currentThread().getName());
        awaitTurn(order);
        
        try
        {
            return callable.call();
        }
        finally
        {
            signalNext(order+1);
        }
    }
    
    
    private void signalNext(int nextOrder)
    {
        lock.lock();
        try
        {
            this.nextOrder = nextOrder;
            Waiter waiter = queue.peek();
            if(waiter != null &&
                    waiter.order == nextOrder )
            {
                queue.remove();
                waiter.condition.signal();
            }
        }
        finally
        {
            lock.unlock();
        }
    }


    private void awaitTurn(int order)
    {
        lock.lock();
        try
        {
           Condition condition = null;
           while(order != nextOrder)
           {
               if (condition == null)
               {
                   condition = lock.newCondition();
                   queue.add(new Waiter(order, condition));
               }
               condition.awaitUninterruptibly();
           }
        }
        finally
        {
            lock.unlock();
        }
    }


    public static void main(String[] args)
    {
        // TODO Auto-generated method stub

    }

}

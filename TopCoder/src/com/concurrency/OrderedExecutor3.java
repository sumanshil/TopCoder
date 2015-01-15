package com.concurrency;

import java.util.concurrent.Callable;

public class OrderedExecutor3
{
    static class MeetingPoint
    {
        int nextOrder;
        
        public synchronized void waitForGreen(int order) throws InterruptedException
        {
            while(order != nextOrder)
            {
                wait();
            }
        }
        
        public synchronized void signalGreen(int order)
        {
            this.nextOrder = order;
            notifyAll();
        }
    }
    
    private MeetingPoint[] meetingPoints;
    public OrderedExecutor3(int stripeSize)
    {
        meetingPoints = new MeetingPoint[stripeSize];
        for(int i = 0 ; i < stripeSize ; i++)
        {
            meetingPoints[i] = new MeetingPoint();
        }
    }
    
    public Object executeInOrder(int order,
                                 Callable callable)
                                 throws Exception
    {
        meetingPoints[(order) % meetingPoints.length].waitForGreen(order);
        try
        {
            return callable.call();
        }
        finally
        {
            meetingPoints[(order+1) % meetingPoints.length].signalGreen(order+1);
        }
    }
    
    
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub

    }

}

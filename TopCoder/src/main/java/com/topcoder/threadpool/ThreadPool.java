package com.topcoder.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ThreadPool
{
    private BlockingQueue<PipedData> taskQueue;
    private BlockingQueue<PipedData> nextQueue;
    private List<ExecuteInPipelineThread> list = new ArrayList<ExecuteInPipelineThread>();
    private int maxNoOfTasksInQueue = 0;
    private boolean isStopped = false;
    
    public ThreadPool(String name,
    		          int noOfThreads,
    		          int maxNoOfTasksInQueue)
    {
    	taskQueue = new LinkedBlockingDeque<PipedData>(maxNoOfTasksInQueue);
    	for(int i = 0 ; i < noOfThreads ; i++)
    	{
    		list.add(new ExecuteInPipelineThread(name+" : "+i, taskQueue));
    	}
    	
    	for(Thread thread : list)
    	{
    		thread.start();
    	}
    	this.maxNoOfTasksInQueue = maxNoOfTasksInQueue;
    }
    
    
    public BlockingQueue<PipedData> getNextQueue()
    {
    	if (nextQueue == null)
    	{
    		nextQueue = new LinkedBlockingDeque<PipedData>(maxNoOfTasksInQueue);
    		for(ExecuteInPipelineThread thread : list)
    		{
    			thread.setNextQueue(nextQueue);
    		}
    	}
    	return nextQueue;
    }
    
    
    public synchronized void execute(PipedData data) throws InterruptedException
    {
    	if (this.isStopped)
    	{
    		throw new  IllegalStateException("ThreadPool is stopped");
    	}
    	this.taskQueue.put(data);
    }
    
    
    public synchronized void stopExecution()
    {
    	isStopped = true;
    	for(ExecuteInPipelineThread thread : list)
    	{
    		thread.stopExecution();
    	}
    }
    
}

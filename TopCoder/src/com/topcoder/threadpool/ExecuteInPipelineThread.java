package com.topcoder.threadpool;

import java.util.concurrent.BlockingQueue;

public class ExecuteInPipelineThread extends Thread
{
	// this is the queue where this thread will get data from
	private BlockingQueue<PipedData> mainQueue;
	private BlockingQueue<PipedData> nextQueue;
	private boolean isStopped = false;
	
	public ExecuteInPipelineThread(String name, 
			                       BlockingQueue<PipedData> queue)
    {
		super(name);
    	this.mainQueue = queue;
    }
    
	
    protected void setNextQueue(BlockingQueue<PipedData> queue)
    {
    	this.nextQueue = queue;
    }
    
    
    public void run()
    {
    	while(!isStopped())
    	{
	    	try
			{
				execute();
			}
	    	catch (Exception e)
			{
				e.printStackTrace();
			}
    	}
    }
    
    
    public void  execute() throws Exception
    {
    	PipedData incomingData = mainQueue.take();
    	PipedData data = incomingData.call();
    	if (nextQueue != null)
    	{
    		nextQueue.put(data);
    	}
    }
    
    
    public synchronized void stopExecution()
    {
    	this.isStopped = true;
    	this.interrupt();
    }

    
    public synchronized boolean isStopped()
    {
    	return isStopped;    	
    }

}

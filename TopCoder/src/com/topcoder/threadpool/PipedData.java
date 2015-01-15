package com.topcoder.threadpool;

import java.util.concurrent.Callable;

public abstract class PipedData implements Callable<PipedData>
{
    
	public PipedData call() throws Exception
	{		
	    return execute();	
	}

	protected abstract PipedData execute();
}

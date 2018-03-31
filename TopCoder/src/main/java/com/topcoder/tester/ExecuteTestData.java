package com.topcoder.tester;

import java.lang.reflect.Method;

import com.topcoder.threadpool.PipedData;

public class ExecuteTestData extends PipedData
{

	private String className;
	private String methodName;
	private Object[] parameters;
	private Object  expectedValue;
	public ExecuteTestData(String className,
			                      String   methodName,
			                      Object[] inputParameters,
			                      Object expectedValue)
	{
		this.className  = className;
		this.methodName = methodName;
		this.parameters = inputParameters;
		this.expectedValue = expectedValue;
	}
	
	@Override
	protected PipedData execute()
	{

		return null;
	}

}

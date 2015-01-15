package com.topcoder.tester;

import com.topcoder.threadpool.PipedData;

public class ActualAndExpectedResultData extends PipedData
{
    private Class<?> dataType;
    private Object   expectedData;
    private Object   actualData;
    
    public ActualAndExpectedResultData(Class<?> dataType,
                                       Object   expectedData,
                                       Object   actualData)
	{
		this.dataType = dataType;
		this.expectedData = expectedData;
		this.actualData = actualData;
	}
    
	@Override
	protected PipedData execute()
	{
		ParserFactory parser = ParserFactory.getInstance(
				                             dataType.toString());
		boolean testResult   = parser.compare(expectedData,
				                              actualData);
		if (testResult)
		{
			System.out.println("===> PASSED");
		}
		else
		{
			System.out.println("===> FAILED");
			System.out.println("Expected result "+ expectedData);
			System.out.println("Actual   result "+ actualData);
		}
        return null;
		
	}

}

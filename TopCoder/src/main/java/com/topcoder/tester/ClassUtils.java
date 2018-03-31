package com.topcoder.tester;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ClassUtils
{
	public static Map<String, Object> classNameToClassInstance = new HashMap<String, Object>();
    public static Map<String, Method> classNameToMethod
                                       = new HashMap<String, Method>();
    public static Map<Method, Class<?>[] > methodToParamType = new HashMap<Method, Class<?>[]>();
	
    public static void setup(String className,
                             String methodName)
                             throws InstantiationException,
                                    IllegalAccessException,
                                    ClassNotFoundException
    {
		Object c = Class.forName(className).newInstance();
		classNameToClassInstance.put(className, c);
    	Method method = classNameToMethod.get(className);
    	
    	if (method == null)    		
    	{
    		Method[] methods = Class.forName(className).
                    getMethods();
    		Method   targetMethod      = null;
    		for(Method m : methods)
    		{
    			if (m.getName().equals(methodName))
    			{
    				targetMethod = m;
    				break;
    			}
    		}
    		
    		Class<?>[] parameterTypes  = targetMethod.getParameterTypes();
    		classNameToMethod.put(className, targetMethod);
    		methodToParamType.put(targetMethod, parameterTypes);
    	}
    	
    }
    
    
    public ActualAndExpectedResultData executeTestAndGetResult(
    		                           String className,
    		                           String methodName,
    		                           String input)
	                		           throws Exception
    {
    	Method method  = classNameToMethod.get(className);
    	Object classObject = classNameToClassInstance.get(className);
    	Class<?>[] parameterTypes = methodToParamType.get(method);
		Method     m   = Class.forName(className).
                                     getMethod(methodName,
                                     parameterTypes);
		Class<?> methodReturnType = m.getReturnType();
		Object[] inputParameters = TopCoderTester.processString(parameterTypes, input);
		Object[] expectedReturnValues = TopCoderTester.processString(new Class<?>[]{methodReturnType}, input);
        Object   result          = m.invoke(classObject, inputParameters);        
        return new ActualAndExpectedResultData(methodReturnType,
        		                               expectedReturnValues,
        		                               result);
    	
    }
}

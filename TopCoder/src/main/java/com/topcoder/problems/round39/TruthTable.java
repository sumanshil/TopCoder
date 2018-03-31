package com.topcoder.problems.round39;

import java.util.LinkedHashMap;
import java.util.Map;
//http://community.topcoder.com/tc?module=ProblemDetail&rd=4010&pm=222
public class TruthTable {
	Map<Integer, StringBuffer>  map =new LinkedHashMap<Integer, StringBuffer>();
	public String[] getTruthTable(String[] param)
	{
		int inputLength = 0;
		int index = 0;
		for(String str : param)
		{
			if ("i".equals(str))
			{
				inputLength++;
			}
			else if ('x' == (str.charAt(0)))
			{
				map.put(index, new StringBuffer());
			}
			index++;
		}
		int[] input = new int[inputLength];
		int[] all = new int[param.length];
		while(true)
		{
			// use input array
			for(int i = 0 ; i< input.length ; i++)
			{
				all[i] = input[i];
			}
			calculate(input, all, param);			
			// if all 1s break; 
			if (allOnes(input))
			{
				break;
			}
			
			for(int i = 0 ; i < input.length ; i++){
				if (input[i] == 0)
				{
					input[i] = 1;
					break;
				}
				else
				{
					input[i] = 0;					
				}
			}
		}
		String[] result = new String[map.entrySet().size()];
		int i = 0;
		for(Map.Entry<Integer, StringBuffer> entry : map.entrySet())
		{
			result[i++] = entry.getValue().toString(); 
		}
	
		return result;
	}
	
	private boolean allOnes(int[] input) 
	{
	    for(int i = 0 ; i< input.length ; i++)
	    {
	    	if (input[i] == 0)
	    	{
	    		return false;
	    	}
	    }
		return true;
	}

	private void calculate(int[] input, int[] all, String[] param)
	{
		int result=0;
	    for (int i = input.length; i < param.length; i++)
	    {
	    	String[] paramArr = param[i].split(":");
	    	String name = paramArr[0];
	    	String[] valueArr = paramArr[1].split(",");
	    	int[] operands = new int[valueArr.length];
	    	for(int j = 0 ; j < operands.length ; j++)
	    	{
	    		operands[j] = all[Integer.parseInt(valueArr[j])];
	    	}
	    	result = calculate(name, operands);
	    	if ("x".equals(name))
	    	{
	    		StringBuffer str = map.get(i);
	    		str.append(result);
	    		
	    	}
	    	all[i] = result;
	    }    	
	}

	private int calculate(String operator, int[] operands)
	{
		if ("n".equals(operator))
		{
			return operands[0] == 1? 0 : 1;
		}
		else if ("a".equals(operator))
		{
			return allOnes(operands)? 1 : 0;
		}
		else if ("o".equals(operator))			
		{
			return anyOne(operands) ? 1 : 0;
		}
		else if ("x".equals(operator))
		{
            return operands[0];			
		}
        return -1;
	}
	private boolean anyOne(int[] operands) {
		for(int i =0 ; i< operands.length ; i++)
		{
			if (operands[i] == 1)
			{
				return true;
			}
		}
		return false;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//String[] arr = {"i","i","i","n:0","a:1,2,3","x:4","x:0","x:3"};
		//String[] arr = {"i", "i", "i", "n:0", "a:1,2,3", "x:4", "x:3", "n:6", "x:7"};
		//String[] arr = 	{"i", "i", "a:0,1", "x:2"};
		//String[] arr = 	{"i", "x:0", "n:1", "x:2", "n:3", "x:4", "n:5", "x:6"};
		//String[] arr = 	{"i", "i", "i", "i", "i", "a:0,1,2,3,4", "o:0,1,2,3,4", "n:5", "n:6", "o:7,8", "x:9"}		;
		//String[] arr = 	{"i", "i", "o:0,1", "x:2"};
		//String[] arr = {"i", "n:0", "n:0", "n:0", "n:0", "n:0", "n:0", "n:0", "n:0", "n:0", "n:1", "x:10"}		;
		String[] arr = {"i", "i", "i", "n:0", "a:1,2,3", "x:4"};
		String[] result = new TruthTable().getTruthTable(arr);
		for(int i = 0 ; i < result.length ; i++)
		{
			System.out.println(result[i]);
		}

	}

}

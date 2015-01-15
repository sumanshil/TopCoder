package com.topcoder.problems.round144;
//http://community.topcoder.com/stat?c=problem_statement&pm=1704&rd=4515
public class BinaryCode
{
	private StringBuffer sb = new StringBuffer();
	
	
	public String[] decode(String message)
	{
		if (message.length() == 1)
		{
		    return processSingleDigitInput(message);
		}
		String[] retVal = new String[2];
		rec(0,  message.length()-2, message );
		retVal[0] = sb.toString();
		sb = new StringBuffer();
		rec(1,  message.length()-2, message );
		retVal[1] = sb.toString();
		
		return retVal;
	}
	
	
	private String[] processSingleDigitInput(String message) 
	{		
		if ("0".equals(message))
		{
			return new String[]{"0", "NONE"};
		}
		else if ("1".equals(message))
		{
			return new String[]{"NONE", "1"};
		}
		else
		{
			return new String[]{"NONE", "NONE"};
		}
	}
	
	
	//123210122
	private int rec(int value, int index, String message )
	{
        if ( index < 0)
        {
        	sb.append(value);
        	return value;
        }
		int val = rec (value, index-1, message);
		int i   = Character.getNumericValue(message.charAt(index));
		
		int j   = i - val;
        if ( index ==  message.length()-2 &&
        	!sb.toString().equals("NONE"))
        {
        	int temp = val;
        	if (index-1>=0)
        	{
        	   temp = val - Character.getNumericValue(sb.toString().charAt(index-1));
        	}
        	
        	if ( (temp+j) != Character.getNumericValue(message.charAt(index+1)))        		 
        	{
        		sb = new StringBuffer("NONE");
        	}
        }
		if (!"NONE".equals(sb.toString()) && (j ==0 || j == 1) )
		{
			if (index-1 >= 0)
			{
				val = val - Character.getNumericValue(sb.toString().charAt(index-1));
			}
		    sb.append(j);
		}
		else if (j > 1)
		{
			sb = new StringBuffer("NONE");
		}
		return val +j;
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
       String message = "111323012211013231000233331110223030100330"; 
       String[] res = new BinaryCode().decode(message);
       for(String s: res)
       {
    	   System.out.println(s);
       }
	}

}

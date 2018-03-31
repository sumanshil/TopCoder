package com.topcoder.problems.round154;

public class Regex 
{

	public boolean isMatch(String regex,
			               String input)
	{
		boolean result = f(regex, input, ' ');
		return result;
	}
	
	
	private boolean f(String regex,
			          String input,
			          char lastChar)
	{
		if (regex.length()==0 &&
			input.length() ==0)
		{
			return true;
		}
		else if (regex.length() == 1
				&& regex.charAt(0) == '*'
				&& input.length() ==0)
		{
			return true;
		}
		else if (input.length() == 0 
				&& regex.length() != 0)
		{
			return false;
		}
		
        if (regex.charAt(0)== input.charAt(0))
        {
        	return f(regex.substring(1),
        			 input.substring(1),
        			 input.charAt(0));
        }
        else if (regex.charAt(0)== '*'
        	     && input.charAt(0)==lastChar)
        {
        	return f (regex,
        			  input.substring(1),
        			  lastChar);
        }
        else if (regex.charAt(0)== '*'
   	     && input.charAt(0)!=lastChar)
	    {
	   	    return f (regex.substring(1),
	   			      input,
	   			      input.charAt(0));
	    }

		return false;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		String regex = "a*b*c";
        String input = "aaccc";
        boolean result = new Regex().isMatch(regex, input);
        System.out.println(result);
	}

}

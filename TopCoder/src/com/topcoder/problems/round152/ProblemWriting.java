package com.topcoder.problems.round152;
//http://community.topcoder.com/stat?c=problem_statement&pm=1763&rd=4565
public class ProblemWriting 
{

	public String myCheckData(String dotForm)
	{
		if ( dotForm.length() > 25)
		{
			 return "dotForm must contain between 1 and 25 characters, inclusive.";
		}
		else if ((dotForm.length() == 0)
				||(dotForm.length() > 0 && (!isNumber(dotForm.charAt(0)))))
		{
			return "dotForm is not in dot notation, check character 0.";
		}		
		
		boolean expectingDigit    = true;
		boolean expectingOperator = false;
		for(int i = 0 ; i < dotForm.length() ; i++)
		{
			char c = dotForm.charAt(i);
			if (expectingDigit && !isNumber(c) && !isDot(c))
			{
				return "dotForm is not in dot notation, check character "+(i)+".";
			}
			else if (expectingDigit && isNumber(c))
			{
				expectingDigit = false;
				if (i < dotForm.length()-1)
				{				    
				    expectingOperator = true;
				}					
			}
			else if (expectingOperator && !isOperator(c)&& !isDot(c))
			{
				return "dotForm is not in dot notation, check character "+(i)+".";
			}
			else if (expectingOperator && isOperator(c))
			{
				expectingDigit = true;
				expectingOperator = false;
			}
		}
		
		if (expectingDigit || expectingOperator)
		{
			return "dotForm is not in dot notation, check character "+dotForm.length()+".";
		}
		return "";
	}
	
	private boolean isOperator(char c)
	{
		return (c == '+'
			    ||c == '-'
			    ||c == '*'
			    ||c == '/');
	}
	
	private boolean isDot(char c)
	{
		return c == '.';
	}
	
	
	private boolean isNumber(char c)
	{
		return (c >='0' && c <='9');
	}
	
	
	/**
	 * @param args
	 */
	//"5.3+4"		"dotForm is not in dot notation, check character 2."		Passed
	public static void main(String[] args) 
	{
	    String str = new ProblemWriting().myCheckData("3+5");
	    System.out.println(str);

	}

}

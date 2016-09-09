package com.geeksforgeeks.pedning;
//Given a number N, inOrder the number of correct combination of parentheses possible.
//
//Input: N=2 => ()()
//Output: 2 [(()),()()]
//
//Input: N=3 => ()()()
//Output: 5 [()()(),((())),(())(),(()()),()(())]
//http://www.geeksforgeeks.org/print-all-combinations-of-balanced-parentheses/
public class ParenthesisProblem
{

	// n is the number of braces
	public static void print(int n)
	{
		printUtil(n, n , "");
	}
	
	private static void printUtil(int leftRemain,
			                      int rightRemain,
			                      String outputString)
	{
		if (rightRemain == 0)
		{
			System.out.println(outputString);
			return;
		}
		
		if (leftRemain > 0)
		{
		    // option 1 : print left braces
			printUtil(leftRemain - 1,
					  rightRemain,
					  outputString+"(");
            // option 2 : print right braces
			if (leftRemain < rightRemain)
			{
				printUtil(leftRemain,
						  rightRemain-1,
						  outputString+")");
			}
		}
		else
		{
			// print right braces
			printUtil(leftRemain,
					  rightRemain-1,
					  outputString+")");
		}
		
	}

	public static void main(String[] args)
	{
		print(3);
	}
}

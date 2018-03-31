package com.topcoder.problems.round158;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//http://community.topcoder.com/stat?c=problem_statement&pm=1789&rd=4598
public class BaseMystery
{
	public 	int[] getBase(String equation)
	{
		StringTokenizer st   = new StringTokenizer(equation, "+=");
		String firstOperand  = st.nextToken();
		String secondOperand = st.nextToken();
		String result        = st.nextToken();
		List<Integer> retVal = new ArrayList<Integer>();
		for(int base = 2 ;base <= 20 ; base++)
		{
			int a = getNumber(firstOperand, base);
			int b = getNumber(secondOperand, base);
			int c = getNumber(result, base);
			if (a == -1000
			    || b == -1000
			    || c == -1000)
			{
				continue;
			}
			if (a + b == c)
			{
				retVal.add(base);
			}
		}
		int[] finalResult = new int[retVal.size()];
		for(int i = 0 ; i < retVal.size() ; i++)
		{
			finalResult[i] = retVal.get(i);
		}
		return finalResult;
	}
	
	public int getNumber(String number,
			             int base)
	{
		int result = 0;
		for(int i = 0 ; i < number.length() ; i++)
		{
			char c = number.charAt(i);
			int v = 0;
			if ( c >= '0' && c <= '9')
			{
				v = c - '0';				
			}
			else 
			{
				v = c - 'A' + 10;
			}
			if ( v >= base)
				return -1000;
			result *= base;
			result += v;
		}
		return result;
	}
	

	public static void main(String[] args)
	{
		int[] result = new BaseMystery().getBase("ABCD+211=B000");
		for(int i = 0 ; i < result.length ; i++)
		{
			System.out.println(result[i]);
		}
	}
}

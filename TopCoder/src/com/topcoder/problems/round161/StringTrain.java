package com.topcoder.problems.round161;
//http://community.topcoder.com/stat?c=problem_statement&pm=1801&rd=4610
public class StringTrain
{
	public String buildTrain(String[] cars)
	{
		String finalString = cars[0];
		for(int i = 1; i < cars.length ; i++)
		{
			int prefixLength = 0;
			for(int j = 1 ; j < cars[i].length(); j++)
			{
				String prefix = cars[i].substring(0, j);
				if (checkIfPrefixOfCurrentIsSuffix(finalString, prefix))
				{
					prefixLength = prefix.length();
				}
			}
			if (isValidPrefix(prefixLength, finalString))
			{
				finalString += cars[i].substring(prefixLength);
			}
		}
		StringBuffer result = new StringBuffer();
		result.append(finalString.length()+" ");
		String truncatedString = "";
		for(int i =  finalString.length()-1 ; i >=0 ; i--)
		{
			if (ifCharacterIsNotPresent(truncatedString, ""+finalString.charAt(i))) 
			{
				truncatedString = finalString.charAt(i)+truncatedString;
			}
		}
		return result.toString()+truncatedString;
	}

	private boolean ifCharacterIsNotPresent(String truncatedString,
			                                String finalString)
	{		
		return !truncatedString.toString().contains(finalString);
	}

	private boolean isValidPrefix(int prefixLength, String finalString)
	{		
		return 	prefixLength > 0
				&& prefixLength != finalString.length();
	}

	private boolean checkIfPrefixOfCurrentIsSuffix(String finalString,
			                                       String prefix)
	{		
		return finalString.endsWith(prefix)
				&& prefix.length() != finalString.length();
	}

	public static void main(String[] args)
	{
		String result = new StringTrain().buildTrain(new String[]{"A","A","A","AA"});
		System.out.println(result);

	}

}

package com.geeksforgeeks.strings;
//http://www.geeksforgeeks.org/check-given-sentence-given-set-simple-grammer-rules/
public class CheckGivenSetForGivenSetOfGrammarRules
{
	public boolean isValidSentence(String str)
	{
		boolean isStartOfSentence = true;
		for(int i = 0; i < str.length() ; i++)
		{
			if (isStartOfSentence && 
				(str.charAt(i) >='A' && str.charAt(i) <='Z'))
			{
				isStartOfSentence = false;
				continue;
			}
			else if ((str.charAt(i) >='A' && str.charAt(i) <='Z'))
			{
				if ((i-1)>=0 &&
					 str.charAt(i-1) != ' '&&
					 !(i == str.length()-2))
				{
					return false;
				}
			}
     	    
			if (str.charAt(i) == ' ')
			{
				if ((i-1)>=0
					&& isValidCharacter(str, i-1))
				{
					continue;
				}
				return false;
			}
			
			if (str.charAt(i)=='.')
			{
				if (i == str.length()-1)
				{
				   if(!((i-1)>= 0 && 
							  isValidCharacter(str, i-1)))
				   {
					   return false;
				   }
				   continue;
				}
				else
				{
					return false;
				}
			}
			
			if (!isValidCharacter(str, i))
			{
				return false;
			}						
		}
		return true;
	}

	private boolean isValidCharacter(String str, int i)
	{
		if ((str.charAt(i)>='a'&& str.charAt(i)<='z')
				  || (str.charAt(i)>='A'&& str.charAt(i)<='Z'))
		{
			return true;
		}
		return false;
    }
	
//	public boolean checkState(String str)
//	{
//		int currentState = 0;
//		int prevState = 0;
//		
//		for(int i = 0 ; i < str.length() ; i++)
//		{
//			if (str.charAt(i)>='A'
//					&& str.charAt(i)<= 'Z')
//			{
//				currentState = 0;
//			}
//			else if (str.charAt(i) >= 'a'
//					&& str.charAt(i) <= 'z')
//			{
//				currentState = 1;
//			}
//			else if (str.charAt(i) == ' ')
//			{
//				currentState = 2;
//			}
//			else if (str.charAt(i) == '.')
//			{
//				currentState = 3;
//			}
//			
//			if (prevState == currentState )
//			{
//				
//			}
//			
//		}
//	}
	
	
	
	public static void main(String[] args)
	{
		String[] strArr = {"My name is KG.",
				           "I love cinema.",
				           "GeeksQuiz is a quiz site.",
				           "  You are my friend.",
				           "I love cinema ."};
		for(String str : strArr)
		{
            boolean result = new CheckGivenSetForGivenSetOfGrammarRules().
            		         isValidSentence(str);
            System.out.println(result);
		}
	}

}

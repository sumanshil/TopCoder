package com.topcoder.problems.round148;
//http://community.topcoder.com/stat?c=problem_statement&pm=1735&rd=4545
public class CircleGame 
{
	private char[] arr= {'A','2','3','4','5','6','7','8','9','T','J','Q','K'};
	public int cardsLeft(String deck)
	{
		while(true)
		{			
			int x = deck.length();
		    if ( x == 0 )
		    	break;
		    int j;
            while((j = getKIndex(deck))!= -1)
            {
            	if ( j > 0)
            	{
            		String s1 = deck.substring(0, j);
            		String s2 = deck.substring(j+1);
            		deck = s1 + s2;
            	}
            	else
            	{
            		deck = deck.substring(1);
            	}
            }
            x = deck.length();
		    if ( x == 0 )
		    	break;

			int x1 = getNum(deck.charAt(0));
			int x2 = getNum(deck.charAt(deck.length()-1));						
			if (x1 + x2 == 13)
			{
			    deck = deck.substring(1, deck.length()-1);	
			}
			
			for(int i = 0 ; i < deck.length()-1 ; i++)
			{
				x1 = getNum(deck.charAt(i));
				x2 = getNum(deck.charAt(i+1));
				if (x1 + x2 == 13)
				{
					deck = deck.substring(0,i)+deck.substring(i+2);
				}
			}
			
			int y = deck.length();
			if (x == y)
			{
				break;
			}
		}
		return deck.length();
	}
    
	
	private int getKIndex(String deck)
	{
		for(int i = 0 ; i < deck.length() ; i++)
		{
			if (deck.charAt(i) == 'K')
			{
				return i;
			}
		}
		return -1;
	}
	
	private int getNum(char c)
	{
		for(int i = 0 ; i< arr.length ; i++ )
		{
			if (arr[i] == c)
			{
				return i+1;
			}
		}
		return -1;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		int result = new CircleGame().cardsLeft("AQK262362TKKAQ6262437892KTTJA332");
		System.out.println(result);

	}

}

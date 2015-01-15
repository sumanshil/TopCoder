package com.topcoder.problems.round161;
//http://community.topcoder.com/stat?c=problem_statement&pm=1800&rd=4610
public class CardCount
{

	public String[] dealHands(int numPlayers, String deck)
	{
		String[] retVal = new String[numPlayers];
		for(int i = 0 ; i < numPlayers ; i++)
		{
			retVal[i] = "";
		}
		while(deck.length()>= numPlayers)
		{
			for(int i = 0 ; i < numPlayers ; i++)
			{
				retVal[i] += ""+deck.charAt(i);
			}
			deck = deck.substring(numPlayers);
		}
		return retVal;
	}
	
	
	public static void main(String[] args)
	{
	    String[] result = new CardCount().dealHands(4,
	    		                                    "111122223333");
	    for(String res : result)
	    {
	    	System.out.println(res);
	    }

	}

}

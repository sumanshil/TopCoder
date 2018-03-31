package com.topcoder.problems.round146;

public class MasterBrainTry 
{

	private String check(String code, String guess)
	{
		char[] b1 = code.toCharArray();
		char[] b2 = guess.toCharArray();
		int b = 0;
		int w = 0;
		
		for(int i =  0 ; i < b1.length ; i++)
		{
			if (b1[i] == b2[i])
			{
				b1[i] = 'x';
				b2[i] = 'y';
				b++;
			}
		}

		for(int i =  0 ; i < b1.length ; i++)
		{
			for(int j = 0 ; j < b2.length ; j++)
			{
				if (b1[i] == b2[j])
				{
					b1[i] = 'x';
					b2[j] = 'y';
					w++;
				}
			}
		}

		return b+"b "+w+"w";
	}
	
	
	public int possibleSecrets(String[] guesses, String[] results)
	{
		int result = 0;
        for(int i = 0 ; i < 7 ; i++)
        {
        	for(int j = 0 ; j < 7 ; j++)
        	{
        		for(int k = 0 ; k < 7 ; k++)
        		{
        			for(int l = 0 ; l < 7 ; l++)
        			{
        				int lie = 0;
        			    String code = ""+(i+1)+""+(j+1)+""+(k+1)+""+(l+1);
        			    for(int m = 0 ; m < guesses.length ; m++)        			    	
        			    {
        			    	if(!check(code, guesses[m]).equals(results[m]))
        			    	{
        			    		lie++;
        			    	}
        			    }
        			    if (lie == 1)
        			    {
        			    	result++;
        			    }
        			}        				
        		}
        	}
        }
		return result;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
       int result = new MasterBrainTry().possibleSecrets(new String[]{"1575"}, new String[]{"4b 0w"});
       System.out.println(result);
	}

}

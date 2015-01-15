package com.topcoder.problems.round145;
//http://community.topcoder.com/stat?c=problem_statement&pm=1728&rd=4530
public class DitherCounter 
{
    public int count(String dithered, String[] screen)
    {
        int screenCount = screen.length;
        int retVal      = 0;
        for(int i = 0 ; i < screenCount ; i++)
        {
        	for (int j = 0; j < screen[i].length() ; j++)
        	{
        		if (dithered.indexOf(screen[i].charAt(j))>=0)
        		{
        			retVal++;
        		}
        	}
        }
        return retVal;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
	    int result = new DitherCounter().count("BW",new String[]{"AAAAAAAA","ABWBWBWA","AWBWBWBA","ABWBWBWA","AWBWBWBA","AAAAAAAA"});
	    System.out.println(result);

	}

}

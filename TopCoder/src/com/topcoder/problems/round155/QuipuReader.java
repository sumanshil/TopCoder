package com.topcoder.problems.round155;


//http://community.topcoder.com/stat?c=problem_statement&pm=1694&rd=4580
public class QuipuReader 
{
	
    public int[] readKnots2(String[] knots)
    {
    	int[] values = new int[knots.length];
    	boolean[] last = new boolean[knots.length];
    	String[] result = new String[knots.length];
        for(int i = 0 ; i < result.length ; i++)
        {
        	result[i] = "";
        }
        boolean[] completed = new boolean[knots[0].length()];
        
    	for(int i = 0 ; i < knots[0].length() ; i++)
        {
        	for(int j =  0 ; j < knots.length ; j++)
        	{
        		if (knots[j].charAt(i) == 'X')
        		{
        			if (last[j] == false&&
        				!completed[i] &&
        				isNewCluster(j, i, knots))
        			{
        				for(int k = 0 ; k < values.length ; k++)
        				{
        			        result[k] += values[k];
        			        values[k] = 0;
        				}
        				completed[i] = true;
        			}
       				values[j]++;
    				last[j] = true;
        		}
        		else
        		{
        			last[j] = false;
        		}
        	}
        }
		for(int k = 0 ; k < values.length ; k++)
		{
	        result[k] += values[k];
	        values[k] = 0;
		}   
		int[] retVal = new int[values.length];
		int j = 0;
        for(String str : result)
        {
        	int r = 0;
        	int power = 0;
        	for(int i = str.length()-1; i>=0 ; i--)
        	{
        		char c= str.charAt(i);
        		int  val = c-'0';
        		r += val*Math.pow(10, power++);
        	}
        	retVal[j++] = r;
        	System.out.println(str);
        }
        return retVal;
    }



	private boolean isNewCluster(int knotIndex,
			                     int stringIndex,
			                     String[] knots) 
	{
        for(int i = 0 ; i < knots.length ; i++)
        {
        	if (i != knotIndex)
        	{
        		if(knots[i].charAt(stringIndex) == 'X'
        			&&(stringIndex-1 >= 0 && knots[i].charAt(stringIndex-1) == 'X'))
        		{
        			return false;
        		}
        	}
        }
		return true;
	}



	public int[] readKnots(String[] knots)
	{
		char[] arr = new char[knots[0].length()];
		for(int i = 0 ; i< arr.length ; i++)
		{
			arr[i] = '-';
		}
		for(String knot : knots)
		{
		    for(int i = 0 ; i < knot.length() ; i++)
		    {
		    	if (knot.charAt(i) == 'X'
		    		&& arr[i] != 'X')
		    	{
		    		arr[i] = 'X';
		    	}
		    }
		}
		
		int count = 0;
		char lastChar = '-';
		for(int i = 0 ; i < arr.length ; i++)
		{
			if (lastChar == 'X'&&
					arr[i] == '-')
			{
				count++;
			}
			lastChar = arr[i];
		}
		if (lastChar == 'X')
		{
			count++;
		}
		System.out.println(count);
		
		int[] result = new int[knots.length];		
		int   j = 0;
		for(String str : knots)
		{
			int number = 0;
			int   power = count-1;
			for(int i = 0 ; i < str.length() ; i++)
			{
				int start = 0;
				int end = 0;
				if (arr[i] == 'X')
				{
					start = i;
					
					while(i < arr.length && arr[i] != '-')
					{
						i++;
					}
					end = i-1;	
					int l = getNumberOfXs(str, start, end);
					number += Math.pow(10, power--)* l;
				}				
			}
			result[j] = number;
			j++;
		}
		
		return result;
	}
	
	
	private int getNumberOfXs(String str,
			                  int start,
			                  int end) 
	{
		int retVal = 0;
		for(int i = start ; i <= end ; i++)
		{
			if (str.charAt(i) == 'X')
			{
				retVal++;
			}
		}
		return retVal;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
//		String[] arr = { "-XXXXXXX--XX-----XXXXX---",
//				         "---XX----XXX-----XXXX----",
//		                 "-XXXXX---XXXXX--XXXXXXXX-" };
		
		//{ 725,  234,  558 }
//		String[] arr = { "XX---XXXX",
//				         "XXX----XX" };
		//{ 24,  31 }
		
//		String[] arr = { "-XXX---XX----X",
//				         "--X----X-XXXXX",
//		                 "-XX--XXXX---XX" };
		//{ 321,  115,  242 }
		
//		String[] arr = { "-------X--------",
//				         "--XXX----XXXX---",
//		                 "--------------XX" };
//		String[] arr = {"---------X----------------------",
//				        "--------------------------------",
//				        "---X----------------------------",
//				        "-----------X--------------------",
//				        "--X-----------------------------",
//				        "--------------X-----------------",
//				        "------------X-------------------",
//				        "------X-------------------------"};
//		String[] arr = 	{"XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXXX-XXXXXXXX-X",
//				         "XXXXXXXX---XXXXXXXX-XXXXXXXX---XXXXXXXX--XXXXXXX--",
//				         "------------XXX---------X-------XXXXXXX--XXXXXXX--",
//				         "-----XXX--XXXXXXXXX-----XXXX----XXX---------XXX--X"};
//		String[] arr = {"-X-X-X-X-", "XX----XXX"};
//		String[] arr = {"--XX--------XXXXXX---X-----XX--------",
//				        "--XXXXXXXX--XXXX-----XX----XXXXXXXXX-",
//				        "--XXXXXXXX-----------XXX---XXXXXXXXX-",
//				        "--XXXXX-----XXXXXXXX-XXX---XX--------",
//				        "--XXXXXXXXX-XXX------XXXXX-XXXXXXXXX-"};
//		String[] arr = {"-XXXXXXXXX-XX-------------XXXXXXX---",
//				        "-XXXXXX----XXXXXX---XXXX--XXX-------",
//				        "-XXXX------XXX------XXX---XXXX------",
//				        "-XXXXXXX------------X---------------",
//				        "-X---------XXXXX----XXXXX-XXXXXXXX--"};
//		String[] arr = {"-XXX-------XXXX------XXXXXXXX---XX-----",
//				        "-X-------------------XX---------X------",
//				        "-----------XXXXX-----XXX--------XXXXXX-",
//				        "-XX--------XXXXXXXXX-XX---------XXXX---",
//				        "-XXXXXXXX--XXXXXX----XXXXXX------------",
//				        "-X---------XXX-------XXXXXXXX---X------",
//				        "-XXX-----------------XX---------X------",
//				        "-XXXX------X---------X----------X------"};
		String[] arr = {"-XXXXX------XXXXXXX---XXXXXXX------XXX---", "-XX---------XXXXX--------XX---------X----", "-XXXX-------XXX------------------XXXXXXX-", "-XXX---------X--------XXXXXX-------------", "-XXXXXXXX--XXXXXXXXX----------------XX---", "-XXXXXX----XXXXXXXXX----------------X----", "-XXXXXX------X----------XXXX-------------", "-XXXXXX-----XXXXX-----XXXXXXXX-XXXXXXXXX-"};
		int[] result = new QuipuReader().readKnots2(arr);
		for(int i = 0 ; i < result.length ; i++)
		{
			System.out.println(result[i]);
		}
	}

}

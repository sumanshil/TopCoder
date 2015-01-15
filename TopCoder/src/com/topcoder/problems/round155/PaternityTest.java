package com.topcoder.problems.round155;
//http://community.topcoder.com/stat?c=problem_statement&pm=1669&rd=4580
import java.util.ArrayList;
import java.util.List;

public class PaternityTest
{
	int[] matchArray = null;
	int matchIndex  = 0;
    List<Integer> resultList = new ArrayList<Integer>();	

	public 	int[] possibleFathers(String child,
			                      String mother,
			                      String[] men)
	{
//	    matchArray = new int[child.length()];
//	    findUtil(child, mother, men, 0);
//	    Collections.sort(resultList);
//	    int[] retVal = new int[resultList.size()];
//	    for(int i = 0 ; i < resultList.size() ; i++)
//	    {
//	    	retVal[i] = resultList.get(i);
//	    }
//	    return retVal;
		return nonrecursive(child, mother, men);
	}
	
	
	private void findUtil(String child,
			              String mother,
			              String[] men,
			              int index)
	{
		if (matchIndex == child.length()/2)
		{
		    matchIndex = 0;
		    int kIndex = 0;
			for(String str : men)
			{
				boolean matchFound = true;
				for(int i = 0 ; i < str.length() ; i++)
				{
					if ( matchArray[i] == 0)
					{
						if (child.charAt(i) != str.charAt(i))
						{
							matchFound = false;
							break;
						}
					}
				}
				if (matchFound &&
						!resultList.contains(kIndex))
				{
					resultList.add(kIndex);
				}
				kIndex++;
			}
			
			for(int i = 0 ; i < matchArray.length ; i++)
			{
				matchArray[i] = 0;
			}
			return;
		}

		if (index >= child.length())
		{
		    matchIndex = 0;

			for(int i = 0 ; i < matchArray.length ; i++)
			{
				matchArray[i] = 0;
			}
			return;
		}
		for(int i = index ; i < child.length() ; i++)
		{
			if (child.charAt(i) == mother.charAt(i))
			{
				matchArray[i] = 1;
				matchIndex++;
				findUtil(child, 
						 mother, 
						 men, 
						 i+1);
			}
		}				
	}
	
	private int[] nonrecursive(String child,
                               String mother,
                               String[] men)
	{
		int[] rr = new int[men.length];
		int rIndex = 0;
		int j = 0;
		for(String man : men)
		{
			int b = 0;
			int mo = 0;
			int fo = 0;
			boolean ok = true;
			for(int i = 0 ; i < man.length() ; i++)
			{
				char c = child.charAt(i);
				char f = man.charAt(i);
				char m = mother.charAt(i);
				if (c == f && f == m)
				{
					b++;
				}
				else if (c == f)
				{
					fo++;
				}
				else if (c == m)
				{
					mo++;
				}
				else
				{
					ok = false;
					break;
				}
			}
			if (ok && mo <= men[0].length()/2 && fo <= men[0].length()/2)
			{
				rr[rIndex++] = j;
			}
			j++;
		}
		int[] res = new int[rIndex];
		for(int i = 0 ; i< rIndex ; i++)
		{
			res[i] = rr[i];
		}
		return res;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		int[] result= new PaternityTest().
		              possibleFathers("WXETPYCHUWSQEMKKYNVP",
		            		          "AXQTUQVAUOSQEEKCYNVP",
		            		          new String[]{"WNELPYCHXWXPCMNKDDXD", "WFEEPYCHFWDNPMKKALIW", "WSEFPYCHEWEFGMPKIQCK", "WAEXPYCHAWEQXMSKYARN", "WKEXPYCHYWLLFMGKKFBB"});

		for(int i = 0 ; i < result.length ; i++)
		{
			System.out.println(result[i]);
		}
	}

}

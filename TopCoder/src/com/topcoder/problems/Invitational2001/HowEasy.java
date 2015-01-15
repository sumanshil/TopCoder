package com.topcoder.problems.Invitational2001;
//http://community.topcoder.com/stat?c=problem_statement&pm=162&rd=50
public class HowEasy {
	public int pointVal(String problemStatement)
	{
		String[] strArr = problemStatement.split(" ");
		int wordCount=0;
		int totalWordLength=0;
		for(String str : strArr)
		{
			int count = checkAndCountWordLength(str);
			if  (count != -1)
			{
				totalWordLength += count;
				wordCount++;
			}
		}
		if (wordCount ==  0 )
		{
			return 250;
		}
		int point = totalWordLength/wordCount;
		int retVal = getPoint(point);
		return retVal;
	}
	
	private int checkAndCountWordLength(String str) 
	{
		int count = 0;
	    for(int i = 0 ; i < str.length() ; i++){
	    	char c = str.charAt(i);
	    	if ( (c >= 'a' && c<='z')
	    		  || ( c >= 'A' && c <= 'Z'))
	    	{
	    		count++;
	    	} else
	    	{
	    		if ( (i == str.length() - 1) 
	    			  && (c == '.'))
	    		{
	    			return count++;
	    		}
	    		else
	    		{
	    			return -1;
	    		}
	    	}
	    }
		return count;
	}

	private int getPoint(int point) 
	{
		if (point <= 3)
		{	
			return 250;
		}	
		else if (point == 4 || point == 5)
		{
			return 500;
		}
		else
		{
		    return 1000;	
		}		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
      //  String str = "This is a problem statement";
		//String str = "523hi";
		//String str = "Implement a class H5 which contains some method.";
		String str = " no9 . wor7ds he8re. hj..";
        int result = new HowEasy().pointVal(str);
        System.out.println(result);

	}

}

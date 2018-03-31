package com.topcoder.problems.Invitational2001.round3;
//http://community.topcoder.com/stat?c=problem_statement&pm=182&rd=52
public class BitChecker {

	private int getIntFromBinary(String str)
	{
		int retVal = 0;
		int pow = str.length()-1;
		for( int i = 0 ; i < str.length() ; i++)
		{
			double p = Math.pow(2, pow);
			char c = str.charAt(i);
			int value = getValue(c);
			retVal += (value * p);
			pow--;
		}		
		return retVal;
	}
	
	private int getLeftMostIndex(int number)
	{
		int length = 0;
		while(number > 0)
		{
			length++;
			number = number >> 1;
		}
		return length;
	}
	
	public String getResidue(String M, String K)
	{
		int m = 0;
		int k = 0;
		//double pow = Math.pow(2, M.length());
        k = getIntFromBinary(K);
        m = getIntFromBinary(M);
        int kLength = K.length();
        int result = 0;
        while(true)
        {
        	int leftMostIndex = getLeftMostIndex(m);
        	if ( leftMostIndex <= (kLength-1))
        	{
        	   int paddingLength;
        	   if (leftMostIndex == 0)
        	   {
        		   paddingLength = kLength-2;
        	   }
        	   else
        	   {
        		   paddingLength =  (kLength-1)-leftMostIndex;
        	   }
        	   
        	   StringBuffer sb = new StringBuffer();
        	   while(paddingLength > 0)
        	   {
        	       sb.append("0");
        	       paddingLength--;
        	   }
        	   sb.append(Integer.toBinaryString(m));
        	   return sb.toString();
        	}        	
        	else if ( leftMostIndex == 0)
        	{
        		result = 0;
        		break;
        	}
        	
        	int diff = leftMostIndex - kLength;
            int shiftedVal = k << diff;
            System.out.println("Original value "+ Integer.toBinaryString(m) + " Length "+Integer.toBinaryString(m).length());
            System.out.println("Shifted value "+ Integer.toBinaryString(shiftedVal) + " Length "+Integer.toBinaryString(shiftedVal).length());
            m = m ^ shiftedVal;       
            System.out.println("Residue "+ Integer.toBinaryString(m));
        } //end while		
        return null;
	}
	private int getValue(char c)
	{		
		return (c == '1') ? 1 : 0;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
//		String k = "11";
//		String m = "111101";
		String k = "10011";
		//String m = "10011010010011";
		String m = "100111001110011";
        String result = new BitChecker().getResidue("11111111111111111111111111111111111111111111111111", "11");
        
        System.out.println(result);
	}

}

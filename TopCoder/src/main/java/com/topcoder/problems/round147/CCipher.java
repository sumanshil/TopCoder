package com.topcoder.problems.round147;

public class CCipher
{
    public  String decode(String cipherText, int shift)
    {
    	StringBuffer sb = new StringBuffer(cipherText.length());
    	for(int i = 0 ; i < cipherText.length() ; i++)
    	{
    		char c = cipherText.charAt(i);
    		int  j = (int)c;
    		int  k = j -shift;
    		char r;
    		if ( k < 65)
    		{
    			int l = 90 - ((65-k)-1);
    			r = (char)l;
    		}
    		else
    		{
    			r = (char)k;
    		}
    		sb.append(r);
    	}
    	return sb.toString();
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        System.out.println((int)'A');
        System.out.println((int)'Z');
        String result = new CCipher().decode("ABCDEFGHIJKLMNOPQRSTUVWXYZ",10);
        System.out.println(result);
	}

}

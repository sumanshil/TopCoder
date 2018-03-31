package com.topcoder.problems.round167;
//http://community.topcoder.com/stat?c=problem_statement&pm=1822&rd=4701
public class LetterFreq
{

    public int[] getFreqs(String[] doc)
    {
        int[] retVal = new int[26];
        for ( String docString : doc)
        {
            for ( int i = 0 ;
                  i < docString.length() ;
                  i++ )
            {
                char c = docString
                        .toLowerCase()
                        .charAt(i);
                if ( c >= 'a' && c <='z' )
                {
                    retVal[c-'a'] = retVal[c-'a'] + 1;
                }
            }
        }
        return retVal;
    }
    public static void main(String[] args)
    {
        String[] str = { "Be sure to ignore case", "and non-letter characters"};
        int[] result = new LetterFreq().getFreqs(str);
        for ( int i : result )
        {
            System.out.println(i);
        }

    }

}

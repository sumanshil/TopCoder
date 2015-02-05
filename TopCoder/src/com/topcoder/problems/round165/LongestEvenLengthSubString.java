package com.topcoder.problems.round165;

public class LongestEvenLengthSubString
{
    public int simpleSolution(String str)
    {
        int maxLength = Integer.MIN_VALUE;
        // O(N^3)
        for ( int i = 0 ; i < str.length()-1; i++ )
        {
            for ( int j = i+1 ; j < str.length() ; j++ )
            {
                if ( ((j-i)+1) % 2 == 0 )
                {
                    int mid = ((j-i)/2);
                    int leftSum = 0;
                    for ( int k = 0 ; k <= mid ; k++ )
                    {
                        leftSum += Integer.parseInt(""+str.charAt(k));
                    }
                    
                    int rightSum = 0;
                    for ( int k = mid+1 ; k <= j ; k++ )
                    {
                        rightSum += Integer.parseInt(""+str.charAt(k));
                    }
                    if (leftSum == rightSum)
                    {
                        if (((j-i)+1) > maxLength)
                        {
                            maxLength = (j-i)+1;
                        }
                    }
                }
            }
        }
        return maxLength;
    }
    
    
    public static void main(String[] args)
    {
        String str = "123123";
        int result = new LongestEvenLengthSubString().simpleSolution(str);
        System.out.println(result);

    }

}

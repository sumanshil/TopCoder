package com.topcoder.problems.round163;
//http://community.topcoder.com/stat?c=problem_statement&pm=1809&rd=4620
public class Pool
{
    public int rackMoves(int[] triangle)
    {
        int sw = 0;
        if ( triangle[4] != 8 )
        {
            for ( int i = 0 ; i < 15 ; i++)
            {
                if ( triangle[i] == 8 )
                {
                    triangle[i] = triangle[4];
                    triangle[4] = 8;                    
                }
            }
            sw = 1;
        }
        int sox = 0;
        
        if (triangle[0] < 8) 
            sox++;
        if (triangle[3] < 8) 
            sox++;
        if (triangle[5] < 8) 
            sox++;        
        if (triangle[9] < 8) 
            sox++;
        if (triangle[7] < 8) 
            sox++;
        if (triangle[10] < 8) 
            sox++;
        if (triangle[12] < 8) 
            sox++;

        if (sox < 4)
            return sox + sw;
        else
            return (7-sox) + sw;
    }
    
    
    public static void main(String[] args)
    {
        int result = new Pool().
                     rackMoves(
                     new int[]{8, 15, 9, 4, 10, 6, 11,
                               3, 14, 7, 2, 1, 13, 12, 5});
        System.out.println(result);
    }

}

package com.topcoder.problems.round163;
//http://community.topcoder.com/stat?c=problem_statement&pm=1799&rd=4620
public class Inchworm
{
    public int lunchtime(int branch, int rest, int leaf)
    {
        int[] branchArr = new int[branch+1];
        
        for ( int i = 0  ; i <= branch ; i+=leaf )
        {
            branchArr[i] = 1;
        }
        
        int result = 0;
        
        for ( int i = 0 ; i <= branch ;  i+=rest )
        {
            if ( branchArr[i] == 1 )
            {
                result++;
            }
        }
        return result;
    }
    
    public static void main(String[] args)
    {
        int result = new Inchworm().lunchtime(1000, 7, 3);
        System.out.println(result);
    }

}

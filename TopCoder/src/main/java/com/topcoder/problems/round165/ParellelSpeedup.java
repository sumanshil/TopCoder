package com.topcoder.problems.round165;

//http://community.topcoder.com/stat?c=problem_statement&pm=1866&rd=4630
public class ParellelSpeedup
{
    public int numProcessors(int k, int overhead)
    {
        int totalTime = 0;
        int minTime = k;
        int retVal  = 1;
        for ( int i = 2 ; i < 10000 ; i++ )
        {
            totalTime = (overhead * (i*(i-1))/2) + k/i + (k%i != 0 ? 1 : 0);  
            if (totalTime < minTime)
            {
                minTime = totalTime;
                retVal = i;
            }
        }
        return retVal;
    }
    
    
    public static void main(String[] args)
    {
        int result = new ParellelSpeedup().numProcessors(9, 10);
        //new ParellelSpeedup().calculateProcessorPairNo(34);
        System.out.println(result);

    }

}

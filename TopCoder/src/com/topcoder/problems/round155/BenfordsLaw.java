package com.topcoder.problems.round155;
//http://community.topcoder.com/stat?c=problem_statement&pm=1348&rd=4580
public class BenfordsLaw
{

    public int questionableDigit(int[] transactions,
                                 int threshold)
    {
        int[] count = new int[10];
        
        for(int i = 0 ; i < transactions.length ; i++)
        {
            int number = transactions[i];
            while(number > 9)
            {
                number = number/10;
            }
            count[number]++;
        }
        int n = transactions.length;
        for(int i = 1; i < 10 ; i++)
        {
            if (count[i] < ((double)(n*Math.log10(1.0+1.0/i)))/(double)threshold)
                
            {
                return i;
            }
            else if ((double)count[i] > ((double)(n*Math.log10(1.0+1.0/i)))*(double)threshold)
            {
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args)
    {
        int[] arr ={ 9,87,765,6543,54321,43219,321987,21987,1987,345,234,123 };
        int threshold = 2;
        int result = new BenfordsLaw().questionableDigit(arr, threshold);
        System.out.println(result);

    }

}

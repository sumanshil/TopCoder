package com.topcoder.problems.round157;
//http://community.topcoder.com/stat?c=problem_statement&pm=1790&rd=4590
public class GuessTheNumber
{

    public int noGuesses(int upper,
                         int answer)
    {
       int result = recursive(1,
                              upper,
                              answer,
                              1);
       return result;
    }
    
    private int recursive(int lower,
                          int upper,
                          int answer,
                          int count)
    {
        if (lower > upper)
            return -1;
        int mid = (lower + upper)/2;
        if (mid == answer)
        {
            return count;
        }
        
        if (mid > answer)
        {
            return recursive(lower,
                             mid-1, 
                             answer,
                             count+1);
        }
        else
        {
            return recursive(mid+1,
                    upper, 
                    answer,
                    count+1);            
        }
    }
    
    public static void main(String[] args)
    {
        int upper = 128;
        int guess = 64;
        int result = new GuessTheNumber().noGuesses(upper, guess);
        System.out.println(result);
    }

}

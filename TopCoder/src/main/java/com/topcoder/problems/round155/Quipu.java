package com.topcoder.problems.round155;
//http://community.topcoder.com/stat?c=problem_statement&pm=1686&rd=4580
public class Quipu
{
    public int readKnots(String knots)
    {        
        int xCount = 0;
        int dashCount = 0;
        char lastChar = ' ';
        int result = 0;
        for(int i = 0 ; i < knots.length() ; i++)
        {
            if (knots.charAt(i) == 'X')
            {
                xCount++;
            }
            else
            {
                dashCount++;
            }
     
            
            if (lastChar != 'X' &&
                knots.charAt(i) == 'X')
            {
                if (dashCount > 1)
                {
                    result = (int)(result * Math.pow(10, dashCount-1));                    
                }   
                dashCount = 0;
            }
            else if (lastChar == 'X'
                    && knots.charAt(i) != 'X')
            {
                result = (result *10)+(xCount);
                xCount = 0;                
            }
            lastChar = knots.charAt(i);
        }
        
        if (xCount > 0)
        {
            result = (result *10)+(xCount);
        }
        else if (dashCount > 1)
        {
            result = (int)(result * Math.pow(10, dashCount-1));
        }
        return result;
    }
    public static void main(String[] args)
    {
//        String str = "-XXXXXXXXX--XXXXXXXXX-XXXXXXXXX-XXXXXXX-XXXXXXXXX-";
//        int result = new Quipu().readKnots(str);
//        System.out.println(result);
        
        System.out.println(Math.log ( 1.0+1.0/1 ) / Math.log(10.0));
    }

}

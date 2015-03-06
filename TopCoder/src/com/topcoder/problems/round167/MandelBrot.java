package com.topcoder.problems.round167;
//http://community.topcoder.com/stat?c=problem_statement&pm=1172&rd=4701
public class MandelBrot
{
    public int iterations(int max, double a, double b)
    {
        double realPart = a;
        double imaginaryPart = b;
        double currentResult = Math.sqrt(a*a + b*b);
        if ( currentResult >= 2)
        {
            return 0;
        }
        for ( int i = 1 ; i <= max ; i++ )
        {
           double realMultiply =  getRealPartForMultiplication(realPart,
                                                               imaginaryPart,
                                                               a,
                                                               b);
           double imaginaryMultiply = getImaginaryPartForMultiplication(realPart,
                                                                         imaginaryPart,
                                                                         a,
                                                                         b);
           
           double realAddition = getRealPartForAddition(realMultiply,
                                                        imaginaryMultiply,
                                                        a,
                                                        b);
           double imaginaryAddition = getImaginaryPartForAddition(realMultiply,
                                                                  imaginaryMultiply,
                                                                  a,
                                                                  b);
           double result = Math.sqrt(realAddition*realAddition
                                    + imaginaryAddition*imaginaryAddition);
           if (result >= 2)
           {
               return i;
           }
           realPart = realMultiply+a;
           imaginaryPart = imaginaryMultiply+b;           
        }
        return -1;
    }
    
    
    private double getImaginaryPartForAddition(double a,
                                               double b,
                                               double c,
                                               double d)
    {        
        return b+d;
    }
    
    
    private double getRealPartForAddition(double a,
                                          double b,
                                          double c,
                                          double d)
    {
        return a+c;        
    }
    
    
    private double getImaginaryPartForMultiplication(double a,
                                                     double b,
                                                     double c,
                                                     double d)
    {       
        return ((a*d) + (b*c));
    }
    
    
    private double getRealPartForMultiplication(double a,
                                                double b,
                                                double c,
                                                double d)
    {
        return ((a*c) - (b*d));
    }
    
    
    public static void main(String[] args)
    {
        int result = new MandelBrot().iterations(30, -1.9, 0);
        System.out.println(result);
    }

}

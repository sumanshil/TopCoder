package com.topcoder.problems.round163;
//http://community.topcoder.com/stat?c=problem_statement&pm=1847&rd=4620
public class CalendarRecycle
{
    public int useAgain(int year) 
    {
        boolean isInputYearALeapYear = isLeapYear(year);
        int initialDay = 0;
        int progression = 0;
        for ( int i = year+1 ;  ; i++)
        {
            int totalDay = 0;
            if ( isLeapYear(i) )
            {
                totalDay = 366;
            }
            else
            {
                totalDay = 365;
            }
            
            int advance = (totalDay % 7);
            progression = (progression+advance) % 7;
            if ( progression == initialDay )
            {
                boolean isThisYearALeapYear = isLeapYear(i);
                if ( isThisYearALeapYear == isInputYearALeapYear )
                {
                    return i;
                }
            }            
        }
    }
    
    
    private boolean isLeapYear(int year)
    {        
        return ( ((year % 4 == 0) 
              && (year % 100 != 0 ))
              || (year % 400 == 0));
    }
    
    
    public static void main(String[] args)
    {
        int result = new CalendarRecycle().useAgain(2008);
        System.out.println(result);
    }

}

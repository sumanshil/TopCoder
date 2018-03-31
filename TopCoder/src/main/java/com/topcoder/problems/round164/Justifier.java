package com.topcoder.problems.round164;

import java.util.LinkedList;
import java.util.List;
//http://community.topcoder.com/stat?c=problem_solution&cr=280275&rd=4625&pm=1757
public class Justifier
{
    public String[] justify(String[] textIn)
    {
        int maxLength = Integer.MIN_VALUE;
        for ( int i = 0 ; i < textIn.length ; i++ )
        {
            if ( textIn[i].length() > maxLength )
            {
                maxLength = textIn[i].length();
            }
        }
        
        List<String> retVal = new LinkedList<String>();
        for ( int i = 0 ; i < textIn.length ; i++)
        {
            int diff = maxLength - textIn[i].length();
            String spaces = getSpaces(diff);
            retVal.add(spaces+textIn[i]);
        }
        return (String[])retVal.toArray(new String[0]);
    }
    
    
    private String getSpaces(int diff)
    {
        StringBuffer sb = new StringBuffer(diff);
        for ( int i = 0 ; i < diff ; i++ )
        {
            sb.append(' ');
        }
        return sb.toString();
    }
    
    
    public static void main(String[] args)
    {
        String[] arr = new Justifier().justify(new String[]{"BOB","TOMMY","JIM"});
        for (  String str : arr )
        {
            System.out.println(str);
        }

    }

}

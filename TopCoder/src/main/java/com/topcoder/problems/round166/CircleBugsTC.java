package com.topcoder.problems.round166;

import java.util.HashMap;
import java.util.Map;

//http://community.topcoder.com/stat?c=problem_statement&pm=1555&rd=4635
public class CircleBugsTC
{
    public  int cycleLength(String formation)
    {
        Map<String, Integer> map = new HashMap<String, Integer>();
        int index = 0;
        map.put(formation, 0);
        index++;
        while(true)
        {
            String nextString = getNext(formation);
            String canonical = canonical(nextString);
            Integer oldIndex = map.put(canonical, index);
            if ( oldIndex != null )
            {
                return index - oldIndex;
            }
            formation = nextString;
            index++;
        }
    }
    
    
    private String canonical(String string)
    {
        String min = "Z";
        for ( int i = 0 ; i < string.length() ; i++)
        {
            String s = string.substring(i)+string.substring(0, i);            
            if ( min.compareTo(s) > 0 )
            {
                min = s;
            }
        }
        return min;
    }


    private String getNext(String formation)
    {
        StringBuffer sb = new StringBuffer();
        for ( int i = 0 ; i < formation.length() ; i++)
        {
            if (formation.charAt(i%formation.length())
                    == formation.charAt((i+1)%formation.length()))
            {
                sb.append("R");        
            }
            else
            {
                sb.append("G");
            }
        }
        return sb.toString();
    }
    
    
    public static void main(String[] args)
    {
        int result = new CircleBugsTC().cycleLength("RRG");
        System.out.println(result);
        

    }

}

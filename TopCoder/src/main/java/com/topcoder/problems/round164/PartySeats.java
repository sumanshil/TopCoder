package com.topcoder.problems.round164;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

//http://community.topcoder.com/stat?c=problem_statement&pm=1854&rd=4625
public class PartySeats
{
    public String[] seating(String[] attendees)
    {
        if (attendees.length <= 2
            || attendees.length % 2 != 0)
        {
            return new String[]{};
        }
        Map<String, List<String>> map = extractBoysAndGirlsInSortedArray(attendees);
        List<String> girlsSet = map.get("GIRL");
        List<String> boysSet  = map.get("BOY");
        if ( girlsSet.size() != boysSet.size() 
                || boysSet.size() % 2 != 0 )
        {
            return new String[]{};
        }
        String[] girls = (String[])girlsSet.toArray(new String[0]);
        String[] boys =  (String[])boysSet.toArray(new String[0]);
        String[] retVal = new String[attendees.length+2];
        int hostessIndex = attendees.length/2+1;
        retVal[0] = "HOST";
        int girlsListIndex = 0;
        int boysListIndex = 0;
        for(int i = 1 ; i < retVal.length ; i++)
        {
            if (i == hostessIndex)
            {
                retVal[i] = "HOSTESS";
            }
            else if ( i%2 != 0)
            {
                retVal[i] = girls[girlsListIndex++];
            }
            else 
            {
                retVal[i] = boys[boysListIndex++];
            }
        }
        return retVal;
    }
    
    
    private Map<String, List<String>> extractBoysAndGirlsInSortedArray(
                                      String[] attendees)
    {
        List<String> boys = new LinkedList<String>();
        List<String> girls = new LinkedList<String>();
        for ( String str : attendees )
        {
            String[] arr = str.split("\\s+");
            String name = arr[0];
            if (arr[1].equals("boy"))
                boys.add(name);
            else
                girls.add(name);
        }
        Collections.sort(boys);
        Collections.sort(girls);
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("BOY", boys);
        map.put("GIRL", girls);
        return map;
    }


    public static void main(String[] args)
    {
        String[] arr = {"AM girl", "ROB boy", "JIM boy", "AM girl", "DAVE boy", "JO girl"};
        String[] result = new PartySeats().seating(arr);
        for ( int i = 0 ; i < result.length ; i++)
        {
            System.out.println(result[i]);
        }
    }

}

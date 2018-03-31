package com.topcoder.problems.round166;
//http://community.topcoder.com/stat?c=problem_statement&pm=1555&rd=4635
import java.util.HashMap;
import java.util.Map;

public class CircleBugs
{ 
    Map<String, Integer> map = new HashMap<String, Integer>();
    public int cycleLength(String formation)
    {
        StringBuffer nextString = new StringBuffer(formation.length());
        int length = formation.length();
        boolean continueLooping = true;
        int rotateIndex = 0;
        map.put(formation, rotateIndex);
        while(continueLooping)
        {
            int i = 0;
            rotateIndex++;
            while( i < formation.length())
            {
                char c1 = formation.charAt(i%length);
                char c2 = formation.charAt((i+1)%length);
                if (c1 == c2)
                {
                    nextString.append("R");
                }
                else
                {
                    nextString.append("G");
                }                
                i++;
            }
            for (Map.Entry<String, Integer> entry : map.entrySet())
            {
                String oldString = entry.getKey();
                if (    oldString.intern().equals(nextString.toString().intern())
                        || isIdenticalViaRotation(oldString, nextString.toString())
                        || isIdenticalViaRotation(nextString.toString(), oldString)
                        || isIdenticalViaRotationAndReverse(oldString, nextString.toString())
                        || isIdenticalViaRotationAndReverse(nextString.toString(), oldString ))
                {
                    return rotateIndex - entry.getValue();
                }
            }

            map.put(nextString.toString(), rotateIndex);
            formation = nextString.toString();
            nextString = new StringBuffer(formation.length());
        }
        return -1;
    }
    
    
    private boolean isIdenticalViaRotation(String string1, String string2)
    {
        String rotatedString = string1;
        for(int i = 0 ; i < string1.length() ; i++)
        {
            if (rotatedString.intern().equals(string2.intern()))
            {
                return true;
            }
            rotatedString = rotate(rotatedString);
        }
        return false;
    }
    
    
    private String rotate(String original)
    {
        return original.charAt(original.length()-1)+original.substring(0, original.length()-1);
    }
    
    
    private boolean isIdenticalViaRotationAndReverse(String string1,
                                                     String string2)
    {
        String rotatedString = string1;
        for ( int i = 0 ; i < string1.length() ; i++ )
        {
            String reversed = reverse(rotatedString);
            if (reversed.intern().equals(string2.intern()))
            {
                return true;
            }
            rotatedString = rotate(rotatedString);
        }
        return false;
    }
    
    
    private String reverse(String string)
    {
        StringBuffer sb = new StringBuffer(string.length());
        for ( int i = 0 ; i < string.length() ; i++)
        {
            sb.append(string.charAt(i));
        }
        return sb.reverse().toString().intern();
    }

    
    public static void main(String[] args)
    {
        String str = "RRG";
        int result = new CircleBugs().cycleLength("RGGGGGGGGGGGGGGGGGGGGGGGGGGGR");
        System.out.println(result);

    }

}

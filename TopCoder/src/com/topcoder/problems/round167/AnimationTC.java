package com.topcoder.problems.round167;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnimationTC
{
    public String[] animate(int speed, String init) 
    {
        int[] pp = new int[init.length()];
        int[] dd = new int[init.length()];
        int nn = 0;
        for ( int i = 0 ; i < init.length() ; i++ )
        {
            if ( init.charAt(i) == 'R' )
            {
                pp[nn] = i;
                dd[nn] = speed;
                nn++;
            }
            else if ( init.charAt(i) == 'L' )
            {
                pp[nn] = i;
                dd[nn] = -speed;
                nn++;
            }
            
        }
        
        StringBuilder sb = new StringBuilder(init.length());
        for ( int j = 0 ; j < init.length() ; j++)
        {
            sb.append('.');
        }
        String end = sb.toString();
        List<String> retVal = new ArrayList<>();
        while ( true )
        {
            char[] charArr = new char[init.length()];
            Arrays.fill(charArr, '.');
            for ( int i = 0 ; i < nn ; i++ )
            {
                if ( pp[i] < init.length()
                     && pp[i] >= 0 )
                {
                    charArr[pp[i]] = 'X';
                }
                pp[i] += dd[i];                
            }
            String res = String.valueOf(charArr);
            retVal.add(res);
            if ( res.intern().equals(end.intern()))
            {
                break;
            }
        }
        return (String[])retVal.toArray(new String[0]);
    }
    public static void main(String[] args)
    {
        String[] arr = new AnimationTC().animate(2 ,"..R....");
        for ( String str : arr )
        {
            System.out.println(str);
        }
    }

}

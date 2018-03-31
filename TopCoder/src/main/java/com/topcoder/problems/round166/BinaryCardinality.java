package com.topcoder.problems.round166;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//http://community.topcoder.com/stat?c=round_overview&er=5&rd=4635
public class BinaryCardinality
{
    class Number
    {
        int n;
        int binaryCardinality;
        Number(int n)
        {
            this.n = n;
        }
    }
    
    
    public int[] arrange(int[] numbers)
    {
        List<Number> list = new ArrayList<Number>();
        for ( int i = 0 ; i < numbers.length ; i++ )
        {
            list.add(new Number(numbers[i]));
        }
        
        list.stream().forEach( n ->
                               n.binaryCardinality=getCardinality(n.n) );
        
        Collections.sort(list, (o1, o2)->
                               {
                                    return o1.binaryCardinality == o2.binaryCardinality? 
                                       (o1.n < o2.n ? -1 : 1) :
                                       (o1.binaryCardinality < o2.binaryCardinality ? -1 : 1);
                               }
                        );   
        int[] retVal = list.stream().
                       mapToInt(c -> c.binaryCardinality).
                       toArray();
        for ( int i = 0 ; i < retVal.length ; i++ )
        {
            retVal[i] = list.get(i).n;
        }
        return retVal;
        
    }
    
    
    private int getCardinality(int decimal)
    {
        int n = decimal;
        int mask = 3;
        int result = 0;
        while(n > 0)
        {
            int l = n & mask;
            n = n >> 2;
            if (l == 0)
                continue;
            if ( l == 3)
            {
                result += 2;
            }
            else
            {
                result += 1;
            }
        }
        return result;
    }
    
    public static void main(String[] args)
    {
        int[] result = new BinaryCardinality().arrange(new int[]{811385,340578,980086,545001,774872,855585,13848,863414,419523,190151,784903,127461});
        for ( int i = 0 ; i < result.length ; i++ )
        {
            System.out.println(result[i]);
        }

    }

}

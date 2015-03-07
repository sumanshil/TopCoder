package com.topcoder.problems.round167;

import java.util.ArrayList;
import java.util.List;

//http://community.topcoder.com/stat?c=problem_statement&pm=1914&rd=4640
public class Animation
{
    public String[] animate(int speed, String init) throws InterruptedException
    {
        int count = countElements(init);
        int[] arr = new int[init.length()];
        for ( int i = 0 ; i < arr.length ; i++ )
        {
            arr[i] = (init.charAt(i) == '.' ? 0 : (init.charAt(i) == 'L') ? 1 : 2); 
        }
        List<String> retVal = new ArrayList<>();
        while( count >= 0 )
        {
            StringBuilder sb = new StringBuilder();
            for ( int i = 0; 
                      i < arr.length ;
                      i++)
            {
                if ( isElementPresentAt(arr, i) )
                {
                    sb.append("X");
                }
                else
                {
                    sb.append(".");
                }
            }
            retVal.add(sb.toString());
            System.out.println(sb.toString());
            if ( count == 0 )
            {
                break;
            }
            int[] newArr = new int[arr.length];
            for ( int i = 0 ; i < init.length() ; i++ )
            {
                if ( isElementPresentAt(arr, i) )
                {
                    if ( ( (arr[i] == 2)
                            && ((i + speed) >= init.length())
                         )  
                         ||
                         ( (arr[i] == 1)
                            && ((i - speed) < 0) 
                         )
                         
                       )  
                    {
                        if ( !isPositionAlreadyFilled(newArr, i) )
                        {
                            newArr[i] = 0;
                        }
                        count--;
                        continue;
                    }

                    if ( arr[i] == 3 )
                    {
                        boolean c = true;
                        if ( (i + speed) >= init.length() )
                        {
                            count--;
                            if ( newArr[i] > 2)
                            {
                                newArr[i] -= 2;
                            }
                        }
                        else
                        {
                            c = false;
                        }
                        
                        if (  (i - speed) < 0 )
                        {
                            count--;
                            if ( newArr[i] > 1 )
                            {
                                newArr[i] -= 1;
                            }                            
                        }
                        else
                        {
                            c = false;
                        }
                        if ( c )
                            continue;
                    }
                    
                    {
                        if ( arr[i] == 1 )
                        {
                            newArr[i-speed] += arr[i] ;
                        }
                        else if ( arr[i] == 2 )
                        {
                            newArr[i+speed] += arr[i];
                        }
                        else if ( arr[i] == 3 )
                        {
                            newArr[i-speed] += 1 ;
                            newArr[i+speed] += 2;
                        }
                        if ( !isPositionAlreadyFilled(newArr, i) )
                        {
                            newArr[i] = 0;
                        }
                    }
                }
            }
            arr = newArr;
        }
        return (String[])retVal.toArray(new String[0]);
    }
    
    
    private boolean isPositionAlreadyFilled(int[] newArr, int i)
    {
        return newArr[i] == 1
               || newArr[i] == 2
               || newArr[i] == 3;
    }


    private int countElements(String init)
    {
        int count = 0;
        for ( int i = 0 ; i < init.length(); i++ )
        {
            if ( init.charAt(i) == 'R'
                 || init.charAt(i) == 'L' )
            {
                count++;
            }
        }
        return count;
    }

    private boolean isElementPresentAt(int[] arr,
                                       int position)
    {
        return arr[position] == 1
                || arr[position] == 2
                || arr[position] == 3;
    }

    public static void main(String[] args) throws InterruptedException
    {
        String[] arr = new Animation().animate(1 ,"LRRL.LR.LRR.R.LRRL.");
        for ( String str : arr )
        {
            System.out.println(str);
        }

    }

}

package com.topcoder.problems.round164;

public class Permutation
{
    private String str;
    private boolean[] flags;
    private int[]  arr;
    public Permutation(String str)
    {
        this.str = str;
        flags = new boolean[this.str.length()];
        arr = new int[this.str.length()];
        for( int i = 0 ; i < arr.length ; i++)
        {
            arr[i] = i;
        }
        permuteRecursive(0);
    }
    
    
    
    private void permuteRecursive(int pos)
    {
        if ( pos == arr.length )
        {
            for( int i = 0 ; i < arr.length ; i++)
            {
                System.out.print(this.str.charAt(arr[i]));
            }
            System.out.println();
            return;
        }
        
        for( int i = 0 ; i < arr.length ; i++ )
        {
            if ( !flags[i] )
            {
                flags[i] = true;
                arr[pos] = i;
                permuteRecursive(pos+1);
                flags[i] = false;
            }
        }
        
    }



    public static void main(String[] args)
    {
        new Permutation("SUMAN");
    }

}

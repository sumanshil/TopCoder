package com.topcoder.problems.round166;

public class Workshop
{
    private int result = 0;
    public  int pictureFrames(int[] pieces)
    {
        recursive(pieces,
                  0,
                  0,
                  new int[3]);
        return result;
    }
    
    
    private void recursive(int[] pieces,
                           int pos,
                           int index,
                           int[] arr)
    {
        if ( pos == 3 )
        {
            if ( (arr[0] + arr[1] > arr[2])
                 && (arr[0] + arr[2] > arr[1])
                 && (arr[1] + arr[2] > arr[0]))
            {
                //System.out.println(arr[0]+" "+arr[1]+" "+arr[2]);
                result = result + 1;
            }
            return;
        }
        
        for ( int i = index ; i < pieces.length ; i++)
        {
            arr[pos] = pieces[i];
            recursive(pieces,
                      pos+1,
                      i+1,
                      arr);
        }
    }
    
    
    public static void main(String[] args)
    {
        int result = new Workshop().
                     pictureFrames(new int[]{10000,9999,9998,9997,9996,1,2,3,4,5});
        System.out.println(result);

    }

}

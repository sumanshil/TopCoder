package com.geeksforgeeks.array;

public class FindSumOfNElements
{

    public boolean find(int[] arr,
                     int sum ,
                     int n)
    {
        boolean result = recursive(arr,
                  sum,
                  n,
                  0,
                  0,
                  0);    
        return result;
    }
    
    private boolean recursive(int[] arr,
                           int targetSum,
                           int n,
                           int currentSum,
                           int currentIndex,
                           int currentCount)
    {
        if (currentIndex >= arr.length )
        {
            if (currentSum != targetSum)
            {
                return false;
            }
            else if (currentCount == n)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if (currentSum == targetSum
                && currentCount == n)
        {
            return true;
        }
        else if (currentSum != targetSum
                && currentCount == n)
        {
            return false;
        }
        
        boolean result = recursive(arr, 
                         targetSum,
                         n,
                         currentSum,
                         currentIndex+1,
                         currentCount);
        if (!result)
        {
          result =  recursive(arr, 
                targetSum,
                n,
                currentSum+arr[currentIndex],
                currentIndex+1,
                currentCount+1);
        }
        return result;
    }

    public static void main(String[] args)
    {
        int[] arr = {1,2,3,4,5,6,7};
        int n = 3;
        int sum = 20;
        
        boolean result = new FindSumOfNElements().find(arr, sum, n);
        System.out.println(result);

    }

}

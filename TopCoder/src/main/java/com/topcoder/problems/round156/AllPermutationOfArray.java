package com.topcoder.problems.round156;

public class AllPermutationOfArray
{

    boolean[] used = null;
    public void permute(int[] arr)
    {
        used = new boolean[arr.length];
        int[] newArr = new int[arr.length];
        search(0,newArr,arr);
        
    }
    private void search(int index,
                        int[] newArr,
                        int[] arr)
    {
        if (index == arr.length)
        {
            for(int i = 0 ; i < arr.length ; i++)
            {
                System.out.print(newArr[i]+ " ");
            }
            System.out.println();
            return;
        }
        
        
        for(int i = 0 ; i < arr.length ; i++)
        {
            if (!used[i])
            {
                used[i] = true;
                newArr[index] = arr[i];
                search(index+1, newArr, arr);
                used[i] = false;
            }
        }
    }
    public static void main(String[] args)
    {
        int[] arr = {1,2,3,4};
        new AllPermutationOfArray().permute(arr);
    }

}

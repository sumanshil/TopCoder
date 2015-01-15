package com.topcoder.problems;

public class Series {
    public int increasingLength(int[] arr){
        int lastIndex = 0;
        int tempcount = 1;
        int bestcount = Integer.MIN_VALUE;
        for(int i = 1 ; i < arr.length ; i++){
            if (arr[i] <= arr[ lastIndex]){
                if (tempcount > bestcount){
                    bestcount = tempcount;                     
                }
                tempcount = 1;                 
            } else if (arr[i] > arr[ lastIndex]){
                tempcount++;
            }
            lastIndex = i;
        }
        if (tempcount > bestcount)
            bestcount = tempcount;
        return bestcount;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        //int[] arr = {-4, 5, -2, 0 , 4 , 5 , 9 ,9};
        //int[] arr = {3, 2, 1, 2, 3, 3};
        //int[] arr = {-1, 10, 8, 2, 4, 1};
        int[] arr = {10, -98, -96, -94, -90, -80, -70, -60, -61, -40, -30, -20, -10, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 9};
        int result = new Series().increasingLength(arr);
        System.out.println(result);

    }

}

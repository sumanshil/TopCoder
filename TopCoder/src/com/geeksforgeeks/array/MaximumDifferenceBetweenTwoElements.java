package com.geeksforgeeks.array;

public class MaximumDifferenceBetweenTwoElements {

    public static int maxDiff = Integer.MIN_VALUE;
    public int recursiveFind(int[] arr, int index){
        if (index == arr.length-1){
            return arr[index];            
        }
            
        int max = recursiveFind(arr, index+1);
        if (max > arr[index]){
            int diff =  max-arr[index];
            if (diff > maxDiff){
                maxDiff = diff;
            }
        } else {
            max = arr[index];            
        }
        return max;
    }
    
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {2, 3, 10, 6, 4, 8, 1};
        new MaximumDifferenceBetweenTwoElements().recursiveFind(arr, 0);
        System.out.println(maxDiff);
    }

}

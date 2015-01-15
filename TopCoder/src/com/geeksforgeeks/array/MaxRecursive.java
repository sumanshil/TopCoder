package com.geeksforgeeks.array;

public class MaxRecursive {

    private static int[] arr = {5,4,3,2,1,8,9};
    
    public static int getMax(int low, int high){
        if (low == high){
            return arr[low];
        }
        
        int mid = (low+high)/2;
        int max1 = getMax(low, mid);
        int max2 = getMax(mid+1, high);
        if (max1 > max2) 
            return max1;
        else
            return max2;
    }
    
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        int result = getMax(0, 6);
        System.out.println(result);

    }

}

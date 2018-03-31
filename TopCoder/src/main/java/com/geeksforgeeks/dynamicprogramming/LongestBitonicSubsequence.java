package com.geeksforgeeks.dynamicprogramming;

public class LongestBitonicSubsequence {

    public int getLength(int[] arr){
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];
        
        for(int i =0;i<arr.length;i++){
            left[i] = 1;
            right[i] = 1;
        }
        for(int i = 1 ; i< arr.length ;i++){
            for(int j = 0 ; j<i;j++){
                if(arr[j] <arr[i]){
                    if(left[j]+1 > left[i]){
                        left[i] = left[j]+1;
                    }
                }
            }
        }
        
        for(int i = arr.length-2 ; i>=0 ;i--){
            for(int j = arr.length-1 ; j>i;j--){
                if(arr[j] <arr[i]){
                    if(right[j]+1 > right[i]){
                        right[i] = right[j]+1;
                    }
                }
            }
        }
        
        int result = Integer.MIN_VALUE;
        for(int i = 0 ; i<arr.length ;i++){
            if((left[i]+right[i]-1) >result){
                result = left[i]+right[i]-1;
            }
        }
        return result;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {1, 11, 2, 10, 4, 5, 2, 1};
        //int[] arr = {12, 11, 40, 5, 3, 1};
        int result = new LongestBitonicSubsequence().getLength(arr);
        System.out.println(result);
    }

}

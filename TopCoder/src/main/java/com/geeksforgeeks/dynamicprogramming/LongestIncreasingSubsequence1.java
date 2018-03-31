package com.geeksforgeeks.dynamicprogramming;

public class LongestIncreasingSubsequence1 {

    public void find(int[] arr){
        int[] lis = new int[arr.length];
        lis[0] = 1;
        for(int i = 0 ; i < lis.length ; i++){
            lis[i] = 1;
        }
        for(int i = 1; i < arr.length ; i++){
            for(int j = 0 ; j < i ; j++){
                if (arr[j] < arr[i]){
                    if (lis[j] >= lis[i]){
                        lis[i] = lis[j]+1;
                    }
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i < lis.length ; i++){
            if (lis[i]> max){
                max = lis[i];
            }
        }
        System.out.println("Maximum length "+ max);    
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        //int[] arr = { 10, 22, 9, 33, 21, 50, 41, 60 };
        int[] arr = {10, 22, 9, 33, 21, 50, 41, 60, 80};
        new LongestIncreasingSubsequence1().find(arr);
    }

}

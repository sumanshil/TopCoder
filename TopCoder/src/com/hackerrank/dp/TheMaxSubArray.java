package com.hackerrank.dp;

/**
 * Created by sshil on 8/15/2016.
 */
//https://www.hackerrank.com/challenges/maxsubarray
public class TheMaxSubArray {

    public void find(int[] arr){
        int maxSumSoFar = Integer.MIN_VALUE;
        int maxSumCurrent = 0;
        int start = 0;
        int end = 0;
        int maxStart = 0;
        int maxEnd = 0;
        while (end < arr.length) {
            maxSumCurrent += arr[end];
            if (maxSumCurrent <= 0){
                start = end;
                maxSumCurrent = 0;
            }
            end++;
            if (maxSumCurrent > maxSumSoFar){
                maxStart = start;
                maxEnd = end;
                maxSumSoFar = maxSumCurrent;
            }
        }
        System.out.println("Maximum start "+maxStart +" Maximum end "+maxEnd);
        System.out.println("Sum = "+maxSumSoFar);

        if (maxSumSoFar == 0){
            int findMaxFromNegativeArr = findMax(arr);
            System.out.println(findMaxFromNegativeArr+" "+findMaxFromNegativeArr);
        } else {
            int maxSumNonContiguous = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] >= 0) {
                    maxSumNonContiguous += Math.abs(arr[i]);
                }
            }
            System.out.println(maxSumSoFar+" "+maxSumNonContiguous);
        }


    }

    private int findMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i < arr.length ; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {2, -1, 2, 3, 4, -5};
        new TheMaxSubArray().find(arr);
//        String s = "1";
//        String[] arr = s.split("\\s+");
//        for ( int i = 0 ; i < arr.length ; i++){
//            System.out.println(arr[i]);
//        }
    }
}

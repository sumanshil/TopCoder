//package com.geeksforgeeks.dynamicprogramming;
//
///**
// * Created by sshil on 7/15/2016.
// */
////http://www.geeksforgeeks.org/dynamic-programming-set-14-maximum-sum-increasing-subsequence/
//public class MaximumSumIncreasingSubSequence1 {
//
//    public void inOrder(int[] arr){
//        int result = recursive(arr,arr.length-1);
//        System.out.println(result);
//    }
//
//    private int dynamic(int[] arr){
//        int[] val = new int[arr.length];
//        val[0] = 0;
//        for (int i = 1 ; i < val.length ; i++ ) {
//            int max = Integer.MIN_VALUE;
//            for ( int j = 0 ; j < i ; j++) {
//                if (arr[j] < arr[i]){
//                    if (val[j]+1 > max){
//                        max = val[j]+1;
//                    }
//                }
//            }
//            val[i] = max;
//        }
//    }
//    public static void main(String[] args) {
//
//    }
//}

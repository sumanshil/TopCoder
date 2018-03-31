package com.geeksforgeeks.array;

import java.util.Arrays;

//http://www.geeksforgeeks.org/find-number-pairs-xy-yx/
//public class FindNumberOfPairsXyYx {
//
//    public int countPair(int[] x, int[] y){
//        int[] noOfY = new int[5];
//        for(int i = 0 ; i < y.length ; i++){
//            if ( y[i]<5){
//                noOfY[y[i]]++;
//            }
//        }
//        
//        Arrays.sort(y);
//        
//        int result = 0;
//        for(int i = 0 ; i < x.length ; i++){
//            result+= count(x[i], noOfY);
//        }
//        
//    }
//    private int count(int x, int[] noOfY) {
//        if (x == 0)
//            return 0;
//        
//        if (x == 1) return noOfY[0];
//        
//        if (x == 2) 
//        return 0;
//    }
//    /**
//     * @param args
//     */
//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        int[] x = {2,1,6};
//        int[] y = {1,5};
//        
//        count(y, x);
//    }
//
//}

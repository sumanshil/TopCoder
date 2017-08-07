package com.geeksforgeeks.dynamicprogramming;
//http://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/
public class MinimumNumberOfJumpsToReachEnd1 {

    public void find(int[] arr) {
        int[] minPath = new int[arr.length];
        minPath[0] = 0;
        for ( int i = 1 ; i < arr.length ; i++) {
            minPath[i] = Integer.MAX_VALUE;
        }

        for (int i = 0 ; i < arr.length ; i++) {
            for (int j = i + 1 ; j <= i + arr[i] && j < arr.length; j++) {
                minPath[j] = Math.min(minPath[j], minPath[i]+1);
            }
        }
        System.out.println(minPath[minPath.length-1]);

    }

    public void findRecursive(int[] arr){
        int result = recursiveUtil (0, arr);
        System.out.println(result);
    }

    private int recursiveUtil(int index, int[] arr) {
        if (index == arr.length-1) {
            return 0;
        }

        int minDistance = Integer.MAX_VALUE;
        for ( int j = index+1 ; j <= index + arr[index] && j < arr.length; j++) {
            int minDist = recursiveUtil(j, arr);
            if (minDist + 1 < minDistance){
                minDistance = minDist + 1;
            }
        }
        return minDistance;
    }


    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
        new MinimumNumberOfJumpsToReachEnd1().findRecursive(arr);
    }
}

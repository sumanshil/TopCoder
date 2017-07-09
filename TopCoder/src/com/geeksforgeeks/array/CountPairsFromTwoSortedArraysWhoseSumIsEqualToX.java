package com.geeksforgeeks.array;

/**
 * Created by sshil on 6/27/17.
 */
//http://www.geeksforgeeks.org/count-pairs-two-sorted-arrays-whose-sum-equal-given-value-x/
public class CountPairsFromTwoSortedArraysWhoseSumIsEqualToX {

    public void findPairs(int[] x, int[] y, int targetSum) {
        int xIndex = 0;
        int yIndex = y.length-1;
        while (xIndex < x.length && yIndex >= 0){
            if (x[xIndex] + y[yIndex] == targetSum) {
                System.out.println("Found pair "+x[xIndex]+ " and "+y[yIndex]);
                xIndex++;
                yIndex--;
            } else if (x[xIndex] + y[yIndex] < targetSum){
                xIndex++;
            } else {
                yIndex--;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 7, 11};
        int[] arr2 = {2, 3, 4, 5, 6, 8, 12} ;
        int sum = 9;
        new CountPairsFromTwoSortedArraysWhoseSumIsEqualToX().findPairs(arr1, arr2, sum);
    }
}

package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 6/7/2016.
 */
//http://www.geeksforgeeks.org/find-maximum-length-snake-sequence/
public class MaximumLengthSnakeSequence {

    private int[][] matrix = null;
    public void find(int[][] arr){
        matrix = arr;
        int maxXAxis = Integer.MIN_VALUE;
        int maxYAxis = Integer.MIN_VALUE;
        int[][] maxDistanceArr = new int[arr.length][arr[0].length];
        int maxDistanceSoFar = Integer.MIN_VALUE;
        maxDistanceArr[0][0] = 0;
        for (int i = 0 ; i < arr.length ; i++){
            for ( int j = 0 ; j < arr[0].length ; j++) {
                if ( i == 0 && j == 0){
                    continue;
                }
                int maxDistance = Integer.MIN_VALUE;
                // check left
                if (i-1 >=0
                    && Math.abs(arr[i-1][j]-arr[i][j]) == 1
                    && maxDistanceArr[i-1][j] > maxDistance){
                    maxDistance = maxDistanceArr[i-1][j];
                }
                // check up
                if (j-1 >= 0
                    && Math.abs(arr[i][j-1]-arr[i][j]) == 1
                    && maxDistanceArr[i][j-1] > maxDistance){
                    maxDistance = maxDistanceArr[i][j-1];
                }
                if (maxDistance != Integer.MIN_VALUE) {
                    maxDistanceArr[i][j] = maxDistance + 1;
                    if (maxDistance + 1 > maxDistanceSoFar) {
                        maxDistanceSoFar = maxDistance+1;
                        maxXAxis = i;
                        maxYAxis = j;
                    }
                }
            }
        }

        System.out.println("Max X axis "+maxXAxis+" Max Y axis "+maxYAxis);
        // now trace back
        while(isValid(maxXAxis, maxXAxis)){
            System.out.println("Position "+ arr[maxXAxis][maxYAxis]);
            int nextX = 0;
            int nextY = 0;
            boolean found = false;
            if (isValid(maxXAxis, maxYAxis-1)
                && Math.abs(maxDistanceArr[maxXAxis][maxYAxis]- maxDistanceArr[maxXAxis][maxYAxis-1]) == 1){
                nextX = maxXAxis;
                nextY = maxYAxis-1;
                found = true;
            }

            if (isValid(maxXAxis-1, maxYAxis)
                && Math.abs(maxDistanceArr[maxXAxis][maxYAxis]- maxDistanceArr[maxXAxis-1][maxYAxis]) == 1){
                nextX = maxXAxis-1;
                nextY = maxYAxis;
                found = true;
            }
            if (found) {
                maxXAxis = nextX;
                maxYAxis = nextY;
            } else {
                break;
            }
        }
    }

    private boolean isValid(int xAxis, int yAxis) {
        return xAxis >= 0
               && xAxis < matrix.length
               && yAxis >= 0
               && yAxis <matrix[0].length;
    }

    public static void main(String[] args) {
        int[][] arr = {
            {9, 6, 5, 2},
            {8, 7, 6, 5},
            {7, 3, 1, 6},
            {1, 1, 1, 7}
        };
        new MaximumLengthSnakeSequence().find(arr);
    }
}

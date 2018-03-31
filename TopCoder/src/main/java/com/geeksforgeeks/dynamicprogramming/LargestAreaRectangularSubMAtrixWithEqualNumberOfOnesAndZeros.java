package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/largest-area-rectangular-sub-matrix-equal-number-1s-0s/
public class LargestAreaRectangularSubMAtrixWithEqualNumberOfOnesAndZeros {

    int matrix[][] = {
            {0, 0, 1, 1},
            {0, 1, 1, 0},
            {1, 1, 1, 0},
            {1, 0, 0, 1}
    };


    public void find (){
        int ones[][] = new int[matrix.length][matrix[0].length];
        int zeros[][] = new int[matrix.length][matrix[0].length];

        if (matrix[0][0] == 0){
            ones[0][0] = 0;
            zeros[0][0] = 1;
        } else {
            ones[0][0] = 1;
            zeros[0][0] = 0;
        }

        for ( int i = 0 ; i < matrix.length ; i++) {
            for ( int j = 0 ; j < matrix[0].length ; j++) {
                if ( i == 0 && j == 0 ) {
                    continue;
                }

                if ( i == 0 ){
                    if (matrix[i][j] == 0 ){
                        ones[i][j] = ones[i][j-1];
                        zeros[i][j] = zeros[i][j-1] + 1;
                    } else {
                        ones[i][j] = ones[i][j-1] + 1;
                        zeros[i][j] = zeros[i][j-1];
                    }
                } else if (j == 0){
                    if (matrix[i][j] == 0 ){
                        ones[i][j] = ones[i-1][j];
                        zeros[i][j] = zeros[i-1][j] + 1;
                    } else {
                        ones[i][j] = ones[i-1][j] + 1;
                        zeros[i][j] = zeros[i-1][j];
                    }
                } else {

                    if (matrix[i][j] == 0) {
                        zeros[i][j] = (zeros[i-1][j] + zeros[i][j-1] - zeros[i-1][j-1]) + 1;
                        ones[i][j] = ones[i-1][j] + ones[i][j-1] - ones[i-1][j-1];
                    } else {
                        zeros[i][j] = (zeros[i-1][j] + zeros[i][j-1] - zeros[i-1][j-1]);
                        ones[i][j] = ones[i-1][j] + ones[i][j-1] - ones[i-1][j-1]  + 1;
                    }
                }
            }
        }

        int maxSize = Integer.MIN_VALUE;

        for (int i = 0 ; i < matrix.length ; i++) {
            for (int j = 0 ; j < matrix[0].length ; j++) {
                if (ones[i][j] == zeros[i][j]) {
                    maxSize = Math.max(maxSize, ones[i][j] + zeros[i][j]);
                }
            }
        }
        System.out.println(maxSize);
    }

    public static void main(String[] args) {
        new LargestAreaRectangularSubMAtrixWithEqualNumberOfOnesAndZeros().find();
    }
}

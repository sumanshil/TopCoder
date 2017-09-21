package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/largest-rectangular-sub-matrix-whose-sum-0/
public class LargestRectangularSubMatrixWhoseSumIsZero {

    int matrix[][] = {
            {9, 7, 16, 5},
            {1,-6, -7, 3},
            {1, 8,  7, 9},
            {7,-2,  0, 19}
    };


    public void find (){
        int sumRectangle[][] = new int[matrix.length][matrix[0].length];

        sumRectangle[0][0] = matrix[0][0];
        for ( int i = 1 ; i < matrix[0].length; i++ ) {
            sumRectangle[0][i] = matrix[0][i];
        }

        for ( int i = 1 ; i < matrix.length ; i++) {
            sumRectangle[i][0] = sumRectangle[i-1][0] + matrix[i][0];
        }

        for ( int i = 1; i < matrix.length ; i++) {
            for ( int j = 1 ; j < matrix[0].length ; j++)  {

                int sum1 = matrix[i-1][j];
                int sum2 = matrix[i][j-1];
                int sum3 = matrix[i-1][j-1];
                int result = sum1 + sum2 - sum3 + matrix[i][j];
                sumRectangle[i][j] = result;
            }
        }

        int maxAreaOfRectangle = Integer.MIN_VALUE;

        for ( int row = 1 ; row < matrix.length ; row++) {
            for ( int col = 1 ; col < matrix[0].length ; col++) {
                for ( int r = 0 ; r < row ; r++) {
                    for ( int c = 0 ; c < col ; c++) {

                        int xLength = (row - r);
                        int yLength = (col - c);
                        int leftLowerX = row;
                        int leftLowerY = c;

                        int leftSum = getSum(leftLowerX, leftLowerY-1, sumRectangle);

                        int rightDownX = r;
                        int rightDownY = col;

                        int rightSum = getSum(rightDownX-1, rightDownY, sumRectangle);

                        int diagonalSum = getSum(r-1, c-1, sumRectangle);

                        int resultSum = sumRectangle[row][col] - (leftSum + rightSum) + diagonalSum;

                        if (resultSum == 0) {
                            if (xLength * yLength > maxAreaOfRectangle){
                                maxAreaOfRectangle = (xLength * yLength);
                            }
                        }
                    }
                }
            }
        }



    }

    private int getSum(int row, int col, int sumMatrix[][]) {
        if (row >= 0 && col >= 0) {
            return sumMatrix[row][col] ;
        }
        return 0;
    }

    public static void main(String[] args) {

    }
}

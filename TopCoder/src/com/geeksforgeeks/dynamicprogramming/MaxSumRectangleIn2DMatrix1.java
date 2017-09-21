package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/dynamic-programming-set-27-max-sum-rectangle-in-a-2d-matrix/
public class MaxSumRectangleIn2DMatrix1 {

    public void find(int matrix[][]) {
        int rowLength = matrix.length;
        int colLength = matrix[0].length;
        int dp[][] = new int[rowLength][colLength];
        for (int i = 0 ; i < rowLength ; i++) {
            for ( int j = 0 ; j < colLength ; j++) {
                if ( i == 0 && j == 0) {
                    dp[i][j] = matrix[0][0];
                } else if ( i == 0) {
                    dp[i][j] = dp[i][j-1] + matrix[i][j];
                } else if ( j == 0) {
                    dp[i][j] = dp[i-1][j] + matrix[i][j];
                } else {
                    dp[i][j] = (dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1]) + matrix[i][j];
                }
            }
        }

        int maxSum = Integer.MIN_VALUE;

        for ( int i = 1, j = 1 ; i < rowLength && j < colLength ; i++,j++){
            for (int k = 0 , l = 0 ; k < i && l < j;  k++, l++) {
                int subMatrixRowLength = (i-k);
                int subMatrixColLength = (j-l);
                int rightUpperX = k;
                int rightUpperY = (k + subMatrixColLength);

                int leftLowerX = k + subMatrixRowLength;
                int leftLowerY = l ;

                int totalSum = dp[i][j];
                if (rightUpperX - 1 >= 0 ){
                    totalSum = totalSum - dp[rightUpperY-1][rightUpperY];
                }

                if (leftLowerY -1 >= 0){
                    totalSum = totalSum - dp[leftLowerX][leftLowerY-1];
                }

                if (totalSum > maxSum) {
                    maxSum = totalSum;
                }
            }
        }
        System.out.println(maxSum);
    }

    public static void main(String[] args) {
        int matrix[][] =
                {
                    { 1, 2,-1,-4,-20},
                    {-8,-3, 4, 2, 1},
                    { 3, 8,10, 1, 3},
                    {-4,-1, 1, 7,-6}
                };
        new MaxSumRectangleIn2DMatrix1().find(matrix);
    }
}

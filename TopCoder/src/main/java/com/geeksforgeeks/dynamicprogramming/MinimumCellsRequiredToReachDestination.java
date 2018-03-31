package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/minimum-cells-required-reach-destination-jumps-equal-cell-values/
public class MinimumCellsRequiredToReachDestination {

    int matrix[][] = { {2, 4, 2},
                       {5, 3, 8},
                       {1, 1, 1} };

    public void find (){
        boolean isPointInPath[][] = new boolean[matrix.length][matrix[0].length];
        int dp[][] = new int[matrix.length][matrix[0].length];

        for (int i = 0 ; i < matrix.length ; i++) {
            for (int j = 0 ; j < matrix[0].length ; j++) {
                if (i ==0 && j == 0){
                    continue;
                }
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        isPointInPath[0][0] = true;
        dp[0][0] = 1;

        for (int i = 0 ; i < matrix.length ; i++) {
            for (int j = 0 ; j < matrix[0].length ; j++) {
                if (isPointInPath[i][j]){
                    int newX = i ;
                    int newY = j + matrix[i][j];
                    if (isValid(newX, newY, matrix.length, matrix[0].length)){
                        isPointInPath[newX][newY] = true;
                        dp[newX][newY] = Math.min(dp[newX][newY], dp[i][j] + 1);
                    }

                    newX = i + matrix[i][j];
                    newY = j;
                    if (isValid(newX, newY, matrix.length, matrix[0].length)){
                        isPointInPath[newX][newY] = true;
                        dp[newX][newY] = Math.min(dp[newX][newY], dp[i][j] + 1);
                    }
                }
            }
        }
        System.out.println(dp[matrix.length-1][matrix[0].length-1] == Integer.MAX_VALUE ?
                           -1 : dp[matrix.length-1][matrix[0].length-1]);
    }

    private boolean isValid(int newX, int newY, int rowLength, int colLength) {
        return newX >= 0 && newX < rowLength && newY >= 0 && newY < colLength;
    }

    public static void main(String[] args) {
        new MinimumCellsRequiredToReachDestination().find();
    }
}

package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 9/16/16.
 */
//http://www.geeksforgeeks.org/find-length-of-the-longest-consecutive-path-in-a-character-matrix/
public class FindLengthOfLongestConsecutivePathFromAGivenCharacter {

    private int[] diffX = {0,  0, 1, -1, -1,  1, 1, -1};
    private int[] diffY = {1, -1, 0,  0, -1, -1, 1,  1};
    public void find(char[][] matrix, char s) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        for ( int i = 0 ; i < row ; i++) {
            for ( int j = 0 ; j < col ; j++) {
                dp[i][j] = 1;
            }
        }
        for ( int i = 0 ; i < row ; i++) {
            for ( int j = 0 ; j < col ; j++) {
                for ( int k = 0 ; k < diffX.length ; k++) {
                    int newX = i + diffX[k];
                    int newY = j + diffY[k];
                    if (isValid(newX, newY, row, col)){
                        if (matrix[newX][newY] - matrix[i][j] == 1) {
                            dp[i][j] = Math.max(dp[newX][newY]+1, dp[i][j]);
                        }
                    }
                }
            }
        }


        int result = Integer.MIN_VALUE;
        for ( int i = 0 ; i < row ; i++) {
            for ( int j = 0 ; j < col ; j++) {
                if (matrix[i][j] == s) {
                    for (int k = 0; k < diffX.length; k++) {
                        int newX = i + diffX[k];
                        int newY = j + diffY[k];
                        if (isValid(newX, newY, row, col)) {
                            if (matrix[newX][newY] - matrix[i][j] == 1) {
                                dp[i][j] = Math.max(dp[newX][newY] + 1, dp[i][j]);
                            }
                        }
                    }

                    if (dp[i][j] > result){
                        result = dp[i][j];
                    }
                }
            }
        }
        System.out.println(result);

    }

    private boolean isValid(int newX, int newY, int row, int col) {
        return newX >= 0 && newX < row && newY >= 0 && newY < col;
    }


    public static void main(String[] args) {
//        char[][] matrix = {
//            {'a', 'c', 'd'},
//            {'h', 'b', 'e'},
//            {'i', 'g', 'f'}
//        };
        char[][] matrix = {
                {'a', 'c', 'd'},
                {'h', 'b', 'a'},
                {'i', 'g', 'f'}
        };

        new FindLengthOfLongestConsecutivePathFromAGivenCharacter().find(matrix, 'f');
    }
}

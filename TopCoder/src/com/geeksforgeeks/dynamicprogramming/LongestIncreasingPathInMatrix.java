package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/longest-increasing-path-matrix/
public class LongestIncreasingPathInMatrix {
//    int matrix[][] = {
//            { 1, 2, 3, 4},
//            { 2, 2, 3, 4},
//            { 3, 2, 3, 4 },
//            { 4, 5, 6, 7 }
//    };

    int matrix[][] = {
            { 1, 2 },
            { 3, 4 }
    };

    public void find() {
        boolean validPath[][] = new boolean[matrix.length][matrix[0].length];
        int longestPath[][] = new int[matrix.length][matrix[0].length];

        validPath[0][0] = true;
        longestPath[0][0] = 0;

        for ( int i = 0 ; i < matrix.length ; i++) {
            for ( int j = 0 ; j < matrix[0].length ; j++) {
                if ( i == 0 && j == 0) {
                    continue;
                }
                int sourceX = i-1;
                int sourceY = j;
                if (isValid(sourceX, sourceY, matrix.length, matrix[0].length)) {
                   if (validPath[sourceX][sourceY] && matrix[sourceX][sourceY] < matrix[i][j]) {
                       validPath[i][j] = true;
                       longestPath[i][j] = Math.max(longestPath[i][j], longestPath[sourceX][sourceY] + 1);
                   }
                }
                sourceX = i;
                sourceY = j - 1;
                if (isValid(sourceX, sourceY, matrix.length, matrix[0].length)) {
                    if (validPath[sourceX][sourceY] && matrix[sourceX][sourceY] < matrix[i][j] ) {
                        validPath[i][j] = true;
                        longestPath[i][j] = Math.max(longestPath[i][j], longestPath[sourceX][sourceY] + 1);
                    }
                }
            }
        }

        System.out.println(longestPath[matrix.length-1][matrix[0].length-1] + 1);

    }

    private boolean isValid(int newX, int newY, int rowLength, int colLength) {
        return newX >= 0 && newX < rowLength && newY >= 0 && newY < colLength;
    }


    public static void main(String[] args) {
        new LongestIncreasingPathInMatrix().find();
    }
}

package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 8/10/2016.
 */
//http://www.geeksforgeeks.org/number-of-paths-with-exactly-k-coins/
public class NumberOfPathsWithExactlyKCoins {
    public void find(int[][] matrix, int sum){
        int result = findRecursive(matrix, matrix.length-1, matrix[0].length-1, sum);
        //int result = findRecursive1(matrix, 0, 0, 0, sum);
        System.out.println(result);
    }

    private int findRecursive(int[][] matrix, int i, int j, int sum) {
        if (!isValid(matrix, i, j)){
            return 0;
        }
        int expectedSum = sum - matrix[i][j];
        if (i == 0 && j == 0){
            if (matrix[i][j] == expectedSum){
                return 1;
            }
            return 0;
        }
        return findRecursive(matrix, i, j-1, expectedSum)
            + findRecursive(matrix, i-1, j, expectedSum);
    }

//    private int findRecursive1(int[][] matrix, int i, int j, int currentSum, int targetSum){
//        if (!isValid(matrix, i, j)){
//            return 0;
//        }
//        int expectedSum = currentSum + matrix[i][j];
//        if (isLastNode(matrix, i, j)){
//            if (expectedSum == targetSum){
//                return 1;
//            }
//            return 0;
//        }
//        return findRecursive1(matrix, i, j+1, expectedSum, targetSum)
//            + findRecursive1(matrix, i-1, j, expectedSum, targetSum);
//
//    }

    private boolean isLastNode(int[][] matrix, int i, int j) {
        return i == matrix.length-1 && j == matrix[0].length-1;
    }


    private boolean isValid(int[][] matrix, int x, int y) {
        return x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 6, 5},
            {3, 2, 1}
        };

        new NumberOfPathsWithExactlyKCoins().find(matrix, 12);
    }
}


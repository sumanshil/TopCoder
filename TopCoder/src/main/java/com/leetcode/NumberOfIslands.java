package com.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//https://leetcode.com/problems/number-of-islands-ii/
public class NumberOfIslands {

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[][] matrix = new int[m][n];
        List<Integer> result = new LinkedList<>();
        int islandCount = 0;
        for (int i = 0 ; i < m; i++) {
            for (int j = 0 ; j < n ; j++) {
                matrix[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int[] pos : positions) {
            int row = pos[0];
            int col = pos[1];
            if (matrix[row][col] != Integer.MAX_VALUE) {
                continue;
            }
            System.out.println("Positions" + row + " " + col);
            int minNeghborValue = Integer.MAX_VALUE;
            Set<Integer> isLandSets = new HashSet<>();
            for (int i = -1; i <= 1 ; i++ ) {
                for (int j = -1 ; j <= 1; j++) {
                    if (( i ==0 && j == 0) || (i != 0 && j != 0)) {
                        continue;
                    }
                    int neighborRow = row + i;
                    int neighborCol = col + j;
                    if (neighborRow >= 0 && neighborRow < m && neighborCol >= 0 && neighborCol < n) {
                        if (matrix[neighborRow][neighborCol] != Integer.MAX_VALUE) {
                            minNeghborValue = Math.min(minNeghborValue, matrix[neighborRow][neighborCol]);
                            isLandSets.add(matrix[neighborRow][neighborCol]);
                        }
                    }
                }
            }
            if (minNeghborValue == Integer.MAX_VALUE) {
                islandCount++;
                matrix[row][col] = islandCount;
                System.out.println("Is land count " + islandCount);
                result.add(islandCount);
            } else {
                islandCount = islandCount - (isLandSets.size()) + 1;
                System.out.println("Is land count " + islandCount);
                result.add(islandCount);
                matrix[row][col] = minNeghborValue;
                for (int i = -1; i <= 1 ; i++ ) {
                    for (int j = -1 ; j <= 1; j++) {
                        if (( i ==0 && j == 0) || (i != 0 && j != 0)) {
                            continue;
                        }
                        int neighborRow = row + i;
                        int neighborCol = col + j;
                        if (neighborRow >= 0 && neighborRow < m && neighborCol >= 0 && neighborCol < n) {
                            if (matrix[neighborRow][neighborCol] != Integer.MAX_VALUE) {
                                floodFill(neighborRow, neighborCol, minNeghborValue, matrix, m, n);
                            }
                        }
                    }
                }
            }
            System.out.println("=========");
            printMatrix(matrix);
            System.out.println("=========");
        }
        return result;
    }

    private void printMatrix(int[][] matrix) {
        for (int i = 0 ; i < matrix.length ; i++) {
            for (int j = 0 ; j < matrix[0].length ; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void floodFill(int neighborRow, int neighborCol, int minNeghborValue, int[][] matrix, int m , int n) {
        if (neighborRow < 0 || neighborRow >= m) {
            return;
        }

        if (neighborCol < 0 || neighborCol >= n) {
            return;
        }
        if (matrix[neighborRow][neighborCol] == Integer.MAX_VALUE || matrix[neighborRow][neighborCol] == minNeghborValue) {
            return;
        }

        matrix[neighborRow][neighborCol] = minNeghborValue;

        floodFill(neighborRow, neighborCol + 1, minNeghborValue, matrix, m, n);
        floodFill(neighborRow, neighborCol - 1, minNeghborValue, matrix, m, n);
        floodFill(neighborRow + 1, neighborCol, minNeghborValue, matrix, m, n);
        floodFill(neighborRow - 1, neighborCol, minNeghborValue, matrix, m, n);
    }

    public static void main(String[] args) {
        /**
        int m = 2;
        int n = 2;
        int[][] matrix = {
                {0, 0},
                {1, 1},
                {0, 1}
        };
         **/
        /**
        int m = 3;
        int n = 3;
        int[][] matrix = {
                {0, 0},
                {0, 1},
                {1, 2},
                {2, 1}
        };
        **/
        /**
        int m = 3;
        int n = 3;
        int[][] matrix = {
                {0, 1},
                {1, 2},
                {2, 1},
                {1, 0},
                {0, 2},
                {0, 0},
                {1, 1}
        };
         **/
        /**
        int m = 3;
        int n = 3;
        int[][] matrix = {
                {0, 0},
                {0, 1},
                {1, 2},
                {2, 1}
        };
        **/
        int m = 3;
        int n = 3;
        int[][] matrix = {
                {0, 0},
                {2, 0},
                {0, 1},
                {2, 1},
                {0, 2},
                {2, 2},
                {0, 1},
                {1, 2}
        };

        List<Integer> result = new NumberOfIslands().numIslands2(m, n , matrix);
        System.out.println(result);
    }
}

package com.geeksforgeeks.graph;

//http://www.geeksforgeeks.org/dynamic-programming-set-16-floyd-warshall-algorithm/
// This algorithm takes each vertex and consider them as intermediate vertex for all the pairs.
// If this is really an intermediate vertex, it updates the pairs distance
public class FloydWarshallAlgorithm1 {

    private int matrix[][] = {
            {0, 5, Integer.MAX_VALUE, 10},
            {Integer.MAX_VALUE, 0, 3, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 1},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
    };

    public void find() {
        int n = matrix.length;
        int solution[][] = new int[matrix.length][matrix[0].length];
        for ( int i = 0 ; i < solution.length ; i++) {
            for ( int j = 0 ; j < solution[0].length ; j++) {
                solution[i][j] = Integer.MAX_VALUE;
            }
        }

        for ( int i = 0 ; i < n ; i++) {
            for ( int j = 0 ; j < n ; j++) {
                for ( int k = 0 ; k < n ; k++) {
                    if (solution[j][i] != Integer.MAX_VALUE && solution[i][k] != Integer.MAX_VALUE) {
                        if (solution[j][i] + solution[i][k] < solution[j][k]) {
                            solution[j][k] = solution[j][i] + solution[i][k];
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

    }



}

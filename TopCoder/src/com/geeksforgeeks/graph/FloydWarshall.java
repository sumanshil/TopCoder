package com.geeksforgeeks.graph;

/**
 * Created by sshil on 4/1/17.
 */
//http://www.geeksforgeeks.org/dynamic-programming-set-16-floyd-warshall-algorithm/
//https://www.youtube.com/watch?v=NzgFUwOaoIw
public class FloydWarshall {

    private static final Integer MAX_VALUE = 100000;
    void find (int[][] graph){
        int[][] minDistance = new int[graph.length][graph[0].length];
        for ( int i = 0 ; i < minDistance.length ; i++) {
            for ( int j = 0 ; j < minDistance[0].length ; j++) {
                minDistance[i][j] = graph[i][j];
            }
        }

        for ( int k = 0 ; k < minDistance.length ; k++) {
            for ( int i = 0 ; i < minDistance.length ; i++) {
                for ( int j = 0 ; j < minDistance.length ; j++) {
                    if ( minDistance[i][k] + minDistance[k][j] < minDistance[i][j]){
                        minDistance[i][j] =  minDistance[i][k] + minDistance[k][j];
                    }
                }
            }
        }

        for (int i = 0 ; i < minDistance.length ; i++) {
            for ( int j = 0 ; j < minDistance[0].length ; j++) {
                if (i != j) {
                    System.out.println("Minimum distance between [" +i+"] and ["+j+"] is "+minDistance[i][j]);
                }
            }
        }
    }



    private boolean isConnected (int[][] graph, int source, int destination) {
        return graph[source][destination] != MAX_VALUE;
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0,   5,  MAX_VALUE, 10},
                {MAX_VALUE,  0,  3,  MAX_VALUE},
                {MAX_VALUE, MAX_VALUE, 0,   1},
                {MAX_VALUE, MAX_VALUE, MAX_VALUE, 0}
        };

        new FloydWarshall().find(graph);
    }
}

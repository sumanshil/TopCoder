package com.geeksforgeeks.graph;

import java.util.LinkedList;
import java.util.Queue;

//http://www.geeksforgeeks.org/clone-directed-acyclic-graph/
public class CloneADirectedGraph {
    private static int[][] matrix = {
            {0,1,1,0,0},
            {0,0,1,1,1},
            {0,0,0,1,0},
            {0,0,0,0,1},
            {0,0,0,0,0}
    };

    public static void cloneGraph(int source){

        int[][] clonedMatrix = new int[matrix.length][matrix[0].length];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        while(!queue.isEmpty()){
            int current = queue.poll();
            int[] arr = matrix[current];
            for ( int i = 0 ; i < arr.length ; i++) {
                if (arr[i] != 0 ){
                    clonedMatrix[current][i] = 1;
                    queue.add(i);
                }
            }
        }

        for ( int i = 0 ; i < clonedMatrix.length ; i++) {
            for (int j = 0; j < clonedMatrix[0].length ; j++) {
                System.out.print(clonedMatrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        cloneGraph(0);
    }


}

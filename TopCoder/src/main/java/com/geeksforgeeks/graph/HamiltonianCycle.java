package com.geeksforgeeks.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sshil on 3/29/17.
 */
//http://www.geeksforgeeks.org/backtracking-set-7-hamiltonian-cycle/
public class HamiltonianCycle {

    private boolean[] visited = null;
    public  void find (int[][] matrix){
        for ( int i = 0 ; i < matrix.length ; i++) {
            visited = new boolean[matrix.length];
            cycleUtil(matrix, i, i, new ArrayList<>());
        }
    }

    private void cycleUtil(int[][] matrix,
                              int source,
                              int target,
                              List<Integer> list) {
        if (source == target) {
            if (visited[target] && list.size() == matrix.length){
                print(list);
                return ;
            }
        }
        if (visited[source]){
            return ;
        }

        visited[source] = true;
        list.add(source);
        int[] neighbors = matrix[source];
        for ( int i = 0 ; i < neighbors.length ; i++) {
            if (neighbors[i] == 1) {
                cycleUtil(matrix, i, target, list);
            }
        }
        visited[source] = false;
        list.remove(list.size()-1);
    }

    private void print(List<Integer> list) {
        String str = list.stream()
                .map(e -> e.toString())
                .collect(Collectors.joining("=>"));
        System.out.println(str);
    }


    public static void main(String[] args) {
        int[][] matrix = {{0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 0},
                {0, 1, 1, 0, 0},
        };
        new HamiltonianCycle().find(matrix);
    }
}

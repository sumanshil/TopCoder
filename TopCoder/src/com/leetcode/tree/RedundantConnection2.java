package com.leetcode.tree;


import java.util.Arrays;

//https://leetcode.com/problems/redundant-connection-ii/description/
public class RedundantConnection2 {

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int can1[] = {-1, -1};
        int can2[] = {-1, -1};

        int parent[] = new int[edges.length+1];

        Arrays.fill(parent, -1);
        for (int i = 0 ; i < edges.length ; i++) {
            int p = edges[i][0];
            int c = edges[i][1];
            if (parent[c] == -1) {
                parent[c] = p;
            } else {
                can1 = new int[] {parent[edges[i][1]], edges[i][1]};
                can2 = new int[] {edges[i][0], edges[i][1]};
                edges[i][1] = 0;
            }
        }

        for ( int i = 0 ; i < parent.length ; i++) {
            parent[i] = i;
        }

        for ( int i = 0 ; i < edges.length ; i++) {
            if (edges[i][1] == 0) {
                continue;
            }
            int father = edges[i][0];
            int child = edges[i][1];

            if (root(parent, father) == child) {
                if (can1[0] == -1) {
                    return edges[i];
                }
                return can1;
            }
            parent[child] = father;
        }
        return can2;
    }

    private int root(int[] parent, int i) {
        while (parent[i] != i) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }


    public static void main(String[] args) {
        int[][] input = {
                {1, 2},
                {1, 3},
                {2, 3}
        };
        int[] result = new RedundantConnection2().findRedundantDirectedConnection(input);
        for (int i = 0 ; i < result.length ; i++) {
            System.out.println(result[i]);
        }
    }

}

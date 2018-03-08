package com.leetcode.tree;

//https://leetcode.com/problems/redundant-connection/description/
public class RedundantConnection {

    public int[] findRedundantConnection(int[][] edges) {
        if (edges.length == 0) {
            return null;
        }

        int count[] = new int[edges.length-1];

        for ( int i = 0 ; i < edges.length ; i++) {
            int[] arr = edges[i];
            if ( count[arr[1]] == 1 ) {
                return arr;
            }
            count[arr[1]]++;
        }
        return null;
    }


    public static void main(String[] args) {

    }
}

package com.dp;

import java.util.ArrayList;

public class Clicountingd2 {
    public int n;
    public int[][] graph;
    public int k=0;
    public int[][] special;
    public int[] atleast = new int[1048576];

    public void dfs(ArrayList<Integer> prev) {
        boolean finishhere=true;
        int begin=0;
        if (prev.size()!=0) begin=prev.get(prev.size()-1)+1;
        for (int i=begin; i<n; i++) {
            int add=2;
            for (int j:prev) {
                if (add>graph[i][j]) {
                    add=graph[i][j];
                }
            }
            if (add==2) finishhere=false;
            if (add!=0) {
                // recursion
                ArrayList<Integer> temp = new ArrayList<>();
                for (int j:prev) temp.add(j);
                temp.add(i);
                dfs(temp);
            }
        }
        if (finishhere) {
            // count the special edges
            int specialcount=0;
            for (int i=0; i<prev.size(); i++) {
                for (int j=i+1; j<prev.size(); j++) {
                    if (graph[prev.get(i)][prev.get(j)]==1) {
                        specialcount|=(1<<special[prev.get(i)][prev.get(j)]);
                    }
                }
            }
            if (atleast[specialcount]<prev.size()) {
                atleast[specialcount]=prev.size();
            }
        }
    }

    public int count (String[] g) {
        n = g.length;
        graph = new int[n][n];
        special = new int[n][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (g[i].charAt(j)=='0') graph[i][j]=0;
                else if (g[i].charAt(j)=='?') {
                    graph[i][j]=1;
                    if (i<j) {
                        special[i][j]=k;
                        k++;
                    }
                }
                else graph[i][j]=2;
            }
        }
        dfs(new ArrayList<Integer>());
        // fill in atleast
        int out=0;
        for (int i=0; i<(1<<k); i++) {
            for (int j=0; j<k; j++) {
                if (atleast[i|(1<<j)]<atleast[i]) {
                    atleast[i|(1<<j)]=atleast[i];
                }
            }
            out+=atleast[i];
        }
        return out;
    }

    public static void main(String[] args) {
        String[] str = {"0?1", "?01", "110"};
        int result = new Clicountingd2().count(str);
        System.out.println(result);
    }
}
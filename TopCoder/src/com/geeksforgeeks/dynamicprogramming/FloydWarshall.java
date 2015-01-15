package com.geeksforgeeks.dynamicprogramming;
//http://www.geeksforgeeks.org/dynamic-programming-set-16-floyd-warshall-algorithm/
public class FloydWarshall {

    int[][] graph = {
            {0,5,Integer.MAX_VALUE,10},
            {Integer.MAX_VALUE,0,3,Integer.MAX_VALUE},
            {Integer.MAX_VALUE,Integer.MAX_VALUE,0,1},
            {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,0}
    };
    
    public void floydWarshall(){
        int[][] v = new int[graph.length][graph[0].length];
        int i = graph.length;
        int j = graph[0].length;
        for(int k = 0 ; k<i;k++){
            for(int l = 0 ; l<j ; l++){
                v[k][l] = graph[k][l];
            }
        }
        
        for(int m=0 ; m<i ; m++){
            for(int n = 0 ; n<i ; n++){
                for(int p=0; p<i; p++){
                    if(v[n][m] != Integer.MAX_VALUE && v[m][p]!=Integer.MAX_VALUE){
                        v[n][p] = Math.min(v[n][p],v[n][m]+v[m][p]);
                    }
                }
            }
        }
        
        for(int m = 0 ; m<i ; m++){
            for(int n = 0 ; n<i ; n++){
                System.out.print(v[m][n]+" ");
            }
            System.out.println();
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        new FloydWarshall().floydWarshall();
    }

}

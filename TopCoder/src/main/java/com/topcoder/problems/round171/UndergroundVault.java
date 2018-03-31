package com.topcoder.problems.round171;

/**
 * Created by SumanChandra on 11/6/2016.
 */
public class UndergroundVault {

    private boolean[][] G = null;
    private int[][] adj = null;
    private int[] count = null;
    private int dfs = 0;
    boolean[] reach = null;
    boolean[] used = null;
    private void visit( int i){
        if (reach[i] || used[i]){
            return;
        }
        dfs++;
        reach[i] = true;
        for ( int j = 0 ; j < count[i]; j++){
            visit(adj[i][j]);
        }
    }


    int[] sealOrder(String[] rooms) {
        G = new boolean[rooms.length][rooms.length];
        adj= new int[rooms.length][rooms.length];
        reach = new boolean[rooms.length];
        used = new boolean[rooms.length];
        count = new int[rooms.length];
        for ( int i = 0 ; i < rooms.length ; i++){

            String[] arr  = rooms[i].split(",");
            for ( int j = 0 ; j < arr.length ;j++){
                if (arr[j] == ""){
                    continue;
                }
                int neighbor = Integer.parseInt(arr[j]);
                G[i][neighbor] = true;
                adj[i][count[i]++] = neighbor;
            }
        }
        int n = rooms.length;
        int[] result = new int[rooms.length];
        int rescount = 0;
        for (int i = n ; i > 0 ; i--){
            for ( int j = 0 ; j < n ; j++){
                if (used[j])
                    continue;
                reach = new boolean[n];
                dfs = 0;
                used[j] = true;
                visit(0);

                if (dfs == i-1){
                    result[rescount++]= j;
                    break;
                }
                used[j] = false;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] str = 	{"1", "2", "3", "1"};
        int[] result = new UndergroundVault().sealOrder(str);
        for ( int i = 0 ; i < result.length ; i++){
            System.out.println(result[i]);
        }
    }
}

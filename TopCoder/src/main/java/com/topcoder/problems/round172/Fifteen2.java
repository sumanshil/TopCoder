package com.topcoder.problems.round172;

/**
 * Created by sshil on 6/24/17.
 */
//https://community.topcoder.com/stat?c=problem_statement&pm=1850&rd=4665

public class Fifteen2 {

    private int[][] position = {
            {0,2,4,6,8},
            {1,3,5,7}
    };

    public String outcome(int[] moves) {

        int[] positions = new int[9];
        boolean[] done = new boolean[15];
        for (int i = 0 ; i < moves.length ; i++){
            positions[i] = moves[i];
            done[moves[i]] = true;
        }

        int result = Integer.MAX_VALUE;
        boolean hasFailed = false;
        for (int i = 1 ; i <= 9; i ++){
            if (!done[i]){
                done[i] = true;
                int j;
                for (j = 0 ; j < positions.length ; j++){
                    if (positions[j] == 0){
                        positions[j] = i;
                        break;
                    }
                }
                if (win(done, positions)){
                    if ( i < result){
                        result = i;
                    }
                }else {
                    hasFailed = true;
                }
                positions[j] = 0;
                done[i] = false;
            }
        }
        if (result == Integer.MAX_VALUE){
            return "LOOSE";
        }
        return  (!hasFailed ? "WIN "+result : "LOOSE");
    }

    private boolean win(boolean[] done, int[] positions) {
        if (check(positions) == 0){
            return true;
        }
        if (check(positions) == 1){
            return false;
        }
        boolean result = true;
        for (int i = 1 ; i <= 9 ; i++) {
            if (!done[i]){
                done[i] = true;
                for ( int j = 0 ; j < positions.length ; j++){
                    if (positions[j] == 0){
                        positions[j] = i;
                        result &= win(done, positions);
                        positions[j] = 0;
                    }

                }
                done[i] = false;
            }
        }
        return result;
    }

    private int check(int[] positions) {
        int[] arr = position[1];
        for ( int i = 0 ; i < arr.length ; i++){
            for ( int j = i+1 ; j < arr.length ; j++){
                for (int k = j+1 ; k < arr.length ; k++){
                    if (positions[arr[i]] > 0 && positions[arr[j]] > 0 && positions[arr[k]] > 0) {
                        if (positions[arr[i]] + positions[arr[j]] + positions[arr[k]] == 15) {
                            return 0;
                        }
                    }
                }
            }
        }
        arr = position[0];
        for ( int i = 0 ; i < arr.length ; i++){
            for ( int j = i+1 ; j < arr.length ; j++){
                for (int k = j+1 ; k < arr.length ; k++){
                    if (positions[arr[i]] > 0 && positions[arr[j]] > 0 && positions[arr[k]] > 0) {
                        if (positions[arr[i]] + positions[arr[j]] + positions[arr[k]] == 15) {
                            return 1;
                        }
                    }
                }
            }
        }

        return 2;
    }

    public static void main(String[] args) {
        int[] arr =

                {7, 5, 9, 6, 8, 1, 2}


                ;
        String str = new Fifteen2().outcome(arr);
        System.out.println(str);
    }
}

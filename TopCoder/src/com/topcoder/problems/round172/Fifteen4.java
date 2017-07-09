package com.topcoder.problems.round172;

/**
 * Created by sshil on 7/5/17.
 */
public class Fifteen4 {

    private boolean dealerWon = false;
    private boolean patronwon = false;

    public String outcome(int[] moves) {
        int[] allMoves = new int[9];
        boolean[] moveCompleted = new boolean[10];
        boolean[] positionTaken = new boolean[9];

        for ( int i = 0 ; i < moves.length ; i++){
            allMoves[i] = moves[i];
            moveCompleted[moves[i]] = true;
            positionTaken[i] = true;
        }

        for (int i = 1 ; i <= 9 ; i++ ) {
            if (!moveCompleted[i]) {
                moveCompleted[i] = true;
                allMoves[moves.length] = i;

                for (int j = moves.length+1; j < 9; j++) {
                    dealerWon = false;
                    patronwon = false;
                    dfs(allMoves, moveCompleted, positionTaken, j);
                    if (patronwon) {
                        return "WIN " + i;
                    }
                }
                allMoves[moves.length] = 0;
                moveCompleted[i] = false;
            }
        }
        return "LOOSE";
    }

    private int[][] position = {
            {0,2,4,6,8},
            {1,3,5,7}
    };

    private void dfs(int[] allMoves, boolean[] moveCompleted, boolean[] positionTaken, int posTaken) {
        if (dealerWon || patronwon ) {
            return;
        }
        if (dealerWins(allMoves)){
            dealerWon = true;
            return;
        }
        if (patronWins(allMoves)){
            patronwon = true;
            return;
        }

        if (movesFull(allMoves)){
            dealerWon = true;
            return;
        }

        for ( int i = 1 ; i <= 9 ; i++) {
            if (!moveCompleted[i]){
                moveCompleted[i] = true;
                for ( int j = posTaken ; j < allMoves.length ; j++) {
                    if (!positionTaken[j]){
                       positionTaken[j] = true;
                       allMoves[j] = i;
                       dfs(allMoves, moveCompleted, positionTaken, posTaken);
                       positionTaken[j] = false;
                       allMoves[j] = 0;
                    }
                }
                moveCompleted[i] = false;
            }
        }
    }

    private boolean patronWins(int[] allMoves) {
        int[] posArr = position[1];
        for ( int i = 0 ; i < posArr.length ; i++) {
            for ( int j = i + 1; j < posArr.length ; j++) {
                for ( int k = j+1 ; k < posArr.length ; k++) {
                    if (allMoves[posArr[i]] > 0 && allMoves[posArr[j]] > 0 && allMoves[posArr[k]] > 0) {
                        if (allMoves[posArr[i]] + allMoves[posArr[j]] + allMoves[posArr[k]] == 15){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean movesFull(int[] allMoves) {
        for ( int i = 0 ; i < allMoves.length ; i++) {
            if (allMoves[i] == 0){
                return false;
            }
        }
        return true;
    }

    private boolean dealerWins(int[] allMoves) {
        int[] posArr = position[0];
        for ( int i = 0 ; i < posArr.length ; i++) {
            for ( int j = i + 1; j < posArr.length ; j++) {
                for ( int k = j+1 ; k < posArr.length ; k++) {
                    if (allMoves[posArr[i]] > 0 && allMoves[posArr[j]] > 0 && allMoves[posArr[k]] > 0) {
                        if (allMoves[posArr[i]] + allMoves[posArr[j]] + allMoves[posArr[k]] == 15){
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] arr = {7, 8, 2};
        String str = new Fifteen4().outcome(arr);
        System.out.println(str);
    }
}

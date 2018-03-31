package com.geeksforgeeks.matrix;

/**
 * Created by sshil on 11/16/2015.
 */
public class CountNumberOfPathsWithAtmostKTurns {
    private int[][] matrix = null;
    private boolean[][] visited = null;

    private int result = 0;
    public int count(int m, int n, int k){
        matrix = new int[m][n];
        visited = new boolean[m][n];
        visited[0][0] = true;
        checkRecursive(0,
                       0,
                       m-1,
                       n-1,
                       false,
                       false,
                       0,
                       k);
        return result;
    }

    private void checkRecursive(int currentX,
                                int currentY,
                                int finishX,
                                int finishY,
                                boolean rowWiseMove,
                                boolean colWiseMove,
                                int currentTurnCount,
                                int totalTurnsAllowed) {
        if ( currentTurnCount > totalTurnsAllowed) {
            return;
        }
        if (currentX == finishX && currentY == finishY) {
            if (currentTurnCount <= totalTurnsAllowed) {
                result++;
            }
            return;
        }

        for ( int i = -1 ; i <=1 ; i++ ){
            for ( int j = -1; j <= 1 ; j++) {
                if ( (i == 0 && j == 0)
                    || ( i != 0 && j != 0)){
                    continue;
                }

                int newX = currentX + i;
                int newY = currentY + j;

                if (isValidPosition(newX, newY)) {
                    boolean isRowMove = false;
                    boolean isColMove = false;
                    boolean increaseCount = false;
                    if ( (i == 0)) {
                        if (colWiseMove) {
                            increaseCount = true;
                        }
                        isColMove = false;
                        isRowMove = true;
                    } else if ( j == 0) {
                        if(rowWiseMove) {
                            increaseCount = true;
                        }
                        isColMove = true;
                        isRowMove = false;
                    }
                    visited[newX][newY] = true;
                    checkRecursive(newX,
                                   newY,
                                   finishX,
                                   finishY,
                                   isRowMove,
                                   isColMove,
                                   increaseCount ? currentTurnCount+1 : currentTurnCount,
                                   totalTurnsAllowed);
                    visited[newX][newY] = false;
                }
            }
        }


    }

    private boolean isValidPosition(int newX, int newY) {
        return (newX >= 0 && newX < matrix.length)
            && (newY >= 0 && newY < matrix[0].length)
            && !visited[newX][newY];
    }

    public static void main(String[] args){
        int result = new CountNumberOfPathsWithAtmostKTurns().count(3, 3, 2);
        System.out.println(result);
    }
}

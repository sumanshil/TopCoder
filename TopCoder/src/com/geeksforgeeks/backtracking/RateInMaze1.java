package com.geeksforgeeks.backtracking;

//http://www.geeksforgeeks.org/backttracking-set-2-rat-in-a-maze/
public class RateInMaze1 {
    private int maze[][] = {
            {1, 0, 0, 0},
            {1, 1, 0, 1},
            {0, 1, 0, 0},
            {1, 1, 1, 1}
    };

    int[] x = {0, 1};
    int[] y = {1, 0};
    public void find (){
        int sol[][] = new int[maze.length][maze[0].length];
        for ( int i = 0 ; i < sol.length ; i++){
            for ( int j = 0 ; j < sol[0].length ; j++){
                sol[i][j] = -1;
            }
        }
        boolean result = recursive(0, 0, sol);

        if (result){
            printMatrix(sol);
        }
    }

    private void printMatrix(int[][] sol) {
        for ( int i = 0 ; i < sol.length ; i++) {
            for ( int j = 0 ; j < sol[0].length ; j++) {
                System.out.print(sol[i][j]+" ");
            }
            System.out.println();
        }
    }

    private boolean recursive(int indexX, int indexY, int[][] sol) {
        if (indexX == sol.length-1 && indexY == sol[0].length-1){
            return true;
        }

        for (int i = 0 ; i < 2 ; i++) {
            int newX = indexX + x[i];
            int newY = indexY + y[i];
            if (isSafe(newX, newY, sol)){
                sol[newX][newY] = 1;
                if (recursive(newX, newY, sol)){
                    return true;
                } else {
                    sol[newX][newY] = -1;
                }
            }
        }
        return false;

    }

    private boolean isSafe(int newX, int newY, int sol[][]) {
        return newX >= 0 && newX < maze.length && newY >= 0
               && newY < maze[0].length && maze[newX][newY] == 1 && sol[newX][newY] == -1;
    }

    public static void main(String[] args){
        new RateInMaze1().find();
    }
}

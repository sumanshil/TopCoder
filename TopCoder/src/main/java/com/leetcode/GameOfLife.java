package com.leetcode;

//https://leetcode.com/problems/game-of-life/
public class GameOfLife {

    public void gameOfLife(int[][] board) {
        for (int i = 0 ; i < board.length ; i++) {
            for (int j = 0 ; j < board[0].length ; j++) {
                int count = getLiveNeighborsCount(i, j, board);
                if (board[i][j] == 1) {
                    if (!(count == 2 || count == 3)) {
                        board[i][j] = 2;
                    }
                } else {
                    if (count == 3) {
                        board[i][j] = -1;
                    }
                }
            }
        }

        for (int i = 0 ; i < board.length ; i++) {
            for (int j = 0 ; j < board[0].length ; j++) {
                if (board[i][j] == 2) {
                    board[i][j] = 0;
                } else if (board[i][j] == -1) {
                    board[i][j] = 1;
                }
            }
        }

        for (int i = 0 ; i < board.length ; i++) {
            for (int j = 0 ; j < board[0].length ; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private int getLiveNeighborsCount(int row, int col, int[][] board) {
        int count = 0;
        for (int i = -1 ; i <= 1; i++) {
            for (int j = -1 ; j <= 1 ; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int newRow = row + i;
                int newCol = col + j ;
                if (newRow >= 0 && newRow < board.length && newCol >= 0
                    && newCol < board[0].length && board[newRow][newCol] >= 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        new GameOfLife().gameOfLife(matrix);
    }
}

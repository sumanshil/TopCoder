package com.leetcode;

//https://leetcode.com/problems/surrounded-regions/
public class SurroundedRegions {

    public void solve(char[][] board) {
        int rowLength = board.length;
        if (rowLength == 0) {
            return;
        }
        int colLength = board[0].length;
        boolean[][] visited = new boolean[rowLength][colLength];

        for ( int i = 0 ; i < colLength ; i++) {
            if (board[0][i] == 'O') {
                recursive(board, 0, i, visited);
            }
        }

        for ( int i = 0 ; i < rowLength ; i++) {
            if (board[i][colLength-1] == 'O') {
                recursive(board, i , colLength-1, visited);
            }
        }

        for ( int i = colLength-1 ; i >= 0 ; i--) {
            if (board[rowLength-1][i] == 'O') {
                recursive(board, rowLength-1 , i, visited);
            }
        }

        for ( int i = rowLength-1 ; i >= 0 ; i--) {
            if (board[i][0] == 'O') {
                recursive(board, i , 0, visited);
            }
        }
        for (int i = 0 ; i < rowLength ; i ++) {
            for ( int j = 0 ; j < colLength ; j++) {
                if (board[i][j] == 'Y') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }

        for (int i = 0 ; i < rowLength ; i++) {
            for (int j = 0 ; j < colLength ; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

    private void recursive(char[][] board, int row, int col, boolean[][] visited) {
        if (!valid(board, row, col)) {
            return;
        }

        if (board[row][col] == 'X' || board[row][col] == 'Y' || visited[row][col]) {
            return;
        }

        if (board[row][col] == 'O') {
            visited[row][col] = true;
            board[row][col] = 'Y';
            recursive(board, row + 1, col , visited);
            recursive(board, row - 1, col , visited);
            recursive(board, row, col + 1 , visited);
            recursive(board, row, col - 1 , visited);
        }
    }

    private boolean valid(char[][] board, int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        new SurroundedRegions().solve(board);
    }
}


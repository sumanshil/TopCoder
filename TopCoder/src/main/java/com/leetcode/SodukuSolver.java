package com.leetcode;

class SodukuSolver {
    public boolean solveSudoku(char[][] board) {
        boolean[][] column = new boolean[9][10];
        boolean[][] row = new boolean[9][10];
        boolean[][] boxes = new boolean[9][10];
        initialSudoku(board, row, column, boxes);
        return solveSudokuHelper(board, row, column, boxes);
    }

    private boolean solveSudokuHelper(char[][] board,
                                      boolean[][] row,
                                      boolean[][] col,
                                      boolean[][] boxes) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                if (board[i][j] != '.') continue;

                for(char ch = '1'; ch <= '9'; ch++) {
                    int chInt = ch - '0';
                    if (row[i][chInt] || col[j][chInt] || boxes[i/3*3 + j/3][chInt]) continue;

                    row[i][chInt]= true; col[j][chInt] = true; boxes[i/3*3 + j/3][chInt] = true;
                    board[i][j] = ch;
                    if (solveSudokuHelper(board, row, col, boxes)) return true;
                    row[i][chInt]= false; col[j][chInt] = false; boxes[i/3*3 + j/3][chInt] = false;
                    board[i][j] = '.';
                }

                return false;
            }
        }
        return true;


    }

    public void initialSudoku(char[][] board,
                              boolean[][] row,
                              boolean[][] col,
                              boolean[][] boxes) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                if (board[i][j] == '.') continue;

                int val = board[i][j] - '0';

                row[i][val] = true;
                col[j][val] = true;
                boxes[i/3*3 + j/3][val] = true;
            }
        }
    }

    public static void main(String[] args) {
        char[][] sudoku = {{'5','3','4', '.','.','.', '6','3','.'},
                           {'.','.','.', '.','.','.', '.','.','.'},
                           {'5','.','.', '.','.','.', '.','9','.'},
                           {'.','.','.', '5','6','.', '.','.','.'},
                           {'4','.','3', '.','.','.', '.','.','1'},
                           {'.','.','.', '7','.','.', '.','.','.'},
                           {'.','.','.', '5','.','.', '.','.','.'},
                           {'.','.','.', '.','.','.', '.','.','.'},
                           {'.','.','.', '.','.','.', '.','.','.'}};

        boolean result = new SodukuSolver().solveSudoku(sudoku);
        System.out.println(result);

    }
}
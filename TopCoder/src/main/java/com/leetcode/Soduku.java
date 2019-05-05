package com.leetcode;

import java.util.HashSet;
import java.util.Set;

import com.geeksforgeeks.recursion.SodukuSolversDescription;

//https://leetcode.com/problems/valid-sudoku/
class Solution {
    /*
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0 ; i < board.length ; i++) {
            Set<Character> set = new HashSet<>();
            for ( int j = 0 ; j < board[0].length ; j++) {
                if (set.contains(board[i][j])) {
                    return false;
                } else if (board[i][j] != '.') {
                    set.add(board[i][j]);
                }
            }
        }

        for (int i = 0 ; i < board[0].length ; i++) {
            Set<Character> set = new HashSet<>();
            for ( int j = 0 ; j < board.length ; j++) {
                if (set.contains(board[j][i])) {
                    return false;
                } else if (board[j][i] != '.') {
                    set.add(board[j][i]);
                }

            }
        }
        for (int startRow = 0 ; startRow < board.length ; startRow = startRow+3) {
          for ( int startCol = 0 ; startCol < board[0].length ; startCol= startCol + 3) {
              Set<Character> set = new HashSet<>();
              for (int i = startRow; i < startRow + 3; i++) {
                  for (int j = startCol; j < startCol + 3; j++) {
                      if (set.contains(board[i][j])) {
                          return false;
                      } else if (board[i][j] != '.') {
                          set.add(board[i][j]);
                      }
                  }
              }
          }
        }
        return true;
    }
    */
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rowMap = new boolean[9][9];
        boolean[][] colMap = new boolean[9][9];
        boolean[][] cubeMap = new boolean[9][9];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] != '.'){
                    int entry = board[i][j] - '1';
                    if(rowMap[i][entry] || colMap[j][entry] || cubeMap[(i / 3) * 3 + (j/3)][entry])
                        return false;
                    rowMap[i][entry] = true;
                    colMap[j][entry] = true;
                    cubeMap[(i / 3) * 3 + (j / 3)][entry] = true;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        /*
       char[][] sudoku = {
               {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
               {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
               {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
               {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
               {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
               {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
               {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
               {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
               {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        */

        char[][] sudoku = {{'.','.','4', '.','.','.', '6','3','.'},
                           {'.','.','.', '.','.','.', '.','.','.'},
                           {'5','.','.', '.','.','.', '.','9','.'},
                           {'.','.','.', '5','6','.', '.','.','.'},
                           {'4','.','3', '.','.','.', '.','.','1'},
                           {'.','.','.', '7','.','.', '.','.','.'},
                           {'.','.','.', '5','.','.', '.','.','.'},
                           {'.','.','.', '.','.','.', '.','.','.'},
                           {'.','.','.', '.','.','.', '.','.','.'}};

       boolean result = new Solution().isValidSudoku(sudoku);
       System.out.println(result);
    }
}
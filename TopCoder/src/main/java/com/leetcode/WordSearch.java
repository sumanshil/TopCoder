package com.leetcode;

//https://leetcode.com/problems/word-search/
public class WordSearch {

    public boolean exist(char[][] board, String word) {
        boolean result = false;
        for (int i = 0 ; i < board.length ; i++) {
            for (int j = 0 ; j < board[0].length ; j++) {
                if (board[i][j] == word.charAt(0)) {
                    boolean[][] visited = new boolean[board.length][board[0].length];
                    result = recursive(board, i, j, word, 0);
                    if (result) {
                        return result;
                    }
                }
            }
        }
        return result;
    }

    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private boolean recursive(char[][] board, int row, int col, String word, int index) {
        if (index == word.length()) {
            return true;
        }

        if ( row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }
        if (board[row][col] != word.charAt(index) || board[row][col] == '$') {
            return false;
        }
        char temp = board[row][col];
        board[row][col] = '$';
        /*
        for (int[] arr : dirs) {
            if (recursive(board, row + arr[0], col + arr[1], word, index+1)) {
                return true;
            }
        }
         */
        if (recursive(board,row + 1, col, word, index+1)
            || recursive(board,row - 1, col, word, index+1)
            || recursive(board,row, col + 1, word, index+1)
            || recursive(board,row, col - 1, word, index+1)) {
            return true;
        }
        board[row][col] = temp;
        return false;
    }

    public static void main(String[] args) {
        char[][] matrix = {{'A','B','C','E'},
                           {'S','F','E','S'}
                          ,{'A','D','E','E'}};
       String str =  "ABCESEEEFS";

        //char[][] matrix = {
        //        {'A', 'B', 'C', 'E'},
        //        {'S', 'F', 'C', 'S'},
        //        {'A', 'D', 'E', 'E'}
        //};

       /*
        char[][] matrix = {
                {'C','A','A'},
                {'A','A','A'},
                {'B','C','D'}};
                8?
        */
        boolean result = new WordSearch().exist(matrix,str);
        System.out.println(result);
    }
}

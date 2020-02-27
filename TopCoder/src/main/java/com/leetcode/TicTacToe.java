package com.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/design-tic-tac-toe/
public class TicTacToe {

    Map<String, Map<Integer, Integer>> map = new HashMap<>();

    Map<String, Integer> leftDiagonalMap = new HashMap<>();

    Map<String, Integer> rightDiagonalMap = new HashMap<>();

    private int dimension;
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        this.dimension = n;
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        String input;
        if (player == 1) {
            input = "X";
        } else {
            input = "0";
        }

        int rowIndex = row;
        int colIndex = col + dimension;

        Map<Integer, Integer> enclosedMap = map.getOrDefault(input, new HashMap<>());
        Integer rowCount = enclosedMap.getOrDefault(rowIndex, 0);
        Integer colCount = enclosedMap.getOrDefault(colIndex, 0);

        enclosedMap.put(rowIndex, rowCount + 1);
        enclosedMap.put(colIndex, colCount + 1);
        map.put(input, enclosedMap);

        if (rowCount + 1 == dimension || colCount + 1 == dimension) {
            return player;
        }

        if (row == col) {
            Integer count = leftDiagonalMap.getOrDefault(input, 0);

            if (count + 1 == dimension) {
                return player;
            }
            leftDiagonalMap.put(input, count + 1);
        }
        if (row + col == dimension -1) {
            Integer count = rightDiagonalMap.getOrDefault(input, 0);
            if (count + 1 == dimension) {
                return player;
            }
            rightDiagonalMap.put(input, count + 1);
        }
        return 0;
    }

    public static void main(String[] args) {

        TicTacToe toe = new TicTacToe(3);
        System.out.println(toe.move(0, 0, 1));
        System.out.println(toe.move(0, 2, 2));
        System.out.println(toe.move(2, 2, 1));
        System.out.println(toe.move(1, 1, 2));
        System.out.println(toe.move(2, 0, 1));
        System.out.println(toe.move(1, 1, 2));

    }
}

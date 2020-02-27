package com.leetcode;

import org.omg.PortableInterceptor.INACTIVE;

//https://leetcode.com/problems/walls-and-gates/
public class WallsAndGates {

    public void wallsAndGates(int[][] rooms) {
        for (int i = 0 ; i < rooms.length ; i++) {
            for (int j = 0 ; j < rooms[0].length ; j++) {
                if (rooms[i][j] == 0) {
                    recursive(rooms, i, j, 0);
                }
            }
        }
    }

    private void recursive(int[][] rooms, int row, int col, int dist) {
        if (row < 0 || row >= rooms.length || col < 0 || col >= rooms[0].length || rooms[row][col] < dist) {
            return;
        }

        rooms[row][col] = dist;
        recursive(rooms, row , col + 1, dist + 1);
        recursive(rooms, row , col - 1, dist + 1);
        recursive(rooms, row + 1, col, dist + 1);
        recursive(rooms, row - 1, col, dist + 1);

    }

    private boolean isValid(int[][] rooms, int newRow, int newCol) {
        return newRow >= 0 && newRow < rooms.length && newCol >= 0 && newCol < rooms[0].length;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {Integer.MAX_VALUE, -1, 0, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -1},
                {Integer.MAX_VALUE, -1, Integer.MAX_VALUE, -1},
                {0, -1, Integer.MAX_VALUE, Integer.MAX_VALUE}
        };
        new WallsAndGates().wallsAndGates(matrix);

        for (int i = 0 ; i < matrix.length ; i++) {
            for (int j = 0 ; j < matrix[0].length ; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

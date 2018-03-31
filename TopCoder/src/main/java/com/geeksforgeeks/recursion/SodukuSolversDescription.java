package com.geeksforgeeks.recursion;

import java.util.*;
import java.util.stream.IntStream;

//https://leetcode.com/problems/sudoku-solver/description/
public class SodukuSolversDescription {

    private static Map<Integer, Set<Integer>> rowMember = new HashMap<>();
    private static Map<Integer, Set<Integer>> colMember = new HashMap<>();

    private static Map<Integer, Integer> COL_SUM = new HashMap<>();
    private static Map<Integer, Integer> ROW_SUM = new HashMap<>();

    private static int[][] SODUKU_MATRIX = {
            {5,3,0,0,7,0,0,0,0},
            {6,0,0,1,9,5,0,0,0},
            {0,9,8,0,0,0,0,6,0},
            {8,0,0,0,6,0,0,0,3},
            {4,0,0,8,0,3,0,0,1},
            {7,0,0,0,2,0,0,0,6},
            {0,6,0,0,0,0,2,8,0},
            {0,0,0,4,1,9,0,0,5},
            {0,0,0,0,8,0,0,7,9}
    };

    static class MatrixPosition {
        int row, col;
        MatrixPosition(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    private static List<MatrixPosition> emptySlots = new ArrayList<>();

    private static void preprocess() {
        for ( int i = 0 ; i < 9 ; i++) {
            for ( int j = 0 ; j < 9 ; j++) {
                if (SODUKU_MATRIX[i][j] != 0) {
                    if (rowMember.containsKey(i)) {
                        rowMember.get(i).add(SODUKU_MATRIX[i][j]);
                    } else {
                        Set<Integer> set = new HashSet<>();
                        set.add(SODUKU_MATRIX[i][j]);
                        rowMember.put(i, set);
                    }
                    if (colMember.containsKey(j)) {
                        colMember.get(j).add(SODUKU_MATRIX[i][j]);
                    } else {
                        Set<Integer> set = new HashSet<>();
                        set.add(SODUKU_MATRIX[i][j]);
                        colMember.put(j, set);
                    }

                    if (ROW_SUM.containsKey(i)) {
                        Integer currentSum = ROW_SUM.get(i);
                        ROW_SUM.put(i, currentSum + SODUKU_MATRIX[i][j]);
                    } else {
                        ROW_SUM.put(i, SODUKU_MATRIX[i][j]);
                    }

                    if (COL_SUM.containsKey(j)) {
                        Integer currentSum = COL_SUM.get(j);
                        COL_SUM.put(j, currentSum + SODUKU_MATRIX[i][j]);
                    } else {
                        COL_SUM.put(j, SODUKU_MATRIX[i][j]);
                    }
                } else {
                    emptySlots.add(new MatrixPosition(i, j));
                }
            }
        }
    }

    private static boolean solved = false;

    public static boolean solve(int emptySlotIndex) {
        if (emptySlotIndex >= emptySlots.size()){
            return false;
        }
        if (allFilled()){
            printMatrix();
            return true;
        }

        printMatrix();
        System.out.println("==========");
        /*try {
            Thread.sleep(3000);
        } catch (InterruptedException e){

        }*/
        MatrixPosition matrixPosition = emptySlots.get(emptySlotIndex);

        for ( int i = 1 ; i <=9 ; i++ ) {
            if (notInRow(matrixPosition.row, i)
                 && notInCol(matrixPosition.col, i)
                 && notInBox(matrixPosition.row, matrixPosition.col, i)){
                SODUKU_MATRIX[matrixPosition.row][matrixPosition.col] = i;
                if (solve(emptySlotIndex+1)) {
                    return true;
                }
                SODUKU_MATRIX[matrixPosition.row][matrixPosition.col] = 0;
            }
        }
        return false;
    }

    private static boolean notInBox(int row, int col, int number) {
        int startRow = row - (row % 3);
        int startCol = col - (col % 3);
        for ( int i = startRow ; i < startRow + 3 ; i++) {
            for ( int j = startCol ; j < startCol + 3 ; j++){
                if (SODUKU_MATRIX[i][j] == number) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean notInCol(int col, int number) {
        for ( int i = 0 ; i < SODUKU_MATRIX.length ; i++) {
            if (SODUKU_MATRIX[i][col] == number) {
                return false;
            }
        }
        return true;
    }

    private static boolean notInRow(int row, int number) {
        for ( int i = 0 ; i < SODUKU_MATRIX[0].length ; i++) {
            if (SODUKU_MATRIX[row][i] == number) {
                return false;
            }
        }
        return true;
    }

    private static boolean allFilled() {
        for ( int i = 0 ; i < SODUKU_MATRIX.length ; i++) {
            for ( int j = 0 ; j < SODUKU_MATRIX[0].length ; j++){
                if (SODUKU_MATRIX[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }

    private static void printMatrix() {
        for ( int i = 0 ; i < SODUKU_MATRIX.length ; i++) {
            for ( int j = 0 ; j < SODUKU_MATRIX[0].length ; j++) {
                System.out.print(SODUKU_MATRIX[i][j]+" ");
            }
            System.out.println();
        }

    }

    private static boolean solved() {
        for (int i = 0 ; i < SODUKU_MATRIX.length ; i ++) {
            if (ROW_SUM.get(i) != 45){
                return false;
            }
            if (COL_SUM.get(i) != 45){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        preprocess();
        if(solve(0)){
            System.out.println("Solved");
        }
        //int sum = IntStream.range(0, 10).sum();
        //System.out.println(sum);
    }
}

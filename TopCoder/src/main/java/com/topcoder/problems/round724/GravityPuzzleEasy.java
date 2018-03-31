package com.topcoder.problems.round724;

import java.util.Arrays;

//https://community.topcoder.com/stat?c=problem_statement&pm=14742
public class GravityPuzzleEasy {

    public String[] solve(String[] board) {
        char matrix[][] = new char[board.length][board[0].length()];
        for ( int i = 0 ; i < board.length ; i++) {
            for ( int j = 0 ; j < board[0].length() ; j++) {
                matrix[i][j] = board[i].charAt(j);
            }
        }
        for (int rowLength = matrix.length-2; rowLength >= 0 ; rowLength--) {
            for (int colIndex = 0 ; colIndex < matrix[0].length ; colIndex ++) {
                if (matrix[rowLength][colIndex] == '#') {
                    int currentRow = rowLength;
                    int currentCol = colIndex;
                    int resultRow = -1;
                    for (int rowToBeChecked = currentRow + 1 ; rowToBeChecked < board.length ; rowToBeChecked++) {
                        if (matrix[rowToBeChecked][currentCol] == '.'){
                            resultRow = rowToBeChecked;
                        }
                    }
                    if (resultRow != -1){
                        matrix[resultRow][currentCol] = '#';
                        matrix[rowLength][currentCol] = '.';
                    }
                }
            }

        }

        String result[] = new String[matrix.length];
        for (int i = 0 ; i < matrix.length ; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for ( int j = 0 ; j < matrix[0].length ; j++) {
                stringBuilder.append(matrix[i][j]);
            }
            result[i] = stringBuilder.toString();
        }
        return result;
    }
    public static void main(String[] args) {
        String str[] =
                {"#.##..#.##..##...#..#...#.#.#.#..", ".#.#.#.#.##..##...#.#...##..###..", "#....##..##...###....#...##.###.#", "#.#..#....#......##......##.##..#", "....#.#.#.##.#........#......#...", "##.#....##.#........#.....#......", "#.#####.#....##..#.#....#.#....#.", ".####...#....#.#...#.#####.#.#.#.", "#......#.###...###...##.#.#..#.##", ".#.....#....#.#...#.#.##.#.##.#.#", ".#.##.#.#.#......#..#..#...###.##", "##...#..###.#.##...#...#.#..#.###"}
                ;
        String[] result = new GravityPuzzleEasy().solve(str);
        Arrays.stream(result).forEach(System.out::println);
    }
}

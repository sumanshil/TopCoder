package com.dailyprogramming.matrix;

import java.util.ArrayList;
import java.util.List;

public class ColorMaze {
    private static char[][] maze = {
            {'B','0','R','0','Y'},
            {'0','R','B','G','R'},
            {'B','0','G','0','Y'},
            {'Y','G','B','Y','G'},
            {'R','0','R','B','R'}
    };
    private static char[] sequence = {'0','G'};

    private static boolean[][] visited = null;

    static class Position {
        int x;
        int y;
        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "{"+x+":"+y+"}";
        }
    }

    private static List<Position> list = new ArrayList<>();

    public void find(){
        char c = sequence[0];
        for ( int i = 0 ; i < maze[0].length ; i++) {
            if (maze[maze.length-1][i] == c) {
                boolean result = recursive(maze.length-1,
                                           i,
                                           0);
                if (result){
                    list.stream().forEach(System.out::println);
                    break;
                }
            }
        }
    }

    private boolean recursive(int row, int col, int sequenceIndex) {
        if (!isValid(row, col)){
            return false;
        }
        if (visited[row][col]){
            return false;
        }
        if (maze[row][col] != sequence[sequenceIndex]){
            return false;
        }
        if (row == 0){
            return true;
        }

        list.add(new Position(row, col));
        visited[row][col] = true;
        sequenceIndex = (sequenceIndex + 1) % sequence.length;
        if (recursive(row, col+1, sequenceIndex)){
            return true;
        }

        if (recursive(row, col-1, sequenceIndex)){
            return true;
        }

        if (recursive(row+1, col, sequenceIndex)){
            return true;
        }

        if (recursive(row-1, col, sequenceIndex)){
            return true;
        }

        visited[row][col] =false;
        list.remove(list.size()-1);
        return false;
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < maze.length && col >= 0 && col < maze[0].length;
    }


    public static void main(String[] args) {
        visited = new boolean[maze.length][maze[0].length];
        new ColorMaze().find();
    }
}

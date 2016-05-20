package com.geeksforgeeks.matrix;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sshil on 5/13/2016.
 */
public class FindAllOccurrencesOfAGivenWordInMatrix {

    private char[][] matrix = {
        {'B', 'N', 'E', 'Y', 'S'},
        {'H', 'E', 'D', 'E', 'S'},
        {'S', 'G', 'N', 'D', 'E'}
    };

    private boolean[][] visited = new boolean[3][5];
    private int[] xDiff = {0, 0, 1, -1,  1, -1, -1, 1};
    private int[] yDiff = {1,-1, 0,  0, -1,  1, -1, 1};

    static class MatrixLocation {
        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        private String data;
        private int x;
        private int y;

        public MatrixLocation(String data, int x, int y) {
            this.data = data;
            this.x = x;
            this.y = y;
        }

        public String toString(){
            return "Data ->" +this.data +" x : "+ x+" y : "+y;
        }
    }

    private List<MatrixLocation> list = null;
    private boolean isValid(int x, int y){
        return  x>=0 && x <matrix.length && y>=0 && y < matrix[0].length && !visited[x][y];
    }

    public void find(String word){
        for ( int i = 0 ; i < matrix.length; i++) {
            for ( int j = 0 ; j< matrix[0].length; j++) {
                if (matrix[i][j] == word.charAt(0)){
                    list = new LinkedList<>();
                    list.add(new MatrixLocation(matrix[i][j]+"", i, j));
                    startSearching(i,j, word);
                }
            }
        }
    }

    private void startSearching(int startX, int startY, String word) {
        if (list.size() == word.length()){
            StringBuffer sb = new StringBuffer();
            list.stream().map(MatrixLocation::getData).forEach(sb::append);
            if (sb.toString().equals(word)) {
                list.stream().forEach(System.out::println);
                System.out.println();
            }
            return;
        }

        for ( int i = 0 ; i < xDiff.length ; i++){
            int xChange = startX+xDiff[i];
            int yChange = startY+yDiff[i];
            if(isValid(xChange, yChange)){
                visited[xChange][yChange] = true;
                list.add(new MatrixLocation(matrix[xChange][yChange]+"", xChange, yChange));
                startSearching(xChange, yChange, word);
                visited[xChange][yChange] = false;
                list.remove(list.size()-1);
            }
        }
    }


    public static void main(String[] args) {
        new FindAllOccurrencesOfAGivenWordInMatrix().find("DES");
    }
}

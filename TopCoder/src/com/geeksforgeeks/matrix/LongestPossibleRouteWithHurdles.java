package com.geeksforgeeks.matrix;

/**
 * Created by sshil on 8/5/2016.
 */
// http://www.geeksforgeeks.org/longest-possible-route-in-a-matrix-with-hurdles/
public class LongestPossibleRouteWithHurdles {

    private int maxDistance = Integer.MIN_VALUE;
    private boolean[][] visited = null;
    private int[][] matrix = null;
    public void find(int[][] matrix){
        visited = new boolean[matrix.length][matrix[0].length];
        this.matrix = matrix;
        visited[0][0] = true;
        recursive(0,0,0);

        System.out.println(maxDistance);
    }

    private int[] diffX = {0, 0 , 1, -1};
    private int[] diffY = {1,-1 , 0,  0};

    private void recursive(int currentX, int currentY, int distance) {
        if (!canMove(currentX, currentY)) {
            if (distance > maxDistance){
                maxDistance = distance;
            }
            return;
        }

        for ( int i = 0 ; i < 4 ; i++){
            int newX = currentX + diffX[i];
            int newY = currentY + diffY[i];
            if (isValid(newX, newY)){
                visited[newX][newY] = true;
                recursive(newX, newY, distance+1);
                visited[newX][newY] = false;
            }

        }
    }

    private boolean canMove(int currentX, int currentY) {
        for ( int i = 0 ; i < 4 ; i++){
            int x = currentX + diffX[i];
            int y = currentY + diffY[i];
            if (isValid(x, y) && !visited[x][y]){
                return true;
            }
        }
        return false;
    }

    private boolean isValid(int currentX, int currentY) {
        return currentX >= 0
            && currentX < matrix.length
            && currentY >= 0
            && currentY < matrix[0].length
            && !visited[currentX][currentY]
            && matrix[currentX][currentY] != 0;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        };
//        int[][] matrix = {
//            {1, 1, 1, 1},
//            {1, 1, 0, 1},
//            {1, 1, 1, 1},
//        };

        new LongestPossibleRouteWithHurdles().find(matrix);
    }
}

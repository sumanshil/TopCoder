package com.topcoder.problems.round170;

/**
 * Created by sshil on 5/7/2016.
 */
//https://community.topcoder.com/stat?c=problem_statement&pm=1864&rd=4655
public class CityLink {

    public int timeTaken(int[] x, int[] y){
        int[][] matrix = new int[x.length][y.length];
        for ( int i = 0 ; i < x.length; i++) {
            for ( int j = 0 ; j < y.length ; j++) {
                if (i != j){
                    int x1 = x[i];
                    int y1 = y[i];
                    int x2 = x[j];
                    int y2 = y[j];
                    if ((x1 != x2 && x1 != y2)||(y1 != x2 && y1 != y2) ){
                        // does not share co-ordinates
                        matrix[i][j] = Math.max(Math.abs(x2-x1), Math.abs(y2-y1));
                    } else if(x1 == x2) {
                        matrix[i][j] = (Math.abs(y2-y1)+1)/2;
                    } else if (y1 == y2){
                        matrix[i][j] = (Math.abs(x2-x1)+1)/2;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {

    }
}

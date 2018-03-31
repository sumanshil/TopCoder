package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 8/25/16.
 */
// http://www.geeksforgeeks.org/minimum-cost-make-two-strings-identical/
public class MinCostToMakeTwoStringsIdentical {

    public void find(String x, String y, int costX, int costY){
        int result = recursive(x, y, x.length()-1, y.length()-1, costX, costY);
        System.out.println(result);
    }

    private int recursive(String x, String y, int indexX, int indexY, int costX, int costY) {
        if (indexX < 0 && indexY < 0){
            return 0;
        }
        if (indexX < 0 && indexY >= 0){
            return (indexY+1)* costY;
        }
        if (indexX >= 0 && indexY < 0){
            return (indexX+1)*costX;
        }
        if (x.charAt(indexX) == y.charAt(indexY)){
            return recursive(x, y, indexX-1, indexY-1, costX, costY);
        } else {
            // delete from x
            return Math.min((costX+recursive(x, y, indexX-1, indexY, costX, costY)),
                    (costY+recursive(x, y, indexX, indexY-1, costX, costY)));
        }
    }

    private void dp(String x, String y, int costX, int costY) {
        int rowLength = x.length()+1;
        int colLength = y.length()+1;
        int[][] matrix = new int[rowLength][colLength];
        matrix[0][0] = 0;
        for ( int i = 1 ; i < colLength ; i++){
            matrix[0][i] = i * costY;
            //matrix[0][i] = 0;
        }

        for ( int j = 1 ; j < rowLength ; j++){
            matrix[j][0] = j * costX;
            //matrix[j][0] = 0;
        }

        for ( int i = 1 ; i < rowLength ; i++) {
            for ( int j = 1 ; j < colLength ; j++) {
                if (x.charAt(i-1) == y.charAt(j-1)) {
                    matrix[i][j] = matrix[i-1][j-1];
                } else {
                    matrix[i][j] = Math.min(costX + matrix[i-1][j],
                                            costY + matrix[i][j-1]);
                }
            }
        }

        System.out.println(matrix[rowLength-1][colLength-1]);
    }


    public static void main(String[] args) {
        String x = "ef";
        String y = "gh";
        int costX = 10;
        int costY = 20;
        new MinCostToMakeTwoStringsIdentical().dp(x, y, costX, costY);
    }
}

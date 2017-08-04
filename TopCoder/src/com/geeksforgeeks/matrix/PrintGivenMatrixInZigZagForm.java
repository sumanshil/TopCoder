package com.geeksforgeeks.matrix;

//http://www.geeksforgeeks.org/print-given-matrix-zigzag-form/
public class PrintGivenMatrixInZigZagForm {

    private int[][] matrix = {
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 10},
            {11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20}
    };

    int[] moveX = {0 , 0};
    int[] moveY = {1 ,-1};

    public void printZigZag(){
        int currentX = 0;
        int currentY = 0;
        int index = 0;

        while (true){
            System.out.println(matrix[currentX][currentY]);
            int newX = currentX + moveX[index];
            int newY = currentY + moveY[index];
            if ( !isValid(newX, newY)){
                currentX = currentX +1;
               // currentY = currentY ;
                if (!isValid(currentX, currentY)){
                    break;
                }
                index = (index + 1) % 2;
            } else {
                currentX = newX;
                currentY = newY;
            }
        }
    }

    private boolean isValid(int newX, int newY) {
        int maxRowLength = matrix.length;
        int maxColLength = matrix[0].length;

        return newX >= 0 && newX < maxRowLength && newY >= 0 && newY < maxColLength;

    }

    public static void main(String[] args) {
        new PrintGivenMatrixInZigZagForm().printZigZag();
    }
}

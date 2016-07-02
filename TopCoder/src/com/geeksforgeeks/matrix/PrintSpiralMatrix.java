package com.geeksforgeeks.matrix;

/**
 * Created by sshil on 5/26/2016.
 */
//http://www.geeksforgeeks.org/print-n-x-n-spiral-matrix-using-o1-extra-space/
public class PrintSpiralMatrix {

    private int[] xDiff = { 1, 0, -1,  0};
    private int[] yDiff = { 0, 1,  0, -1};
    int[][] matrix = null;

    public  void print(int dimension) {
        matrix = new int[dimension][dimension];
        int midX;
        int midY;
        if (dimension % 2 != 0) {
            midX = dimension / 2;
            midY = dimension / 2;
        } else {
            midX = (dimension / 2)-1;
            midY = dimension / 2;
        }
        startPrinting(midX, midY);
        printMatrix();
    }

    private void printMatrix() {
        for ( int i = 0 ; i < matrix.length ; i++) {
            for(int j = 0 ; j < matrix[0].length ; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean isValid(int x, int y){
        return x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length;
    }

    private void startPrinting(int midX, int midY) {
        int stepCount = 0;
        int targetStepCount = 2;
        matrix[midX][midY] = 1;
        int currentX = midX;
        int currentY = midY-1;
        int currentNumber = 2;
        int diffIndex = 0;
        while(true){
            if (stepCount++ < targetStepCount){
                if (isValid(currentX, currentY)){
                    matrix[currentX][currentY] = currentNumber++;
                    printMatrix();
                    if (stepCount < targetStepCount) {
                        currentX = currentX + xDiff[diffIndex];
                        currentY = currentY + yDiff[diffIndex];
                    }
                }
            } else if (stepCount >= targetStepCount){
                // we have reached maximum step count
                diffIndex = (diffIndex + 1)%4;
                if (diffIndex == 0){
                    // we have to start from new spiral
                    //currentX = currentX;
                    currentY = currentY-1;
                    if (!isValid(currentX, currentY)){
                        break;
                    }
                    targetStepCount = targetStepCount+2;
                    stepCount = 0;
                } else {
                    stepCount = 0;
                    currentX = currentX+xDiff[diffIndex];
                    currentY = currentY+yDiff[diffIndex];
                    if (!isValid(currentX, currentY) || matrix[currentX][currentY] != 0){
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new PrintSpiralMatrix().print(6);
    }
}

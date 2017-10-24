package com.geeksforgeeks.rangeminimumquery;

//http://www.geeksforgeeks.org/range-minimum-query-for-static-array/
public class SimpleSolution {

    private static int matrix[][] = null;
    public static void find ( int[] arr, int min, int max) {
        preprocess(arr);
        System.out.println(matrix[min][max]);
    }

    private static void preprocess(int[] arr) {
        matrix = new int[arr.length][arr.length];
        for ( int i = 0 ; i < arr.length ; i++) {
            matrix[i][i] = arr[i];
        }

        for ( int i = 0 ; i < arr.length ; i++) {
            for ( int j = i+1 ; j < arr.length ; j++) {
                matrix[i][j] = Math.min(matrix[i][j-1], arr[j]);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {7,2 ,3 ,0, 5,10, 3,12,18};
        SimpleSolution.find(arr, 4, 8);
    }
}

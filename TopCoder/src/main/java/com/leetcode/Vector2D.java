package com.leetcode;

//https://leetcode.com/problems/flatten-2d-vector/
public class Vector2D {

    private int[][] matrix;
    private int rowPointer = 0;
    private int columPointer = 0;

    public Vector2D(int[][] v) {
        this.matrix = v;
    }

    public int next() {

        if (!hasNext()) {
            return -1;
        }
        return this.matrix[rowPointer][columPointer++];
    }

    public boolean hasNext() {
        while (rowPointer < this.matrix.length && this.columPointer >= this.matrix[rowPointer].length) {
            columPointer = 0;
            rowPointer++;
        }
        return  rowPointer < this.matrix.length;
    }


    public static void main(String[] args) {
        /*
        int[][] matrix = {
                {},
                {3},
        };

        */
        int[][] matrix = {
                {1, 2},
                {3},
                {4}
        };
        Vector2D iterator = new Vector2D(matrix);


        System.out.println(iterator.next()); // return 1
        System.out.println(iterator.next()); // return 2
        System.out.println(iterator.next()); // return 3
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next()); // return 4
        System.out.println(iterator.hasNext()); // return false

        /*
        Vector2D iterator = new Vector2D(matrix);

        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next()); // return 4
        System.out.println(iterator.hasNext()); // return false

        */
    }
}

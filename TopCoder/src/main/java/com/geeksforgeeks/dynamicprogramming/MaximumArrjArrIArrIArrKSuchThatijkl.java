package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 7/28/2016.
 */
//http://www.geeksforgeeks.org/maximize-arrj-arri-arrl-arrk-such-that-i-j-k-l/
public class MaximumArrjArrIArrIArrKSuchThatijkl {

    public void find( int[] arr ) {
        int[] table1 = new int[arr.length+1];
        int[] table2 = new int[arr.length+1];
        int[] table3 = new int[arr.length+1];
        int[] table4 = new int[arr.length+1];
        int length = arr.length;
        for ( int i = 0 ; i <= length ; i++){
            table1[i] = table2[i] = table3[i] = table4[i] = Integer.MIN_VALUE;
        }

        for ( int i = length-1 ; i >= 0 ; i-- ) {
            table1[i] = Math.max(table1[i+1], arr[i]);
        }

        for ( int i = length-1 ; i >= 0 ; i-- ) {
            int a = table2[i+1];
            int b = table1[i]-arr[i];
            table2[i] = Math.max(a,b);
        }

        for ( int i = length-1 ; i >= 0 ; i-- ) {
            int a = table3[i+1];
            int b = arr[i]+table2[i];
            table3[i] = Math.max(a,b);
        }

        for ( int i = length-1 ; i >= 0 ; i-- ) {
            int a = table4[i+1];
            int b = table3[i]-arr[i];
            table4[i] = Math.max(a,b);
        }
        int res = Integer.MIN_VALUE;
        for ( int i = 0 ; i < length ; i++) {
            res = Math.max(res, table4[i]);
        }
        System.out.println(res);
    }

    public static void main(String[] args) {
        int[] arr = {4, 8, 9, 2, 20};
        new MaximumArrjArrIArrIArrKSuchThatijkl().find(arr);
    }
}

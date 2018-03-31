package com.geeksforgeeks.array;

//http://www.geeksforgeeks.org/number-of-jumps-for-a-thief-to-cross-walls/
public class NumberOfJumpsForAThiefToCrossTheWall {

    public void find( int arr[], int x, int y) {
        int jumps = 0;
        for ( int i = 0 ; i < arr.length ; i++) {
            if (arr[i] <= x) {
                jumps++;
                continue;
            }

            int jumpsNeeded = arr[i]/x;
            int remain = arr[i] % x;
            if (remain > 0) {
                jumpsNeeded++;
            }
            jumps += jumpsNeeded;
        }
        System.out.println(jumps);
    }


    public static void main(String[] args) {
        int arr[] = {11, 34, 27, 9};
        int x = 10;
        int y = 1;
        new NumberOfJumpsForAThiefToCrossTheWall().find(arr, x, y);
    }
}

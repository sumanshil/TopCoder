package com.geeksforgeeks.recursion;


import java.util.Arrays;
import java.util.stream.Collectors;

//http://www.geeksforgeeks.org/print-all-sequences-of-given-length/
public class PrintAllSequencesOfGivenLength {
    static int arr[];
    static boolean used[];

    public void find (int n , int k, int start) {
        if (!stillRemaining(arr)){
            printArrayContent(arr);
            return;
        }
        if (!validSequence(arr)) {
            return;
        }

        for (int i = 0 ; i < arr.length ; i++) {
            if (arr[i] == -1) {
                for (int j = start; j <= n ; j++ ){
                    arr[i] = j;
                    find(n, k, start);
                    arr[i] = -1;
                }
            }
        }
    }

    private boolean validSequence(int[] arr) {
        int firstEmptyIndex = 0;
        for ( int i = 0 ; i < arr.length ; i++) {
            if (arr[i] == -1){
                firstEmptyIndex = i;
                break;
            }
        }
        int firstNonEmptyIndex = -1;
        for ( int i = 0 ; i < arr.length ; i++) {
            if (arr[i] != -1){
                firstNonEmptyIndex = i;
                break;
            }
        }

        return firstEmptyIndex > firstNonEmptyIndex;
    }

    private boolean stillRemaining(int[] arr) {
        return Arrays.stream(arr).anyMatch(e -> e == -1);
    }

    private void printArrayContent(int[] arr) {
        String str = Arrays.stream(arr).mapToObj(e -> e).map(e -> e+"").collect(Collectors.joining(","));
        System.out.println(str);
    }

    public static void main(String[] args) {
        int k = 2;
        int n = 3;
        arr = new int[k];
        Arrays.fill(arr, -1);
        used = new boolean[n+1];
        new PrintAllSequencesOfGivenLength().find(n, k, 1);
    }

}

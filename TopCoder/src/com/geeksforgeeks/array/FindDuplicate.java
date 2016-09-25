package com.geeksforgeeks.array;

/**
 * Created by sshil on 9/17/16.
 */
public class FindDuplicate {

    public static void main(String[] args) {
        int[] arr = new int[5];
        arr[0] = 4;
        arr[1] = 3;
        arr[2] = 2;
        arr[3] = 1;
        arr[4] = 2;
        find(arr);

    }

    private static void find(int[] arr) {
        int index = 0;
        while(true) {
            int elem = arr[index];
            if (arr[elem] == elem){
                System.out.println("duplicate found "+elem);
                break;
            } else {
                int temp = arr[index];
                arr[index] = arr[elem];
                arr[elem] = temp;
            }
        }
    }
}

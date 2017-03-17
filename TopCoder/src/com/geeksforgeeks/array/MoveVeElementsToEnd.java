package com.geeksforgeeks.array;

/**
 * Created by sshil on 12/23/16.
 */
//http://www.geeksforgeeks.org/move-ve-elements-end-order-extra-space-allowed/
public class MoveVeElementsToEnd {

    public void move(int[] arr){
        for ( int i = arr.length-1 ; i>=0 ; i--){
            if (arr[i] < 0){
                moveVe(arr, i);
            }
        }

        for ( int i = 0 ; i < arr.length ; i++){
            System.out.println(arr[i]);
        }
    }

    private void moveVe(int[] arr, int index) {
        while (index + 1 < arr.length && arr[index+1] > 0){
            int a = arr[index];
            int b = arr[index+1];
            arr[index] = b;
            arr[index+1] = a;
            index = index+1;
        }
    }

    public static void main(String[] args) {
        int[] arr = {-5, 7, -3, -4, 9, 10, -1, 11};
        new MoveVeElementsToEnd().move(arr);
    }
}

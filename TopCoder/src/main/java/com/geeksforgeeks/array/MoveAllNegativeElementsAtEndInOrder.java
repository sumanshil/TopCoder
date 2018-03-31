package com.geeksforgeeks.array;

import java.util.Arrays;

//http://www.geeksforgeeks.org/move-ve-elements-end-order-extra-space-allowed/
public class MoveAllNegativeElementsAtEndInOrder {

    public void find (int arr[]) {
        for (int i = arr.length-1 ; i >= 0 ; i--) {
            int j = i;
            while ( j >= 0 && arr[j] > 0){
                j--;
            }

            if (j >= 0 && arr[j] < 0) {
                int temp = arr[j];
                int k;
                for ( k = j+1 ; k < arr.length ; k++) {
                    if (arr[k] < 0){
                        break;
                    }
                    arr[k-1] = arr[k];
                }
                if (k != j+1){
                    arr[k-1] = temp;
                }
            }

        }
        Arrays.stream(arr).forEach(System.out::print);
    }


    public static void main(String[] args) {
        int arr[] = {-5, 7, -3, -4, 9, 10, -1, 11};
        new MoveAllNegativeElementsAtEndInOrder().find(arr);
    }

}

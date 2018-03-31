package com.geeksforgeeks.array;
//http://www.geeksforgeeks.org/move-zeroes-end-array/
public class MoveAllZerosAtEnd {

    public int[] arrangeArray(int[] arr){
        int count = 0;
        for(int i = 0 ; i < arr.length ; i++){
            if (arr[i] != 0){
                arr[count++] = arr[i];
            }
        }
        
        for(int i = count  ; i < arr.length; i++){
            arr[i] = 0;
        }
        return arr;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0};
        int[] result = new MoveAllZerosAtEnd().arrangeArray(arr);
        for(int i : result){
            System.out.println(i);
        }
    }

}

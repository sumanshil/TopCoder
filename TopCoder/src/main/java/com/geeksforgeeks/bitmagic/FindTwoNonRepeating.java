package com.geeksforgeeks.bitmagic;

public class FindTwoNonRepeating {

    public void find(int[] arr){
        int xor = arr[0];
        for(int i = 1 ; i < arr.length ; i++){
            xor = xor ^ arr[i];
        }
        
        int right_bit_set = xor & ~(xor -1);
        int x = 0;
        int y = 0;
        for(int i = 0 ; i < arr.length ; i++){
            if ((right_bit_set & arr[i]) > 0){
                x = x ^ arr[i];
            } else {
                y = y ^ arr[i];
            }
        }
        System.out.println("1 "+x);
        System.out.println("2 "+y);
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {2, 3, 7, 9, 11, 2, 3, 11};
        new FindTwoNonRepeating().find(arr);
        
    }

}

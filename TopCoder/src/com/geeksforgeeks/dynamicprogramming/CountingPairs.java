package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/counting-pairs-person-can-form-pair-one/
public class CountingPairs {

    public void find (int number){
        int result = recursive(number, 0, number-1);
        System.out.println(result);
    }

    private int recursive(int arr, int low, int high) {
        if (low > high){
            return 0;
        }
        if (low == high){
            return 1;
        }

        if (low == high-1){
            return 1;
        }

        int result = 0;
        for (int i = low ; i <= high ; i++ ) {
            result = recursive(arr, low, i) + recursive(arr, i+1, high);
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 3;
        new CountingPairs().find(n);
    }

}

package com.geeksforgeeks.array;

/**
 * Created by sshil on 7/2/17.
 */
//http://www.geeksforgeeks.org/sum-product-elements-sub-arrays-size-k/
public class SumOfProductOfAllElementsOfSubArraysOfSizeK {

    public void find (int[] arr, int size){
        int sum = 0;
        int product = 1;
        int i;
        for (i = 0 ; i < size ; i++){
            product *= arr[i];
        }
        sum += product;
        int startIndex = 0;
        for (int j = i ; j < arr.length ; j++){
            product = product / arr[startIndex];
            product *= arr[j];
            startIndex = startIndex + 1;
            sum += product;
        }
        System.out.println(sum);
    }


    public static void main(String[] args) {
//        int[] arr = {1, -2, 3, -4, 5, 6} ;
//        int size = 2;
//        new SumOfProductOfAllElementsOfSubArraysOfSizeK().find(arr, size);
        System.out.println(9*8*7*6);
    }
}

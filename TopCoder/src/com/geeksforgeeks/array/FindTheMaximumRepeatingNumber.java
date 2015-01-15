package com.geeksforgeeks.array;
//http://www.geeksforgeeks.org/find-the-maximum-repeating-number-in-ok-time/
//Given an array of size n, the array contains numbers in range from 0 to k-1 where k is a positive integer and
//k <= n. Find the maximum repeating number in this array. For example, let k be 10 the given array be
//arr[] = {1, 2, 2, 2, 0, 2, 0, 2, 3, 8, 0, 9, 2, 3}, the maximum repeating number would be 2.
//Expected time complexity is O(n) and extra space allowed is O(1). Modifications to array are allowed.


public class FindTheMaximumRepeatingNumber {

    public void find(int[] arr, int k){
        for(int i = 0 ; i < arr.length ; i++){
            int element = arr[i];
            if (element>= k){
                while(element>=k){
                    element = element-k;
                }
            }
            arr[element] = arr[element]+k;
        }
        
        int maxCount = Integer.MIN_VALUE;
        int element = 0;
        
        for(int i = 0 ; i < arr.length;i++){
            int c = 0;
            int number = arr[i];
            while(number > k){
                number = number-k;
                c++;
            }
            
            if (c> maxCount){
                maxCount = c;
                element = number;
            }
        }
        System.out.println("Result is "+element);
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 2, 0, 2, 0, 2, 3, 8, 0, 9, 2, 3};
        new FindTheMaximumRepeatingNumber().find(arr, 10);
        

    }

}

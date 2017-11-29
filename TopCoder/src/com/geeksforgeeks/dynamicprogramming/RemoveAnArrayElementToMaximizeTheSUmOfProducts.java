package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/remove-array-end-element-maximize-sum-product/
public class RemoveAnArrayElementToMaximizeTheSUmOfProducts {

    public void find (int arr[]) {
       int result = recursive(arr, 0, arr.length-1, 1);
        System.out.println(result);
    }

    private int recursive(int[] arr, int low, int high, int numberOfElementsRemoved) {
        if (low > high){
            return 0;
        }
        if (low == high) {
            return arr[low] * numberOfElementsRemoved;
        }

        // remove the element at low

        int result1 = recursive(arr, low + 1, high, numberOfElementsRemoved+1) +
                      (arr[low] * (numberOfElementsRemoved));

        // remove the element at high
        int result2 = recursive(arr, low, high -1 , numberOfElementsRemoved+1) +
                      (arr[high] * (numberOfElementsRemoved));
        return Math.max(result1, result2);
    }


    public static void main(String[] args) {
        int arr1[] = {1, 3, 1, 5, 2};
        new RemoveAnArrayElementToMaximizeTheSUmOfProducts().find(arr1);
    }

}

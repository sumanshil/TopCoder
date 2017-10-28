package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/longest-common-subsequence-with-at-most-k-changes-allowed/
public class LongestCommonSubSequenceWithAtmostK {

    public  void find(int[] arr1, int[] arr2, int k) {
        int result = recursive(arr1, arr2, arr1.length, arr2.length, k);
        System.out.println(result);
    }

    private  int recursive(int[] arr1,
                                 int[] arr2,
                                 int arr1Index,
                                 int arr2Index,
                                 int k) {
        if (arr1Index == 0 || arr2Index == 0){
            return 0;
        }
        if (k == 0){
            return 0;
        }

        if (arr1[arr1Index-1] == arr2[arr2Index-1]) {
            return recursive(arr1, arr2, arr1Index-1, arr2Index-1, k)+1;
        } else {
            // change the current number in first sequence
            int result1 =  recursive(arr1, arr2, arr1Index-1, arr2Index-1, k-1)+1;
            int result2 = Math.max(recursive(arr1, arr2, arr1Index-1, arr2Index,k),
                                   recursive(arr1, arr2, arr1Index, arr2Index-1, k));
            return Math.max(result1, result2);
        }

    }

    public static void main(String[] args) {
        int[] a = {8, 3};
        int[] b = {1, 3};
        new LongestCommonSubSequenceWithAtmostK().find(a, b, 1);
    }

}

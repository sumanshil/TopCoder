package com.geeksforgeeks.array;

//http://www.geeksforgeeks.org/longest-subsegment-1s-formed-changing-k-0s/
public class LongestSubSegmentOfOnesByChangingAtmostKZeros {

    public void find (int arr[], int k) {
        int l = 0;
        int r = 0;
        int max = Integer.MIN_VALUE;
        int countOfZeros = 0;
        while (r < arr.length){
            if (arr[r] == 0) {
                countOfZeros++;
            }
            while (countOfZeros > k){
                if (arr[l] == 0){
                    countOfZeros--;
                }
                l++;
            }
            max = Math.max((r-l)+1, max);
            r++;
        }
        System.out.println(max);
    }

    public static void main(String[] args) {
        int arr[] = {1, 0, 0, 1, 0, 1, 0, 1, 0, 1};
        int k = 2;
        new LongestSubSegmentOfOnesByChangingAtmostKZeros().find(arr, k);
    }
}

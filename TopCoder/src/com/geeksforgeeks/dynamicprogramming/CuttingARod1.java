package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/
public class CuttingARod1 {

    public void find (int arr[], int lengthOfRod) {
        int result = recursive(arr, arr.length, lengthOfRod);
        System.out.println(result);
    }

    private int recursive(int[] arr, int arrayIndex, int lengthOfRod) {
        if (arrayIndex == 0) {
            return 0;
        }

        if (arrayIndex > lengthOfRod) {
            return recursive(arr, arrayIndex-1, lengthOfRod);
        } else {
            return Math.max(recursive(arr, arrayIndex, lengthOfRod - arrayIndex)
                            + arr[arrayIndex-1]
                            , recursive(arr, arrayIndex-1, lengthOfRod)
            );
        }
    }

    public void dp(int arr[], int lengthOfRod) {
        int rowAsLengthOfRod = lengthOfRod+1;
        int colAsCutSize = arr.length+1;
        int dp[][] = new int[rowAsLengthOfRod][colAsCutSize];

        for (int currentLengthOfRod = 1 ; currentLengthOfRod < rowAsLengthOfRod ; currentLengthOfRod++) {
            for (int currentCutSize = 1; currentCutSize < colAsCutSize ; currentCutSize++) {
                if (currentCutSize > currentLengthOfRod ){
                    dp[currentLengthOfRod][currentCutSize] = dp[currentLengthOfRod][currentCutSize-1];
                } else {
                    dp[currentLengthOfRod][currentCutSize] = Math.max(
                            dp[currentLengthOfRod - currentCutSize][currentCutSize]+ arr[currentCutSize-1],
                            dp[currentLengthOfRod][currentCutSize-1]
                    );
                }
            }
        }
        System.out.println(dp[rowAsLengthOfRod-1][colAsCutSize-1]);
    }


    public static void main(String[] args) {
        int arr[] ={3, 5};
        int length = 2;
        new CuttingARod1().dp(arr, length);
    }

}

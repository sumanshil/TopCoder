package com.geeksforgeeks.rangeminimumquery;

//http://www.geeksforgeeks.org/range-minimum-query-for-static-array/
public class SquareRootDecomposition {

    int block[] = null;

    public void find (int arr[], int minIndex, int maxIndex ) {
        int length = arr.length;
        int blockArrSize = (int)Math.sqrt(length);
        block = new int[blockArrSize];

        for (int i = 0 ; i < block.length ; i++) {
            block[i] = Integer.MAX_VALUE;
        }

        int blockIndex = -1;
        for ( int i = 0 ; i < arr.length ; i++) {

            if ( i % blockArrSize == 0){
                blockIndex++;
            }
            block[blockIndex] = Math.min(block[blockIndex], arr[i]);
        }

        int l = minIndex;
        int r = maxIndex;
        int result = Integer.MAX_VALUE;
        while (l < r && (l % blockArrSize != 0 || l == 0) ){
            result = Math.min(result, arr[l++]);
        }

        while ( l + blockArrSize < r){
            result = Math.min(result, block[l%blockArrSize+1]);
            l += blockArrSize;
        }

        while (l <= r){
            result = Math.min(result, arr[l++]);
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        int arr[] = {7, 2, 3, 0, 5, 10, 3, 12, 18};
        new SquareRootDecomposition().find(arr, 4, 8);
    }
}

// worst case complexity O(sqrt(n))
package com.geeksforgeeks.rangeminimumquery;

//http://www.geeksforgeeks.org/sqrt-square-root-decomposition-technique-set-1-introduction/
public class RangeSumDecomposition {

    public int[] preprocess ( int arr[]){
        int blockSize = (int)Math.sqrt(arr.length);
        int blockIndex = -1;
        int index = 0;
        int newArr[] = new int[blockSize];
        while (index < arr.length) {
            if (blockIndex < newArr.length-1 && index % blockSize == 0){
                blockIndex++;
            }
            newArr[blockIndex] += arr[index++];
        }
        return newArr;
    }

    public void findSum(int arr[], int index1, int index2) {
        int[] newArr = preprocess(arr);
        int l = index1;
        int r = index2;
        int blockSize = (int)Math.sqrt(arr.length);
        int result= 0;
        while (l < r && (l % blockSize != 0 || l != 0 )){
            result += arr[l++];
        }
        while( l + blockSize <= r){
            result += newArr[l/blockSize];
            l += blockSize;
        }

        while (l <= r){
            result += arr[l++];
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        int input[] = {1, 5, 2, 4, 6, 1, 3, 5, 7, 10};
        new RangeSumDecomposition().findSum(input, 7, 9);
    }
}

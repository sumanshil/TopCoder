package com.geeksforgeeks.recursion;

//http://www.geeksforgeeks.org/make-combinations-size-k/
public class MAkeAllCombinationsOfSizeK {

    public void find(int n, int k) {
        boolean nArray[] = new boolean[n];
        boolean kArray[] = new boolean[k];
        int[] result = new int[k];
        recursiveUtil(nArray, kArray, result, n, 0, k);
    }

    private void recursiveUtil(boolean[] nArray,
                               boolean[] kArray,
                               int[] result,
                               int n,
                               int index,
                               int k) {
        if (allFull(kArray)){
            for ( int i = 0 ; i < result.length ; i++) {
                System.out.print(result[i]+" ");
            }
            System.out.println();
            return;
        }

        for ( int i = index ; i < n ; i++ ) {
            if (!nArray[i]) {
                nArray[i] = true;
                for ( int j = 0 ; j < kArray.length ; j++) {
                    if (!kArray[j]) {
                        kArray[j] = true;
                        result[j] = i+1;
                        recursiveUtil(nArray, kArray,result,n, i+1,k);
                        kArray[j] = false;
                    }
                }
                nArray[i] = false;
            }
        }
    }

    private boolean allFull(boolean[] kArray) {
        for ( int i = 0 ; i < kArray.length ; i++) {
            if (!kArray[i]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new MAkeAllCombinationsOfSizeK().find(5, 3);
    }
}

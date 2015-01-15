package com.geeksforgeeks.array;

public class MergeAnArrayOfSizeMN {

    public void merge(int[] arr1, int[] arr2, int m, int n){
        int j = arr1.length-1;
        int i = j ;
        while(j>=0){
            while(j>=0 && arr1[j]>0){
                j--;
            }
            
            if (j >0){
                i = j-1;
                while(i>=0 &&arr1[i]<0){
                    i--;
                }
            }
            
            if (i>=0 && j >=0  ){
                int temp = arr1[i];
                arr1[i] = arr1[j];
                arr1[j] = temp;
            }
            j--;
        }
        
        int index1 = i+1;
        int index2 = 0;
        
        int index3 = 0;
        
        while(index1 < arr1.length && index2 < arr2.length){
            arr1[index3++] = (arr1[index1] < arr2[index2]) ?arr1[index1++] : arr2[index2++];   
        }
        
        while (index2 < arr2.length){
            arr1[index3++] = arr2[index2++];
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
//        int[] arr1 = {2,-1,7,-1,-1,10,-1};
//        int[] arr2 = {5,8,12,14};
        int[] arr1 = {2, 8, -1, -1, -1, 13, -1, 15, 20};
        int[] arr2 = {5, 7, 9, 25};
        int n = 4;
        int m = 3;
        new MergeAnArrayOfSizeMN().merge(arr1, arr2, 5, 4);
        
        
    }

}

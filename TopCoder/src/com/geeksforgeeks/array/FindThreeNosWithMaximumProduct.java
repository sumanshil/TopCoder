package com.geeksforgeeks.array;

public class FindThreeNosWithMaximumProduct {

    public void find(int[] arr){
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];
        left[0] = 1;
        for(int i = 1 ; i < arr.length ; i++){
            int max= arr[0];
            for(int j = 0 ; j < i ; j++){
                if (arr[j] > max){
                    max = arr[j];
                }
            }
            left[i] = max;
        }
        
        right[arr.length - 1] = 1;
        
        for(int i = arr.length -2 ; i >= 0 ; i--){
            int max = arr[arr.length - 1];
            for(int j = arr.length-1 ; j>i;j--){
                if (arr[j]> max){
                    max = arr[j];
                }
            }
            right[i] = max;
        }
        int maxProduct = left[1]*right[1]*arr[1];
        int a = left[1];
        int b = right[1];
        int c = arr[1];
        for(int i = 2 ; i < arr.length ; i++){
            if (arr[i]*left[i]*right[i] > maxProduct){
                maxProduct=arr[i]*left[i]*right[i];
                a = left[i];
                b = right[i];
                c = arr[i];
            }
        }
        System.out.println(a +" "+ b +" "+c);
    }
    
    public void findIncreasingSubSeqWithMaxProduct(int[] arr){
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];
        
        left[0] = 1;
        
        for(int i = 1; i < arr.length ; i++){
            int max = arr[0];
            for(int j = 0 ; j < i ; j++){
                if ( arr[j] > max && arr[j]< arr[i]){
                    max = arr[j];
                }
            }
            left[i]= max;
        }

        right[arr.length-1] = 1;
        for(int i = arr.length-2; i >=0 ; i--){
            int max = arr[arr.length-1];
            for(int j = arr.length-1 ; j > i ; j--){
                if ( arr[j] >= max && arr[j]> arr[i]){
                    max = arr[j];
                    right[i]= max;
                }
            }
            
        }        
        
        int a = left[0];
        int b = right[0];
        int c = arr[0];
        int maxProduct = a*b*c;
        for(int i = 1 ; i <arr.length ; i++){
            if (left[i]*right[i]*arr[i] > maxProduct){
                maxProduct = left[i]*right[i]*arr[i];
                a = left[i];
                b = right[i];
                c = arr[i];
            }
        }
        System.out.println(a+" "+b+" "+c);
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {1, 5, 10, 8, 9};
        //int[] arr = {6, 7, 8, 1, 2, 3, 9, 10} ;
        new FindThreeNosWithMaximumProduct().findIncreasingSubSeqWithMaxProduct(arr);
    }

}

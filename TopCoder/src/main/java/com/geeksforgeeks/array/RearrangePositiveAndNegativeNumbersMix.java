package com.geeksforgeeks.array;

public class RearrangePositiveAndNegativeNumbersMix {

    
    public void arrange(int[] arr){
        int i= 0 ;
        int j = arr.length -1;
        while(i<j){
            if (arr[i] > 0 && arr[j] <0){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            } else if (arr[i] <0){
                i++;
            } else if (arr[j] >0){
                j--;
            }
        }
            
        i = 0;
        j = arr.length-1;
        while(arr[j]>0){
            j--;
        }
        j++;
        i++;
        while (i<j){            
            if (arr[i]<0 && arr[j]>0){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j++;
                i= i+2;
            }
            
            if ((arr[i]>0 && arr[j]>0) || (arr[i]<0 && arr[j]<0)){
                break;
            }
        }
        for(int k = 0 ; k < arr.length; k++){
            System.out.print(arr[k]+" ");
        }
        System.out.println();
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {-1, 2, -3, 4, 5, 6, -7, 8, 9};
        new RearrangePositiveAndNegativeNumbersMix().arrange(arr);
    }

}

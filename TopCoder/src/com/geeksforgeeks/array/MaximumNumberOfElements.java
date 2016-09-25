package com.geeksforgeeks.array;

// an array may contain elements that range from m to (m+n-1). We need to inOrder out the element that occurs most

public class MaximumNumberOfElements {
    public void find(int[] arr, int m , int n){
        for(int i = 0 ; i < arr.length ; i++){
            int element = arr[i];
            int count = 0;
            while(element > (m+n)){
                element = element - (m+n);
                count++;
            }
            
            if (element >= m){
                element = element - m;
                arr[element] = arr[element]+(m+n);
            }
        }
        
        int maxCount = Integer.MIN_VALUE;
        int element = 0;
        
        for(int i =0 ; i < arr.length ; i++){
            int number = arr[i];
            int count = 0;
            while (number > (m+n)){
                number = number - (m+n);
                count++;
            }
            if (count > maxCount ){
                maxCount = count;
                element = i+m;
            }
        }
        System.out.println("Element "+element);
    }
    
    public static void main(String[] args){
        int[] arr = {2,3,4,4};
        int m = 2;
        int n = 4;
        new MaximumNumberOfElements().find(arr, m, n);
    }
}

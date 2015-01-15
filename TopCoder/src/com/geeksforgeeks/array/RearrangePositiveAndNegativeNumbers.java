package com.geeksforgeeks.array;

import java.net.NetworkInterface;

//http://www.geeksforgeeks.org/rearrange-positive-and-negative-numbers-publish/
public class RearrangePositiveAndNegativeNumbers {
    private static int[] arr = {-1, 2, -3, 4, 5, 6, -7, 8, 9};
    
    private static void rearrange1(){
    	int i = -1;
    	for(int j = 0 ; j < arr.length ; j++){
    		if (arr[j]<0){
    			i++;
    			if (i != j){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
    			}
    		}
    	}
    	
    	for(int k = 0 ; k < arr.length ; k++){
    		System.out.print(arr[k]);
    	}
    	System.out.println();
    	System.out.println("i "+i);
    	int pos = i+1;
    	int neg = 1;
    	
    	while(pos < arr.length  && neg < pos && arr[neg] <0){
    		swap(pos, neg);
    		pos++;
    		neg = neg+2;
    	}
    	
    	for(int k = 0 ; k < arr.length ; k++){
    		System.out.print(arr[k]);
    	}
    	
    }
    
    private static void swap(int i , int j){
    	int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;    	
    }
    private static void rearrange(){
    	int i = 0 ; 
    	int j = arr.length -1;
    	
    	while(i < j){
    		
    		while(arr[i] <0){
    			i++;
    		}
    		
    		while(arr[j]>0){
    			j--;
    		}
    		if (i < j){
    			int temp = arr[i];
    			arr[i] = arr[j];
    			arr[j] = temp;
    		}
    		i++;
    		j--;
    	}
    	
    	for(int k = 0 ; k < arr.length ; k++){
    	     System.out.print(arr[k] +", ");	
    	}
    	System.out.println();
    	System.out.println("i = "+i);
    	
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        rearrange1();
	}

}

package com.geeksforgeeks.array;

import java.util.HashMap;
import java.util.Map;

public class LargetsArrayWithEqualZerosAndOnes {

    public static int getMaxLength(int[] arr){
        int[] arrTemp = arr;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0 ; i < arrTemp.length ; i++){
            if (arrTemp[i] == 0){
                arrTemp[i] = -1;
            }
        }
        int maxLength = Integer.MIN_VALUE;
        int sum = arrTemp[0];
        for(int i = 1 ; i  < arrTemp.length ; i++){
            sum += arrTemp[i];
            if (sum == 0 ){
                if (i > maxLength){
                    maxLength = i+1;
                }
            } else if (!map.containsKey(sum)){
                map.put(sum, i+1);
            } else {
                int index =  map.get(sum);
                if (((i+1) - index) > maxLength){
                    maxLength = ((i+1)-index);
                }
            }
        }
        return maxLength;
    }
    
    public static int getLength(int[] arr){
        int[] numberOfOnes = new int[arr.length];
        int[] numberOfZeros = new int[arr.length];
        
        if (arr[0] == 0){
            numberOfZeros[0] = 1;
            numberOfOnes[0] = 0;
        } else {
            numberOfZeros[0] = 0;
            numberOfOnes[0] = 1;            
        }
        for(int i = 1 ; i < arr.length ; i++){
            if (arr[i] == 0){
                numberOfZeros[i] = numberOfZeros[i-1]+1;
                numberOfOnes[i] = numberOfOnes[i-1];                
            } else {
                numberOfZeros[i] = numberOfZeros[i-1];
                numberOfOnes[i] = numberOfOnes[i-1]+1;
            }
        }
        
        int maxLength = Integer.MIN_VALUE;
        for(int i = 0 ; i < arr.length ; i++){
            if (numberOfOnes[i] > 0 && numberOfZeros[i] > 0){
                if (numberOfOnes[i] > numberOfZeros[i]){
                    int temp = 2* numberOfZeros[i];
                    if (temp > maxLength){
                        maxLength = temp;
                    } 
                } else if (numberOfZeros[i] > numberOfOnes[i]){
                    int temp = 2* numberOfOnes[i];
                    if (temp > maxLength){
                        maxLength = temp;
                    }                     
                }
            }
        }
        return maxLength;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
       //int[] arr ={1,1,1,1,1,0,0,0,0,0,0,0,1};
        //{0 0 1 0 1 1 0 1}
        int[] arr = {0,0,1,0,1,0,1,0,1}; 
       int result = getMaxLength(arr);
       System.out.println(result);

    }

}

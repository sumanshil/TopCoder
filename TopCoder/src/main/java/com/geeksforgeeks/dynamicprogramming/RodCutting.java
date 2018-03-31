package com.geeksforgeeks.dynamicprogramming;

public class RodCutting {

    public int getMax(int[] rodSizes, int[] price){
        int result = getMaxRecursive(rodSizes,price,rodSizes.length-1);
        return result;
    }
    private int getMaxRecursive(int[] rodSizes, int[] prices, int index) {
        if(index <0){
            return 0;
        }
        int retVal = prices[index];
        for(int i = 0 ; i< index ; i++){
            int r = getMaxRecursive(rodSizes, prices,(index-i-1));
            int s= prices[i];
            if (r+s > retVal){
                retVal = r+s;
            }            
        }
        return retVal;
    }
    
    public int getMaxDynamic(int[] prices){
        int[] table = new int[prices.length];
        for(int i = 0 ; i < prices.length ; i++){
            table[i] = prices[i];
        }
        int n  = prices.length-1;
        for(int i = 1 ; i < prices.length; i++){            
            for(int j = 0 ; j < i ; j++){
                table[i] = Math.max(table[i], prices[j]+table[i-j-1]);
            }
        }
        return table[n];        
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        //int[] length  = {1,2};
        int[] price   = {3,5,8,9,10,17,17,20};
        int result = new RodCutting().getMaxDynamic(price);//(length, price);
        System.out.println(result);
    }

}

package com.geeksforgeeks.dynamicprogramming;
//http://www.geeksforgeeks.org/dynamic-programming-set-36-cut-a-rope-to-maximize-product/
public class MaximumProductCutting {

    public int getMax(int product){
        
        if (product == 1)
            return 1;
              
        int retVal = Integer.MIN_VALUE;
        for(int i = 1; i < product; i++){
            int ret = Math.max((i*(product-i)),i * getMax(product-i));
            if (ret > retVal){
                retVal = ret;
            }
        }
        return retVal;
        
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        int product = 10;
        int result = new MaximumProductCutting().getMax(product);
        System.out.println(result);
    }

}

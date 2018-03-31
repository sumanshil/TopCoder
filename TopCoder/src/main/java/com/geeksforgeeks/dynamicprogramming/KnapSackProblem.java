package com.geeksforgeeks.dynamicprogramming;

public class KnapSackProblem {

    private int max = 0;
    public int getMaxValue(int[] val, int[] wt, int w, int index){
        if (index >= wt.length)
            return 0;
        if (wt[index] < w){
            for(int i = index+1; i < wt.length ; i++){
                int best = val[index]+getMaxValue(val, wt, w-wt[index], i);
                if (best > max){
                    max = best;
                }
            }
        }
        return max;
    }
    
    public int getMaxValueRecursive(int[] val, int[] wt, int weight, int n){
        if (n == 0 || weight == 0){
            return 0;
        }
        
        if (wt[n-1] > weight){
            return getMaxValueRecursive(val, wt, weight, n-1);
        } else {
            int maxWith = val[n-1]+getMaxValueRecursive(val, wt, weight-wt[n-1], n-1);
            int maxWithOut = getMaxValueRecursive(val, wt, weight-wt[n-1], n-1);
            return Math.max(maxWith, maxWithOut);
        }
    }
    
    
    public int getMaxValueDynamic(int[] val, int[] wt, int weight){
        int[][] table = new int[val.length+1][weight+1];
        
        for(int i = 0 ; i <= val.length ; i++){
            for(int w = 0 ; w <= weight ; w++){
                if (i == 0 || w == 0){
                    table[i][w] = 0;
                } else if( wt[i-1] <=w) {
                    table[i][w] = Math.max(val[i-1]+table[i-1][w-wt[i-1]], table[i-1][w]);
                } else {
                    table[i][w] = table[i-1][w];
                }
            }
        }
        return table[val.length][weight];
        
        
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        int[] val= {60, 100, 120};
        int[] wt = {10, 20, 30};
        int W = 50;

        int result = new KnapSackProblem().getMaxValueDynamic(val, wt, W);
        System.out.println(result);
        
    }

}

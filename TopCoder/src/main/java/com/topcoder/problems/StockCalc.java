package com.topcoder.problems;

public class StockCalc {
    
	public static int getProfit(int[] param0){
		int profitSoFar = Integer.MIN_VALUE;
		int currentProfit = 0;
		int i = 0;
		while(i < param0.length){
			if ((i+1) < param0.length){
				if (param0[i]< param0[i+1]){
					currentProfit += param0[i+1]-param0[i];
				} else if (param0[i] > param0[i+1]){
					if (currentProfit > profitSoFar){
						profitSoFar = currentProfit;
					}
					currentProfit = 0;
				}
			}
			i++;
		}
		if (currentProfit > profitSoFar){
			profitSoFar = currentProfit;
		}
		return (profitSoFar == Integer.MIN_VALUE) ? 0 : profitSoFar;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//int[] prices = {3,5,10,20,80,70,40,1};
		//int[] prices = {59, 40, 70, 80, 90, 200};
		//int[] prices = {1, 3, 8, 10, 20, 14, 12, 1, 17};
		int[] prices = 	{2, 3, 8, 1, 99, 8};
        int result = getProfit(prices);
        System.out.println(result);
	}

}

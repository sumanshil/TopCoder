package com.topcoder.problems.round22;

public class ThreeCentCoin {
    // Penny = 1 cent
	// Nickel = 5 cent
	// Dime = 10 cent
	// Quarter = 25 cents
	private int[] arr = {1,3,5,10,25};
	public int getChange(int amount){
		int[] min = new int[amount+1];
		min[0] = 0;
		for(int i = 1 ; i <= amount ; i++){
			min[i] = Integer.MAX_VALUE;
		}
		
		for(int i = 1; i <= amount ; i++){
			for(int j = 0 ; j < arr.length ; j++){
				if (arr[j] <=i){
					min[i] = Math.min(min[i], min[i-arr[j]]+1);
				}
			}
		}
		return min[amount];
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int amount = 898;
		int result = new ThreeCentCoin().getChange(amount);
        System.out.println(result);
	}

}

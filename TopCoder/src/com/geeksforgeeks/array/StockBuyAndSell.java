package com.geeksforgeeks.array;

public class StockBuyAndSell {
    private static int[] price = {100, 180, 260, 310, 40, 535, 695};
	/**
	 * @param args
	 */
    static class Interval{
    	int buy;
    	int sale;
    }
	public static void main(String[] args) {
		int n = price.length ;
		
		Interval[] solution = new Interval[n/2+1];

		int i = 0;
		int count = 0;
		while(i < n){
			while(i < n-1 && price[i] > price[i+1]){
				i++;
			}
			
			if (i == n-1)
				break;
			if (solution[count] == null){
				solution[count] = new Interval();
			}
			solution[count].buy = i++;
			while(i< n && price[i]>price[i-1]){
				i++;
			}
			solution[count].sale = i-1;
			count++;
			
		}
		
		for(int j = 0 ; j < count ; j++){
			System.out.println("Buy on day "+solution[j].buy+" Sell on day "+solution[j].sale);
		}
	}

}

package com.geeksforgeeks.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sshil on 2/4/2016.
 */
//http://www.geeksforgeeks.org/dynamic-programming-set-14-variations-of-lis/
public class BuildingBridges1 {

	static class BridgeAxis {
		private int low;
		private int high;

		public BridgeAxis( int x, int y) {
			if (x < y){
				low = x;
				high = y;
			} else {
				low = y;
				high = x;
			}
		}

		public int getLow() {
			return low;
		}

		public void setLow(int low) {
			this.low = low;
		}

		public int getHigh() {
			return high;
		}

		public void setHigh(int high) {
			this.high = high;
		}

		public String toString(){
			return this.low + " " + this.high;
		}
	}

    public void find(int[] north, int[] south ){
		List<BridgeAxis> list = new ArrayList<>();
		for ( int i = 0 ; i < north.length ; i++ ) {
			int northValue = north[i];
			int southValue = 0;
			for ( int  j = 0 ; j < south.length ; j++) {
				if (south[j] == northValue){
					southValue = j;
					break;
				}
			}
			list.add(new BridgeAxis(i, southValue));
		}

		for (BridgeAxis bridgeAxis : list){
			System.out.println(bridgeAxis);
		}

		int[] count = new int[list.size()];
		for ( int i = 0 ; i < count.length ; i++) {
			count[i] = 1;
		}

		for ( int i = 1 ; i < count.length ; i++) {
			for ( int j = 0 ; j < i ; j++) {
				if (list.get(j).high <= list.get(i).low && count[j]+ 1 > count[i]) {
					count[i] = count[j]+1;
				}
			}
		}

		int result = Integer.MIN_VALUE;
		for ( int i = 0 ; i < count.length ; i++){
			if (result < count[i]){
				result = count[i];
			}
		}
		System.out.println(result);
	}


	public static void main(String[] args) {
		int[] north = {8,1,4,3,5,2,6,7};
		int[] south = {1,2,3,4,5,6,7,8};
		new BuildingBridges1().find(north, south);
	}
}

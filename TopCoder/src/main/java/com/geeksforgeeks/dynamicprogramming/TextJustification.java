package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 3/19/2016.
 */
public class TextJustification {

	public String justify(String[] words, int width) {
		int[][] cost = new int[words.length][words.length];
		for ( int i = 0 ; i < words.length ; i++) {
			cost[i][i] = width - words[i].length();
			for ( int j = i+1 ; j < words.length ; j++) {
				cost[i][j] = cost[i][j-1]-words[j].length()-1;
			}
		}

		for ( int i = 0 ; i < words.length ; i++) {
			for ( int j = i; j < words.length ; j++) {
				if (cost[i][j] <  0){
					cost[i][j] = Integer.MAX_VALUE;
				} else {
					cost[i][j] = (int)Math.pow(cost[i][j],2);
				}
			}
		}

		int[] minCost = new int[words.length];
		int[] result = new int[words.length];

		for (int i = words.length-1 ; i >=0 ; i--) {
			minCost[i] = cost[i][words.length-1];
			result[i] = words.length;
			for ( int j = words.length-1 ; j > i ; j--) {
				if(cost[i][j-1] == Integer.MAX_VALUE){
					continue;
				}
				if (minCost[i] > minCost[j]+cost[i][j-1]) {
					minCost[i] = minCost[j]+cost[i][j-1];
					result[i] = j;
				}
			}
		}

		int start = 0;
		StringBuffer stringBuffer = new StringBuffer();
		while (start < words.length){
			int endExclusive = result[start];
			for ( int i = start ; i < endExclusive-1 ; i++) {
				stringBuffer.append(words[i]+" ");
			}
			stringBuffer.append(words[endExclusive-1]+"\n");
			start = endExclusive;
		}
		return stringBuffer.toString();
	}

	public static void main(String[] args) {
		String words1[] = {"Tushar","likes","to","write","code","at", "free", "time"};
		TextJustification awl = new TextJustification();
		System.out.println(awl.justify(words1, 12));
	}
}

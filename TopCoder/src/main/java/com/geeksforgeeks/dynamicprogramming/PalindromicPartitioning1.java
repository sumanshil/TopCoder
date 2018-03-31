package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 1/31/2016.
 */
//http://www.geeksforgeeks.org/dynamic-programming-set-17-palindrome-partitioning/
public class PalindromicPartitioning1 {

	public void count(String str) {
		int result = countRecursive(str);
		System.out.println(result);
	}

	private int countDynamic(String str){
		// min number of cuts needed to make a string [i,j] palindromic
		int[][] countMinCut = new int[str.length()][str.length()];
		// is a string[i..j] is palindrome
		boolean[][] isPalinDrome = new boolean[str.length()][str.length()];
		int rowLength = str.length();
		int colLength = str.length();
		for ( int i = 0 ; i < rowLength ; i++){
			isPalinDrome[i][i] = true;
			countMinCut[i][i] = 0;
		}

		for ( int L = 2 ; L < str.length() ; L++) {
			int j = str.length()-L+1;
			for (int i = 0 ; i < j ; i++) {
				int k = i+L-1;

			}
		}
		//TODO complete it
		return 0;

	}
	private int countRecursive(String str) {
		if ( str.length() == 1) {
			return 1;
		}

		if (isPalindrome(str)){
			return 1;
		}

		int minCount = Integer.MAX_VALUE;
		for ( int i = 1 ; i < str.length(); i++){
			int count1 = countRecursive(str.substring(0,i));
			int count2 = countRecursive(str.substring(i, str.length()));
			if (count1 + count2 < minCount){
				minCount = count1 + count2;
			}
		}
		return minCount;
	}

	private boolean isPalindrome(String str) {
		int start = 0;
		int end = str.length()-1;
		while (start < end){
			if (str.charAt(start++) != str.charAt(end--)){
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		new PalindromicPartitioning1().count("abac");
	}
}

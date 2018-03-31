package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 1/17/2016.
 */
public class LengthOfLongestPalindromicSubStringWithoutRepeatingChars {
	public int calculate(String str) {
		int[] lastIndexPoint = new int[26];
		for ( int i = 0 ; i < 26 ; i++){
			lastIndexPoint[i] = -1;
		}

		int maxSubStringLength = Integer.MIN_VALUE;
		int startIndex = 0;
		int currentLength = 0;
		for ( int i = 0 ; i < str.length() ; i++) {
			char c = str.toLowerCase().charAt(i);
			int index = c - 'a';
			if (lastIndexPoint[index] != -1 && lastIndexPoint[index] >= startIndex) {
				if (currentLength > maxSubStringLength) {
					maxSubStringLength = currentLength;
				}
				currentLength = 1;
				startIndex = i;
				lastIndexPoint[index] = i;
			} else {
				currentLength++;
				lastIndexPoint[index] = i;
			}
		}
		return maxSubStringLength == Integer.MIN_VALUE ? str.length() :
			(currentLength > maxSubStringLength ? currentLength : maxSubStringLength);
	}
	public static void main(String[] args) {
		int result = new LengthOfLongestPalindromicSubStringWithoutRepeatingChars()
			.calculate("GEEKSFORGEEKS");
		System.out.println(result);
	}
}

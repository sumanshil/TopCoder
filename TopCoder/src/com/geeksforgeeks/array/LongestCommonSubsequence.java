package com.geeksforgeeks.array;

/**
 * Created by sshil on 1/10/2016.
 */
public class LongestCommonSubsequence {

	public void lcs(String str1, String str2){
		//int result = recursive(str1, str2, str1.length(), str2.length());
		int result = dynamic(str1, str2);
		System.out.println(result);
	}

	private int dynamic(String str1, String str2){
		int str1Length = str1.length();
		int str2Length = str2.length();

		int rowLength = str1Length+1;
		int colLength = str2Length+1;
		int[][] matrix = new int[rowLength][colLength];

		for ( int i = 0 ; i < colLength ; i++){
			matrix[0][i] = 0;
		}

		for ( int i = 0 ; i < rowLength ; i++){
			matrix[i][0] = 0;
		}

		for ( int i = 1 ; i < rowLength ; i++) {
			for ( int j = 1 ; j < colLength ; j++) {
				if (str1.charAt(i-1) == str2.charAt(j-1)) {
					matrix[i][j] = matrix[i-1][j-1]+1;
				} else {
					matrix[i][j] = Math.max(matrix[i-1][j], matrix[i][j-1]);
				}
			}
		}
		return matrix[rowLength-1][colLength-1];
	}

	private int recursive(String str1, String str2, int length1, int length2) {
		if (length1 == 0 || length2 == 0){
			return 0;
		}
		if (str1.charAt(length1-1) == str2.charAt(length2-1)) {
			return  1 + recursive(str1, str2, length1-1, length2-1);
		} else {
			return Math.max(recursive(str1, str2, length1-1, length2), recursive(str1, str2, length1, length2-1));
		}
	}

	public static void main(String[] args) {
		new LongestCommonSubsequence().lcs("ABCDGH","AEDFHR");
	}
}

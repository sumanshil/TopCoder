package com.topcoder.problems.round26;
//http://community.topcoder.com/stat?c=problem_statement&pm=150&rd=3025
public class CompareSubstring {

	public int findLongest(String s1, String s2){
		int[][] matrix = new int[s1.length()][s2.length()];
		
		int max = Integer.MIN_VALUE;
		for(int i = 0 ; i < s1.length() ; i++){
			for(int j = 0 ; j < s2.length() ; j++){
				char c1 = s2.charAt(j);
				char c2 = s1.charAt(i);
				if ( c1 == c2 ){
					if (i == 0 || j == 0){
						matrix[i][j] = 1;
					} else {
						matrix[i][j] = matrix[i-1][j-1]+1;
						if (max < matrix[i][j]){
							max = matrix[i][j];
						}
					}
				}
			}
		}
		
		for(int i = 0 ; i < s1.length() ; i++){
			for(int j = 0 ; j < s2.length() ; j++){
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
		
		return max == Integer.MIN_VALUE ? 0 : max;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s1 = "Hey This java is hot";
		String s2 = "Java is a new paradigm";
        int result = new CompareSubstring().findLongest("aaaaaaaabaafdh", "abafdh");
        System.out.println(result);
	}

}

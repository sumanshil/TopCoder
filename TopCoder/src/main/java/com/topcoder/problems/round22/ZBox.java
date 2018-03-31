package com.topcoder.problems.round22;

//http://community.topcoder.com/stat?c=problem_statement&pm=137&rd=3021

public class ZBox {
	public int getZBoxes(String string, int i){
		int result = recursion(string, i, 0);
		return result;
	}
	private int recursion(String string, int i, int startIndex) {
		if (i == 0)
			return string.length();
		
		if (i == string.length() || string.charAt(i) != string.charAt(startIndex) ){
		    return 0;
		}
			
		int result = recursion(string, i+1, startIndex+1);			
		return result+1;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "aabcaabca";
		int i = 3;
		int result = new ZBox().getZBoxes(str, i);
		System.out.println(result);

	}

}

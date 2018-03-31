package com.topcoder.problems;
//http://community.topcoder.com/stat?c=problem_statement&pm=58&rd=3002
public class CommonChar {

	public int compare(String input1, String input2) {
		int[] c = new int[256];		
		int result=0;
		for(int i = 0 ; i < input1.length() ;i++){
			c[input1.charAt(i)]++;
		}
		
		for(int i = 0; i < input2.length(); i++){
			if (c[input2.charAt(i)] > 0){
				result++;
				c[input2.charAt(i)]--;
			}
		}
		
		return result;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        System.out.println(new CommonChar().compare("abc3", "abc3"));
        
	}

}

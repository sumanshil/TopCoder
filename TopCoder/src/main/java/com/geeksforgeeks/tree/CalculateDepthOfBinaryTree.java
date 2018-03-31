package com.geeksforgeeks.tree;

public class CalculateDepthOfBinaryTree {
	
	public void find(String str){
		int result = recursiveUtil(0, str);
		System.out.println(result);
	}
	
	private int recursiveUtil(int index, String str) {
		if (index >= str.length()) {
			return 0;			
		}
		if (str.charAt(index) == 'l') {
			return 0;
		}
		int lHeight = recursiveUtil(index+1, str);
		int rHeight = recursiveUtil(index+2, str);
		return Math.max(lHeight, rHeight)+1;
	}

	public static void main(String[] args){
		String str = "nlnnlll";
		new CalculateDepthOfBinaryTree().find(str);
	}

}

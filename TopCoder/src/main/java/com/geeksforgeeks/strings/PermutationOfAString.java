package com.geeksforgeeks.strings;

/**
 * Created by sshil on 1/9/2016.
 */
public class PermutationOfAString {

	public void permute(String str){
		permuteRecursively("", str);
	}

	private void permuteRecursively(String current, String remaining) {
		if (remaining.length() == 0){
			System.out.println(current);
			return;
		}

		for ( int i= 0 ; i < remaining.length() ; i++){
			String a = current;
			String b ="";

			a = a + remaining.charAt(i);
			if (i > 0){
				b = remaining.substring(0, i);
			}
			if ( i < remaining.length()-1) {
				b = b + remaining.substring(i + 1);
			}
			permuteRecursively(a, b);
		}
	}

	public static void main(String[] args) {
		String str = "abc";
		new PermutationOfAString().permute(str);
	}
}

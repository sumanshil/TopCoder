package com.geeksforgeeks.strings;
//http://www.geeksforgeeks.org/palindrome-pair-in-an-array-of-words-or-strings/
public class PalindromPairInArrayOfStrings {
	
	public void find (String[] arr) {
		for ( int i = 0 ; i < arr.length ; i++) {
			for (int j = i +1 ; j < arr.length ; j++) {
				String str = arr[i] + arr[j];
				if (isPalindrome (str)){
					System.out.println("Pair found : "+ arr[i]+" : "+ arr[j]);
				}
			}
		}
	}
	
	private boolean isPalindrome(String str) {
		int i = 0;
		int j = str.length()-1;
		while ( i < j ) {
			if (str.charAt(i) != str.charAt(j)) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}

	public static void main(String[] args) {
		String[] str ={"abc", "xyxcba", "geekst", "or",
                "keeg", "bc"};
		new PalindromPairInArrayOfStrings().find(str);
	}

}

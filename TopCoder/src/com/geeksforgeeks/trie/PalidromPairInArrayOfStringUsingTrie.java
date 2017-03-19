package com.geeksforgeeks.trie;

import com.geeksforgeeks.dynamicprogramming.PalindromicPartitioning;

public class PalidromPairInArrayOfStringUsingTrie {

	static class TrieNode {
		private char c;
		private int strIndex;
		private TrieNode[] children = new TrieNode[26];
		
		public TrieNode(char c ) {
			this.c = c;
		}
		
		public void insert(String str, int strIndex) {
			if (str == null || str.length() == 0) {
				return;
			}
			
			char c = str.charAt(0);
			int index = c - 'a';
			TrieNode child = this.children[index];
			if (child == null) {
				child = new TrieNode(c);
				this.children[index] = child;
			}
			if (str.length() == 1) {
				child.strIndex = strIndex;
			}
			child.insert(str.length() == 1 ? null : str.substring(1), strIndex);
		}
		
		public boolean isPalindromAvailable (String str, int strIndex) {
			TrieNode currentNode = this;
			for ( int i = 0 ; i < str.length() ; i++) {
				char c = str.charAt(i);
				int index = c - 'a';
				if ( currentNode.children[index] == null){
					return false;
				} else if (currentNode.children[index].strIndex != 0 && currentNode.children[index].strIndex != strIndex) {
					
					String subStr = str.substring(i+1);
					if (palinrome (subStr)) {
						return true;
					}
				}
				currentNode = currentNode.children[index];
			}
			return false;
		}

		private boolean palinrome(String subStr) {
			if (subStr.length() == 1 ) {
				return true;
			}
			int i = 0 , l= subStr.length()-1;
			while ( i < l ) {
				if (subStr.charAt(i) != subStr.charAt(l)){
					return false;
				}
			}
			return false;
		}
	}
	
	public static String reverse (String str) {
		StringBuilder sb = new StringBuilder();
		for ( int i = str.length()-1 ; i >= 0 ; i--){
			sb.append(str.charAt(i));
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String[] str = {"geekf", "geeks", "or",
                "keeg", "abc", "bc"};
		TrieNode root = new TrieNode(' ');
		for ( int i = 0 ; i < str.length ; i++) {
			String reversed = PalidromPairInArrayOfStringUsingTrie.reverse(str[i]);
			root.insert(reversed, i);
		}
		
		for ( int i = 0 ; i < str.length ; i++){
			boolean result = root.isPalindromAvailable(str[i], i);
			System.out.println(result);
		}
	}

}

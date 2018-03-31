package com.geeksforgeeks.advanced;

/**
 * Created by sshil on 1/20/2016.
 */
public class Trie1 {
	static class TrieNode{
		private char c;
		private boolean isWord;
		private TrieNode[] children;
		private int wordCount;
		public TrieNode(char c) {
			this.c = c;
			this.isWord = false;
			children = new TrieNode[26];
		}
	}

	private TrieNode root = new TrieNode(' ');

	public boolean checkIfWordExists(String str) {
		return checkRecursive(root, str);
	}

	private boolean checkRecursive(TrieNode root, String str) {
		if (root == null){
			return false;
		}
		char c = str.charAt(0);
		int index = c - 'a';
		TrieNode child = root.children[index];
		if (child == null){
			return  false;
		} else if (str.length() == 1) {
			return child.isWord;
		} else {
			return checkRecursive(child, str.substring(1));
		}
	}


	public void insert(String input){
		insertRecursive(input, root);
	}

	private void insertRecursive(String input, TrieNode root) {
		if ( input == null || input.length() == 0) {
			return;
		}
		char c = input.charAt(0);
		int index = c - 'a';
		TrieNode trieNode = root.children[index];
		if (trieNode == null){
			trieNode = new TrieNode(c);
			root.children[index] = trieNode;
		}
		trieNode.wordCount++;
		if (input.length() > 1){
			insertRecursive(input.substring(1), trieNode);
		} else {
			trieNode.isWord = true;
			insertRecursive(null, trieNode);
		}
	}

	public void delete(String str) {
		deleteRecursive(str, root);
	}

	private TrieNode deleteRecursive(String str, TrieNode root) {
		if (str.length() == 1){
			char c = str.charAt(0);
			int index = c - 'a';
			TrieNode trieNode = root.children[index];
			trieNode.wordCount--;
			if (trieNode.wordCount == 0){
				root.children[index] =null;
				return root;
			}
			if (trieNode != null ){
				trieNode.isWord = false;
				return root;
			}
			return null;
		}
		char c = str.charAt(0);
		int index = c - 'a';
		TrieNode child = root.children[index];
		if (child == null) {
			return  null;
		} else {
			root.children[index] = deleteRecursive(str.substring(1), child);
			root.wordCount--;
			if (root.wordCount == 0){
				return  null;
			} else {
				return root;
			}
		}
	}

	private int getChildCount(TrieNode node) {
		int childCount = 0;
		for (TrieNode child : node.children){
			if (child != null){
				childCount++;
			}
		}
		return childCount;
	}


	public static void main(String[] args) {
		String str = "suman";
		Trie1 trie1 = new Trie1();
		trie1.insert(str);
		trie1.insert("suma");
		trie1.delete("suman");
		boolean result = trie1.checkIfWordExists("suman");
		System.out.println(result);
	}
}

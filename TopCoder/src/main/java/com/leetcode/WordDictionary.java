package com.leetcode;

//https://leetcode.com/problems/add-and-search-word-data-structure-design/
public class WordDictionary {

    class TrieNode {
        String content;
        TrieNode[] children = new TrieNode[26];
        boolean isWord;

        public TrieNode(String s) {
            this.content = s;
        }
    }

    TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        this.root = new TrieNode("");
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        insertRecursive(word, root);
    }

    private void insertRecursive(String word, TrieNode root) {
        if (word == null || word.length() == 0) {
            return;
        }

        char c = word.charAt(0);
        int index = c - 'a';
        if (root.children[index] == null) {
            root.children[index] = new TrieNode(c+"");
        }
        if (word.length() > 1) {
            insertRecursive(word.substring(1), root.children[index]);
        } else {
            root.children[index].isWord = true;
        }

    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        boolean result = recursiveSearch(word, root);
        return result;
    }

    private boolean wordExistsInChildren(TrieNode node) {
        for (TrieNode node1 : node.children) {
            if (node1 != null && node1.isWord) {
                return true;
            }
        }
        return false;
    }
    private boolean recursiveSearch(String word, TrieNode root) {
        if (word.length() == 0) {
            return false;
        }
        if (word.length() == 1) {
            if (word.charAt(0) == '.') {
                if (wordExistsInChildren(root)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                int index = word.charAt(0) - 'a';
                return root != null && root.children[index] != null
                       && (word.charAt(0) + "").equals(root.children[index].content) && root.children[index].isWord;
            }
        }
        char c = word.charAt(0);

        if (c != '.') {
            int index = c - 'a';
            if (root.children[index] == null) {
                return false;
            }
            return recursiveSearch(word.substring(1), root.children[index]);
        } else {
            for (TrieNode child : root.children) {
                if (child != null) {
                    if (recursiveSearch(word.substring(1), child)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("at");
        wordDictionary.addWord("and");
        wordDictionary.addWord("an");
        wordDictionary.addWord("add");

        System.out.println(wordDictionary.search("a"));
        System.out.println(wordDictionary.search(".at"));

        wordDictionary.addWord("bat");

        System.out.println(wordDictionary.search(".at"));
        System.out.println(wordDictionary.search("an."));
        System.out.println(wordDictionary.search("a.d."));
        System.out.println(wordDictionary.search("b."));
        System.out.println(wordDictionary.search("a.d"));
        System.out.println(wordDictionary.search("."));

    }
}

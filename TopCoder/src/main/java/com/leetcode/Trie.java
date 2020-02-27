package com.leetcode;

//https://leetcode.com/problems/implement-trie-prefix-tree/
public class Trie {

    class TrieNode {
        String content;
        boolean isWord;
        TrieNode[] children = new TrieNode[26];
    }
    TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        recursiveInsert(root, word);
    }

    private void recursiveInsert(TrieNode root, String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        char c = word.charAt(0);
        int index = c - 'a';
        if (root.children[index] == null) {
            root.children[index] = new TrieNode();
            root.children[index].content = c+"";
        }
        if (word.length() > 1) {
            recursiveInsert(root.children[index], word.substring(1));
        } else {
            root.children[index].isWord = true;
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        return recursiveSearch(root, word);
    }

    private boolean recursiveSearch(TrieNode root, String word) {
        if (root == null && word.length() > 0) {
            return false;
        }
        if (word == null || word.length() == 0) {
            return true;
        }

        char c = word.charAt(0);
        int index = c - 'a';
        if (root.children[index] == null) {
            return false;
        }
        if (word.length() > 1) {
            return recursiveSearch(root.children[index], word.substring(1));
        } else {
            if (root.children[index].isWord) {
                return true;
            } else {
                return false;
            }
        }
    }

    private boolean isLeaf(TrieNode root) {
        for (TrieNode child : root.children) {
            if (child != null) {
                return false;
            }
        }
        return true;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return recursivePrefixSearch(root, prefix);
    }

    private boolean recursivePrefixSearch(TrieNode root, String word) {
        if (root == null && word.length() > 0) {
            return false;
        }
        if (word == null || word.length() == 0) {
            return true;
        }

        char c = word.charAt(0);
        int index = c - 'a';
        if (root.children[index] == null) {
            return false;
        }
        if (word.length() > 1) {
            return recursivePrefixSearch(root.children[index], word.substring(1));
        } else {
           return true;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));   // returns true
        System.out.println(trie.search("app"));     // returns false
        System.out.println(trie.startsWith("app")); // returns true
        trie.insert("app");
        System.out.println(trie.search("app"));     // returns true
    }
}

package com.geeksforgeeks.trie;

public class TrieDelete {
    static class TrieNode {
        private String data;
        private TrieNode[] children;
        private boolean isWord;

        TrieNode(String data){
            this.data = data;
            this.children = new TrieNode[26];
        }

        boolean isLeaf() {
            for (int i = 0 ; i < this.children.length ; i++) {
                if ( this.children[i] != null) {
                    return false;
                }
            }
            return true;
        }
    }

    static class Trie {
        private TrieNode root= new TrieNode("");

        public void insert(String str){
            TrieNode current = root;
            for (int i = 0 ; i < str.length() ; i++) {
                char c = str.charAt(i);
                int index = c - 'a';
                if (current.children[index] == null) {
                    current.children[index] = new TrieNode(c+"");
                }
                current = current.children[index];
            }
            current.isWord = true;
        }

        public boolean search(String input) {
            TrieNode current = this.root;
            for (int i = 0 ; i < input.length() ; i++) {
                char c = input.charAt(i);
                int index = c - 'a';
                if (current.children[index] == null){
                    return false;
                }
                current = current.children[index];
            }
            return current.isWord;
        }


        public void delete(String string) {
            recursiveDelete(this.root, 0, string.length(), string);
        }

        private boolean recursiveDelete(TrieNode root, int currentIndex, int currentLength, String str) {
            if (root == null) {
               return false;
            }

            if (currentIndex == currentLength && !root.isWord){
                return false;
            }

            if (currentIndex == currentLength && root.isWord){
                return true;
            }

            char c = str.charAt(currentIndex);
            int index = c - 'a';
            TrieNode node = root.children[index];

            if (recursiveDelete(node, currentIndex + 1, currentLength, str)) {
                if (node.isLeaf()){
                    if (node.isWord && currentIndex == currentLength-1) {
                        root.children[index] = null;
                        return true;
                    } else if (!node.isWord) {
                        root.children[index] = null;
                        return true;
                    }
                } else if (!node.isWord) {
                    root.children[index] = null;
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        /*
        Trie trie = new Trie();
        trie.insert("she");
        trie.insert("sells");
        trie.insert("sea");
        trie.insert("shore");
        trie.insert("the");
        trie.insert("by");
        trie.insert("sheer");
        System.out.println(trie.search("sea"));
        System.out.println(trie.search("sheer"));
        System.out.println(trie.search("shaving"));
        System.out.println(trie.search("the"));
        trie.delete("the");
        System.out.println(trie.search("the"));
        */
        System.out.println(Math.ceil(Math.log(5)));
    }

}

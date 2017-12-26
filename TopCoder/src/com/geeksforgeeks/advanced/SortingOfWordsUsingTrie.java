package com.geeksforgeeks.advanced;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

//http://www.geeksforgeeks.org/sorting-array-strings-words-using-trie/
public class SortingOfWordsUsingTrie {

    static class TrieNode {
        private String data;
        private TrieNode[] children;
        private boolean isWord;
        private int count = 0;

        public TrieNode (String data){
            this.data = data;
            this.children = new TrieNode[26];
        }

    }

    static class BfsNode {
        String content;
        TrieNode trieNode;
    }

    static class Trie {

        private TrieNode root = new TrieNode("");

        public void insert(String content) {
            TrieNode currentRoot = root;
            for (int i = 0; i < content.length(); i++) {
                char c = content.charAt(i);
                int index = c - 'a';
                if (currentRoot.children[index] == null) {
                    currentRoot.children[index] = new TrieNode(c + "");
                }
                currentRoot = currentRoot.children[index];
            }
            currentRoot.isWord = true;
            currentRoot.count = currentRoot.count + 1;
        }

        private List<String> result = new LinkedList<>();

        public void getAllStringRecursive(){
            recursiveUtil(root, "");
            String res = result.stream().collect(Collectors.joining("=>"));
            System.out.println(res);
        }

        private void recursiveUtil(TrieNode root, String string) {
            if (root == null) {
                return;
            }
            if (isLeafNode(root)) {
                if (root.isWord) {
                    String str = string ;
                    for ( int i = 0 ; i < root.count ; i++) {
                        result.add(str);
                    }
                }
                return;
            }
            for (TrieNode node : root.children) {
                if (node != null) {
                    recursiveUtil(node, string+node.data);
                }
            }
        }

        private boolean isLeafNode(TrieNode root) {
            for (TrieNode trieNode : root.children) {
                if (trieNode != null) {
                    return false;
                }
            }
            return true;
        }


        public List<String> getAllStrings() {
            List<String> retVal = new LinkedList<>();
            BfsNode bfsNode = new BfsNode();
            bfsNode.content = root.data;
            bfsNode.trieNode = root;
            Queue<BfsNode> queue = new LinkedList<>();
            queue.add(bfsNode);

            while (!queue.isEmpty()) {
                BfsNode node = queue.remove();
                TrieNode node1 = node.trieNode;

                TrieNode[] children = node1.children;

                for (TrieNode trieNode : children) {
                    if (trieNode != null) {
                        BfsNode newBfsNode = new BfsNode();
                        newBfsNode.content = node.content + trieNode.data;
                        newBfsNode.trieNode = trieNode;
                        queue.add(newBfsNode);
                        if (trieNode.isWord) {
                            for (int i = 0; i < trieNode.count; i++) {
                                retVal.add(newBfsNode.content);
                            }
                        }
                    }
                }
            }
            return retVal;
        }
    }

    public static void main(String[] args) {
        String[] str = {"geeks", "for", "geeks", "a", "portal",
                        "to", "learn", "can", "be", "computer",
                        "science", "zoom", "yup", "fire", "in", "data"};
        Trie trie = new Trie();
        Arrays.stream(str).forEach(trie::insert);
        trie.getAllStringRecursive();
    }

}

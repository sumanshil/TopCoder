package com.geeksforgeeks.advanced;

import java.util.*;

/**
 * Created by sshil on 10/12/16.
 */
//http://www.geeksforgeeks.org/implement-a-phone-directory/
public class ImplementAPhoneDirectory {

     class Trie {
         private TrieNode root = new TrieNode(' ');
         public void add (String str){
             root.add(str, str);
         }

         public TrieNode getNode(String str){
             TrieNode currentNode = root;
             for ( int i = 0 ; i < str.length() ; i++){
                 char c = str.charAt(i);
                 int index = c - 'a';
                 currentNode = currentNode.children[index];
                 if (currentNode == null){
                     return currentNode;
                 }
             }
             return currentNode;
         }
     }


     class TrieNode {
         private char c ;
         private TrieNode[] children = new TrieNode[26];
         private boolean isWord;
         private Set<String> words = new HashSet<>();

         public TrieNode(char c){
             this.c = c;
         }

         public List<String> getAllChildren(){
             List<String> finalList = new ArrayList<>();
             List<String> currentList = new ArrayList<>();
             recursive(this, finalList, currentList);
             return finalList;
         }

         private void recursive(TrieNode trieNode, List<String> finalList, List<String> currentList) {
             if (trieNode.isLeaf()){
                 StringBuffer sb = new StringBuffer();
                 currentList.stream().forEach(s -> sb.append(s));
                 finalList.add(sb.toString());
                 return;
             }

             for ( int i = 0 ; i < this.children.length ; i++){
                 TrieNode child = this.children[i];
                 if (child != null){
                     currentList.add(this.children[i].c+"");
                     recursive(this.children[i], finalList, currentList);
                     currentList.remove(currentList.size()-1);
                 }
             }
         }

         private boolean isLeaf() {
             for ( int i = 0 ; i < this.children.length ; i++){
                 if (this.children[i] != null) {
                     return true;
                 }
             }
             return false;
         }

         public void add(String str, String originalStr) {
             if (str == null || str.length() == 0){
                 return;
             }
             char c = str.charAt(0);
             int index = c - 'a';
             TrieNode node = children[index];
             if (node == null) {
                 node = new TrieNode(c);
                 children[index] = node;
             }
             node.words.add(originalStr);
             if (str.length() > 1){
                 node.add(str.substring(1), str);
             } else {
                 node.isWord = true;
                 node.add(null, originalStr);
             }
        }
    }

    public void find(String[] contacts, String queryString){
        Trie trie = new Trie();
        Arrays.stream(contacts).forEach(e -> trie.add(e));
        for (int i = 1 ; i < queryString.length() ; i++){
            String str = queryString.substring(0, i);
            TrieNode node1 = trie.getNode(str);
            if (node1 != null) {
                node1.words.stream().forEach(System.out::println);
            }
        }
    }

    public static void main(String[] args) {
        String[] strArr = {"gforgeeks", "geeksquiz"};
        String queryString = "gekk";
        new ImplementAPhoneDirectory().find(strArr, queryString);
    }
}

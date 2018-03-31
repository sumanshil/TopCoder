package com.geeksforgeeks.advanced;

import java.util.ArrayList;
import java.util.List;
//
public class PrintTrieContent {

    static class TrieNode {
        private char c;
        TrieNode[] childNodes;
        boolean isWord;

        public TrieNode(char c){
            this.c = c;
            this.childNodes = new TrieNode[26];
        }

        public void addString(String text){
            if (text == null || text.length() == 0){
                return;
            }
            char c = text.charAt(0);
            int index = c - 'a';
            if (this.childNodes[index] == null){
                this.childNodes[index] = new TrieNode(c);
            }
            if (text.length() > 1) {
                this.childNodes[index].addString(text.substring(1));
            } else {
                this.childNodes[index].isWord = true;
            }
        }

        public List<String> getContent(){
            List<String> retVal = new ArrayList<>();
            List<String> childStrings = new ArrayList<>();

            for (TrieNode trieNode : this.childNodes) {
                if (trieNode != null) {
                    List<String> children = trieNode.getContent();
                    childStrings.addAll(children);
                }
            }

            if (childStrings.size() > 0) {
                for (String str : childStrings) {
                    retVal.add(this.c + str);
                }
            }
            if (this.isWord){
                retVal.add(this.c+"");
            }
            return retVal;
        }
    }

    public static void main(String[] args) {
        TrieNode root = new TrieNode(' ');
        root.addString("the");
        root.addString("a");
        root.addString("there");
        root.addString("answer");
        root.addString("any");
        root.addString("by");
        root.addString("bye");
        root.addString("there");
        List<String> list = root.getContent();
        list.stream().forEach(System.out::println);
    }
}

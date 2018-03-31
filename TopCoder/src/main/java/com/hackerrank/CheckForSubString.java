package com.hackerrank;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sshil on 4/9/2016.
 */
//https://www.hackerrank.com/challenges/two-strings
public class CheckForSubString {
    class TrieNode {
        char c;
        TrieNode[] children;
        TrieNode(char c){
            this.c  = c;
            children = new TrieNode[26];
        }

        public void insert(String str){
            if (str == null || str.length() == 0){
                return;
            }

            char c = str.charAt(0);
            int index = c -'a';
            TrieNode child;
            if (this.children[index] == null){
                this.children[index] = new TrieNode(c);
            }
            child = this.children[index];
            if (str.length() > 1){
                child.insert(str.substring(1));
            }
        }

        public boolean findPartial(String str){
            if (str == null || str.length() == 0){
                return true;
            }
            char c = str.charAt(0);
            int index = c - 'a';
            TrieNode child = this.children[index];
            return child != null;
        }
    }
    private TrieNode root = new TrieNode(' ');

    public boolean find(String str1, String str2){
        /*for ( int i = 0; i < str1.length() ; i++){
            String subStr = str1.substring(i, str1.length());
            root.insert(subStr);
        }
        for ( int i = 0 ; i < str2.length() ;i++) {
            String subStr = str2.substring(i);
            if (root.findPartial(subStr)){
                return true;
            }
        }*/
        Set<String> set = new HashSet<>();
        for (int i = 0; i < str2.length() ; i++){
            set.add(str2.charAt(i)+"");
        }
        for (int i = 0; i < str1.length() ; i++){
            char c = str1.charAt(i);
            if (set.contains(c+"")){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String str1 = "hello";
        String str2 = "world";
        CheckForSubString checkForSubString = new CheckForSubString();
        boolean result = checkForSubString.find(str1, str2);
        System.out.println(result);
    }
}

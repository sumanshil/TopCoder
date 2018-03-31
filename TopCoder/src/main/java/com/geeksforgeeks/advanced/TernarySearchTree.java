package com.geeksforgeeks.advanced;

public class TernarySearchTree {

    static class Node{
        char c ;
        Node left;
        Node right;
        Node equal;
        boolean isWord;
        public Node(char c){
            this.c = c;
        }
    }
    
    public Node root = null;
    public void insert(String word){
        for(int i = 0 ; i < word.length(); i++){
           root = insertUtil(root, word,0);
        }
    }
    
    public Node insertUtil(Node root, String str, int index){
       if (root == null){ 
           root = new Node(str.charAt(index));
           if ((index+1)>=str.length()){
               root.isWord = true;
           }
           return root;
       }    
       
       if (root.c < str.charAt(index)){
               root.right = insertUtil(root.right, str, index);
       } else if (root.c > str.charAt(index)){
               root.left = insertUtil(root.left, str, index);
       } else {
           if(index+1 < str.length())
               root.equal = insertUtil(root.equal, str,index+1);
       }       
       return root;
    }
    
    public boolean traverseUtil(Node root, String word, int index){
        if (index>= word.length() && root != null)
            return false;
        if (index < word.length() && root == null)
            return false;
        
        if (index == word.length()-1 && root.c == word.charAt(index)){
            return true;
        }
        if (root.c < word.charAt(index)){
            return traverseUtil(root.right, word, index);
        } else if (root.c > word.charAt(index)){
            return traverseUtil(root.left, word, index);
        } else{
           return  traverseUtil(root.equal, word, index+1);
        }        
    }
    public boolean findWord(String word){
        boolean result = traverseUtil(root, word, 0);
        return result;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        TernarySearchTree tst = new TernarySearchTree();
        tst.insert("cat");
        tst.insert("cats");
        tst.insert("up");
        tst.insert("bug");
        boolean result = tst.findWord("bug");
        System.out.println(result);
    }

}

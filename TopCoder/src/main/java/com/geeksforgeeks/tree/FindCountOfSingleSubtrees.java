package com.geeksforgeeks.tree;

/**
 * Created by sshil on 11/18/2015.
 */
//http://www.geeksforgeeks.org/find-count-of-singly-subtrees/
public class FindCountOfSingleSubtrees {
    static class  Node {
        private int data;
        private Node left;
        private Node right;
        Node(int data ){
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public String toString(){
            return  data+"";
        }
    }

    public int countSingleValueSubTree(Node root){
        if (root == null) {
            return  0;
        }
        if (root.getRight() == null && root.getLeft() == null) {
            return  1;
        }

        int lCount = countSingleValueSubTree(root.getLeft());
        int rCount = countSingleValueSubTree(root.getRight());
        boolean leftSame = true;
        if (root.getLeft() != null && root.getLeft().data != root.data){
            leftSame = false;
        }
        boolean rightSame = true;
        if (root.getRight() != null && root.getRight().data != root.data) {
           rightSame = false;
        }
        if (leftSame && rightSame){
            return  lCount + rCount + 1;
        } else  {
            return lCount + rCount;
        }
    }



    public static void main(String[] args){
        Node root = new Node(5);
        root.setLeft(new Node(4));
        root.setRight(new Node(5));
        root.getLeft().setLeft(new Node(4));
        root.getLeft().setRight(new Node(4));

        root.getRight().setRight(new Node(5));
        int result = new FindCountOfSingleSubtrees()
                     .countSingleValueSubTree (root);
        System.out.println(result);
    }

}

package com.geeksforgeeks.advanced;

public class AVLTree {
    static class Node{
        int key;
        Node left;
        Node right;
        int height;
        public Node(int key){
            this.key = key;
        }
    }
    
    public int height(Node node){
        if (node == null){
            return 0;
        }
        return node.height;
    }
    
    public int getBalance(Node node){
        if (node == null)
            return 0;
        
        return height(node.left)-height(node.right);
    }
    
    
    public Node rightRotate(Node z){
        Node y = z.left;
        Node T3 = y.right;
        z.left = T3;
        y.right = z;
        y.height = Math.max(height(y.left), height(y.right))+1;
        z.height = Math.max(height(z.left), height(z.right))+1;
        return y;
    }
    
    public Node leftRotate(Node z){
        Node y = z.right;
        Node T3 = y.left;
        y.left = z;
        z.right = T3;
        y.height = Math.max(height(y.left), height(y.right))+1;
        z.height = Math.max(height(z.left), height(z.right))+1;
        return y;        
    }
    public Node insert(Node root, int key){
        if (root == null)
            return new Node(key);
        if (key > root.key){
            root.right = insert(root.right, key);
        } else {
            root.left = insert(root.left, key);
        }
        root.height = Math.max(height(root.left), height(root.right))+1;
        int balance = getBalance(root);
        if (balance > 1 && key < root.left.key){
            return rightRotate(root);
        }
        
        if (balance > 1 && key > root.left.key ){
            root.left = leftRotate(root.left);
            return rightRotate(root.right);
        }
        
        return root;
    }
    
    
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}

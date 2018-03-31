package com.geeksforgeeks.tree;

import com.topcoder.geeksforgeeks.BinarySearchTree;

//http://www.geeksforgeeks.org/add-greater-values-every-node-given-bst/
public class AddAllGreaterValues {

    private int valueToAdd = 0;
    public BinarySearchTree.TreeNode modifyTree(BinarySearchTree.TreeNode root){
        if (root == null){
            return null;
        }
        
        modifyTree(root.right);
        if (root != null){
            root.data = root.data + valueToAdd;
            valueToAdd = root.data;
        }
        modifyTree(root.left);
        return root;
    }
    
    public void inorder(BinarySearchTree.TreeNode root){
        if (root != null){
            inorder(root.left);
            System.out.println(root.data);
            inorder(root.right);
        }
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
       BinarySearchTree.TreeNode root = new BinarySearchTree.TreeNode(50);
       root.left =  new BinarySearchTree.TreeNode(30);
       root.left.left =new BinarySearchTree.TreeNode(20);
       root.left.right = new BinarySearchTree.TreeNode(40);

       root.right = new BinarySearchTree.TreeNode(70);
       root.right.right = new BinarySearchTree.TreeNode(80);
       root.right.left = new BinarySearchTree.TreeNode(60);
       BinarySearchTree.TreeNode root1 = new AddAllGreaterValues().modifyTree(root);
       new AddAllGreaterValues().inorder(root1);
    }

}

package com.geeksforgeeks.tree;

import com.topcoder.geeksforgeeks.BinarySearchTree;
import com.topcoder.geeksforgeeks.BinarySearchTree.TreeNode;

public class ExtractLeavesToDLL {

    private static BinarySearchTree.TreeNode head = null;
    private static BinarySearchTree.TreeNode tail = null;

    public TreeNode extract(BinarySearchTree.TreeNode root){
        if (root == null){
            return null;
        }        
        if (root.left != null && root.right != null)
            root.isNonLeaf = true;
        TreeNode left = extract(root.left);
        TreeNode right = extract(root.right);
        
        if (left != null && left.left == null && left.right == null && !left.isNonLeaf){
            insert(left);
        }
//        else if (left != null) {
//            left.isNonLeaf = true;
//        }
        
        if (right != null && right.left == null && right.right == null && !right.isNonLeaf){
            insert(right);
        }
//        else if (right != null){
//            right.isNonLeaf = true;
//        }

        if (root.left != null && root.left.right!=null && root.left.right.left == root.left){
            root.left = null;
        }
        
        if (root.right != null && root.right.left!= null &&root.right.left.right == root.right){
            root.right = null;
        }
        return root;
    }

    
    public TreeNode extract1(TreeNode root){
        if (root == null)
            return null;
        if (root.left == null && root.right == null){
            insert(root);
            return null;
        }
        root.left = extract1(root.left);
        root.right = extract1(root.right);
        return root;
    }
    
    public void inorder(TreeNode root){
        if (root != null){
            inorder(root.left);
            System.out.println(root.data);
            inorder(root.right);
        }
    }
    
    private void insert(TreeNode node) {
        if (head == null){
            head = node;
            tail = head;
        } else{
            tail.right = node;
            node.left = tail;
            tail = node;
        }
        
        
        
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        BinarySearchTree.TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(8);

        root.right= new TreeNode(3);
        root.right.right= new TreeNode(6);
        root.right.right.right= new TreeNode(10);
        root.right.right.left= new TreeNode(9);
        new ExtractLeavesToDLL().extract1(root);
        new ExtractLeavesToDLL().inorder(root);
        printDLL();
    }
    private static void printDLL() {
        TreeNode temp= head;
        while(temp!= null){
            System.out.print(temp.data+" ");
            temp = temp.right;
        }
        System.out.println();
    }

}

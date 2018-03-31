package com.geeksforgeeks.tree;

import com.topcoder.geeksforgeeks.BinarySearchTree;

public class CheckIfLeafAreOnSameLevel {

    private int level = Integer.MAX_VALUE;
    public boolean checkLevel(BinarySearchTree.TreeNode node, int level){
        if (node != null && node.left == null && node.right == null){
            if (this.level != Integer.MAX_VALUE && this.level != level){
                return false;
            } else if (this.level == Integer.MAX_VALUE){
                this.level = level;
                return true;
            }
        } else if (node == null){
            return true;
        }
        
        boolean isLeftOk = checkLevel(node.left, level+1);
        boolean isRightOk = checkLevel(node.right, level+1);
        return isLeftOk && isRightOk;
    }
    /**
     * @param args
     */
    public static BinarySearchTree.TreeNode getTreeNode1(){
        BinarySearchTree.TreeNode root = new BinarySearchTree.TreeNode(12);
        root.left = new BinarySearchTree.TreeNode(5);
        root.right = new BinarySearchTree.TreeNode(7);

        root.left.left = new BinarySearchTree.TreeNode(3);
        root.right.right = new BinarySearchTree.TreeNode(1);
        return root;        
    }
    
    public static BinarySearchTree.TreeNode getTreeNode2(){
        BinarySearchTree.TreeNode root = new BinarySearchTree.TreeNode(12);
        root.left = new BinarySearchTree.TreeNode(5);
        root.right = new BinarySearchTree.TreeNode(7);

        root.left.left = new BinarySearchTree.TreeNode(3);
        return root;        
    }

    public static BinarySearchTree.TreeNode getTreeNode3(){
        BinarySearchTree.TreeNode root = new BinarySearchTree.TreeNode(12);
        root.left = new BinarySearchTree.TreeNode(5);
        root.left.right = new BinarySearchTree.TreeNode(9);
        root.left.right.left = new BinarySearchTree.TreeNode(2);

        root.left.left = new BinarySearchTree.TreeNode(3);
        root.left.left.left = new BinarySearchTree.TreeNode(1);
        return root;        
    }
    
    public static void main(String[] args) {
        BinarySearchTree.TreeNode root = getTreeNode3();

        boolean result = new CheckIfLeafAreOnSameLevel().checkLevel(root, 0);
        System.out.println(result);
    }

}

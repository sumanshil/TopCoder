package com.geeksforgeeks.tree;

import com.topcoder.geeksforgeeks.BinarySearchTree;

public class FindDepthOfDeepestOddLevelNode {

    public int getOddLevelMaxDepth(BinarySearchTree.TreeNode root , int level){
        if (root != null && root.left == null && root.right == null){
            if (level %2 != 0){
                return level;
            } else {
                return 0;
            }
        } else if (root == null){
            return 0;
        }
        int leftValue = getOddLevelMaxDepth(root.left, level+1);
        int rightValue = getOddLevelMaxDepth(root.right, level+1);
        return (leftValue > rightValue ? leftValue : rightValue);
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
       BinarySearchTree.TreeNode root = new BinarySearchTree.TreeNode(1);
       root.left = new BinarySearchTree.TreeNode(2);
       root.left.left = new BinarySearchTree.TreeNode(4);

       root.right = new BinarySearchTree.TreeNode(3);
       root.right.left = new BinarySearchTree.TreeNode(5);
       root.right.left.right = new BinarySearchTree.TreeNode(7);
       root.right.left.right.left = new BinarySearchTree.TreeNode(9);
       
       root.right.right = new BinarySearchTree.TreeNode(6);
       root.right.right.right = new BinarySearchTree.TreeNode(8);
       root.right.right.right.right = new BinarySearchTree.TreeNode(10);
       root.right.right.right.right.left = new BinarySearchTree.TreeNode(11);
       
       int result = new FindDepthOfDeepestOddLevelNode().getOddLevelMaxDepth(root, 1);
       System.out.println(result);
    }

    
}

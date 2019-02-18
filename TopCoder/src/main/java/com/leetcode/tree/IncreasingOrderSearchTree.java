package com.leetcode.tree;

import java.util.Stack;

//https://leetcode.com/problems/increasing-order-search-tree/
public class IncreasingOrderSearchTree {

   public static class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
   }
    public TreeNode increasingBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode newRoot = null;
        TreeNode prevNode = null;
        while (!stack.empty()) {

            TreeNode top = stack.peek();
            TreeNode left = top.left;
            while (left != null) {
                stack.push(left);
                left = left.left;
            }
            while (!stack.isEmpty()) {

                TreeNode topNode = stack.pop();
                if (newRoot == null) {
                    newRoot = topNode;
                }
                if (prevNode != null) {
                    prevNode.right = topNode;
                }
                prevNode =topNode;
                prevNode.left = null;

                if (topNode.right != null) {
                    stack.push(topNode.right);
                    break;
                }
           }
        }
        return newRoot;

    }

    public static void main(String[] args) {
        TreeNode node5 = new TreeNode(5);
        TreeNode node3 = new TreeNode(3);
        TreeNode node6 = new TreeNode(6);
        node5.left = node3;
        node5.right = node6;
        TreeNode node2 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        node3.left = node2;
        node3.right = node4;
        TreeNode node1 = new TreeNode(1);
        node2.left = node1;
        TreeNode node8 = new TreeNode(8);
        node6.right = node8;
        TreeNode node7 = new TreeNode(7);
        TreeNode node9 = new TreeNode(9);
        node8.left = node7;
        node8.right = node9;

        TreeNode result = new IncreasingOrderSearchTree().increasingBST(node5);



    }

}

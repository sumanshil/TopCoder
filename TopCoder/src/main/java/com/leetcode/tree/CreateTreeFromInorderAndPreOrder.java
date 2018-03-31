package com.leetcode.tree;

import java.util.Stack;

public class CreateTreeFromInorderAndPreOrder {

    public TreeNode buildTree(int[] inOrder, int[] preOrder) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(preOrder[0]);
        TreeNode curr = root;
        for ( int preorderIndex = 1 , inorderIndex = 0 ; preorderIndex < preOrder.length ; preorderIndex++) {
            if (curr.val != inOrder[inorderIndex]) {
                stack.push(curr);
                curr.left = new TreeNode(preOrder[preorderIndex]);
                curr = curr.left;
            } else {
                inorderIndex++;
                while (!stack.isEmpty() && stack.peek().val == inOrder[inorderIndex]) {
                    curr = stack.pop();
                    inorderIndex++;
                }
                curr.right = new TreeNode(preOrder[preorderIndex]);
                curr = curr.right;
            }
        }
        return root;
    }


    public static void main(String[] args) {
        int[] preOrder = {3,9,20,15,7};
        int[] inOrder = {9,3,15,20,7};
        TreeNode treeNode = new CreateTreeFromInorderAndPreOrder()
                .buildTree(preOrder, inOrder);

    }
}

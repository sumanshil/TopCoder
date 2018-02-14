package com.leetcode.tree;


import java.util.Stack;

//https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
public class CreateBinaryTreeFromPreorderAndInOrderTraversalIterative {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = new TreeNode(preorder[0]);
        TreeNode root = curr;
        for ( int preOrderIndex = 1, inOrderIndex = 0; preOrderIndex < preorder.length ; preOrderIndex++) {
            if (curr != null && curr.val != inorder[inOrderIndex]) {
                stack.push(curr);
                curr.left = new TreeNode(preorder[preOrderIndex]);
                curr = curr.left;
            } else {
                inOrderIndex++;
                while (!stack.isEmpty() && stack.peek().val == inorder[inOrderIndex++]) {
                    curr = stack.pop();
                }
                curr.right = new TreeNode(preorder[preOrderIndex]);
                curr = curr.right;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        int[] preOrder = {3,9,20,15,7};
        int[] inOrder = {9,3,15,20,7};
        TreeNode treeNode = new CreateBinaryTreeFromPreorderAndInOrderTraversalIterative()
                .buildTree(preOrder, inOrder);

    }

}

package com.leetcode.tree;

import java.util.Stack;

//https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
public class ConstructBinaryTreeFromPreorderAndInorder {

    private int preOrcderIndex = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //TreeNode rootNode = recursive(0, inorder.length-1, preorder, inorder);
        TreeNode rootNode = buildIterative(inorder, preorder);
        return rootNode;
    }

    private TreeNode recursive(int inOrderStart, int inOrderEnd, int[] preorder, int[] inorder) {
        if (inOrderStart > inOrderEnd) {
            return null;
        }

        int rootElement = preorder[preOrcderIndex++];
        TreeNode newRoot = new TreeNode(rootElement);
        int inOrderIndex = getInorderIndex(inorder, rootElement, inOrderStart, inOrderEnd);

        newRoot.left = recursive(inOrderStart, inOrderIndex-1, preorder, inorder);
        newRoot.right = recursive(inOrderIndex+1, inOrderEnd, preorder, inorder);

        return newRoot;
    }


    private TreeNode buildIterative(int[] inorder, int[] preorder) {

        Stack<TreeNode> stack = new Stack<>();

        int inorderIndex = 0;
        int preOrderIndex;

        TreeNode root = new TreeNode(preorder[0]);
        stack.push(root);

        for (preOrderIndex = 1 ; preOrderIndex < preorder.length ; preOrderIndex++) {
            TreeNode current = new TreeNode(preorder[preOrderIndex]);

            if (!stack.isEmpty() && stack.peek().val != inorder[inorderIndex]) {
                stack.peek().left = current;
                stack.push(current);
            } else {
                TreeNode currentTopNode = null;
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    currentTopNode = stack.pop();
                    inorderIndex++;
                }
                if (currentTopNode != null) {
                    currentTopNode.right = current;
                    stack.push(current);
                }
            }
        }
        return root;
    }

    private int getInorderIndex(int[] inorder, int rootElement, int inOrderStart, int inOrderEnd) {

        for (int i = inOrderStart ; i <= inOrderEnd ; i++) {
            if (inorder[i] == rootElement) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] inOrder = {9,3,15,20,7};
        int[] preOrder = {3,9,20,15,7};
        TreeNode newRoot = new ConstructBinaryTreeFromPreorderAndInorder().buildTree(preOrder, inOrder);
        System.out.println(newRoot);
    }
}

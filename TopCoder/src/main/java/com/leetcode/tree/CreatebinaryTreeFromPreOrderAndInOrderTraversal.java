package com.leetcode.tree;


//https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
public class CreatebinaryTreeFromPreOrderAndInOrderTraversal {

    private int preOrderIndex = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = buildRecursive(preorder, inorder, 0, inorder.length-1);
        return root;
    }

    private TreeNode buildRecursive(int[] preorder, int[] inorder, int start, int end) {
        if (end < start) {
            return null;
        }

        int root = preorder[preOrderIndex++];
        int index = findInorderIndexForRoot(root, inorder, start, end);
        int inOrderRoot = inorder[index];
        if (inOrderRoot == -1) {
            return null;
        }
        TreeNode newRoot = new TreeNode(root);
        newRoot.left = buildRecursive(preorder, inorder, start, index-1);
        newRoot.right = buildRecursive(preorder, inorder, index + 1, end);
        return newRoot;
    }

    private int findInorderIndexForRoot(int root, int[] inorder, int start, int end) {
        for (int i = start ; i <= end ; i++) {
            if (inorder[i] == root) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] inOrder = {3,9,20,15,7};
        int[] preOrder = {9,3,15,20,7};
        TreeNode root = new CreatebinaryTreeFromPreOrderAndInOrderTraversal()
                .buildTree(preOrder, inOrder);
        System.out.println(root);
    }
}

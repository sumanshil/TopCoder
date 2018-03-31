package com.leetcode.tree;


import java.util.Stack;

//https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/
public class CreateBinaryTreeFromInorderAndPostorder {

    private int postOrderIndex = 0;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postOrderIndex = postorder.length - 1;
        TreeNode root = iterative(inorder, postorder);
        return root;
    }

    public TreeNode iterative(int[] inorder, int[] postorder){
        Stack<TreeNode> stack = new Stack<>();
        int postOrderIndex = postorder.length - 1;
        TreeNode root = new TreeNode(postorder[postOrderIndex]);
        TreeNode curr = root;
        for ( int pi = postOrderIndex - 1, ii = inorder.length-1; pi >= 0 ; pi--) {
            if (curr.val != inorder[ii]) {
                stack.push(curr);
                curr.right = new TreeNode(postorder[pi]);
                curr = curr.right;
            } else {
                ii--;
                while (!stack.isEmpty() && stack.peek().val == inorder[ii]){
                    curr = stack.pop();
                    ii--;
                }
                curr.left = new TreeNode(postorder[pi]);
                curr = curr.left;
            }
        }
        return root;
    }


    private TreeNode recursive(int[] inorder, int low, int high, int[] postOrder) {
        if (low > high) {
            return null;
        }

        int root = postOrder[postOrderIndex--];
        int inOrderIndex = getInorderIndex(inorder, root, low, high);
        TreeNode newRoot = new TreeNode(inorder[inOrderIndex]);
        newRoot.right = recursive(inorder,inOrderIndex+1, high, postOrder);
        newRoot.left = recursive(inorder, low, inOrderIndex-1, postOrder);
        return newRoot;
    }

    private int getInorderIndex(int[] inorder, int root, int low, int high) {
        for (int i = low; i <= high ; i++) {
            if (inorder[i] == root) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] inOrder = {9,3,15,20,7};
        int[] postOrder = {9,15,7,20,3};
        TreeNode root =
                new CreateBinaryTreeFromInorderAndPostorder().buildTree(inOrder, postOrder);
    }

}

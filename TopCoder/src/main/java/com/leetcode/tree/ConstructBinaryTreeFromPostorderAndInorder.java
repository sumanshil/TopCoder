package com.leetcode.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
public class ConstructBinaryTreeFromPostorderAndInorder {

    private int postIndex = 0;
    private Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int postOrderIndex = postorder.length - 1;
        int inorderIndex = inorder.length - 1;

        TreeNode root = new TreeNode(postorder[postOrderIndex--]);
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        for (postOrderIndex = postorder.length - 2 ; postOrderIndex >= 0 ; postOrderIndex--) {
            TreeNode currentNode = new TreeNode(postorder[postOrderIndex]);
            if (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                stack.peek().right = currentNode;
                stack.push(currentNode);
            } else {
                TreeNode currentTopNode = null;
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    currentTopNode = stack.pop();
                    inorderIndex--;
                }
                if (currentTopNode != null) {
                    currentTopNode.left = currentNode;
                    stack.push(currentNode);
                }

            }
        }
        return root;
        /*
        postIndex = postorder.length-1;
        for (int i = 0 ; i < inorder.length ; i++) {
            map.put(inorder[i], i);
        }
        TreeNode result = recursive(0, inorder.length-1, inorder, postorder);
        return result;
        */
    }

    private TreeNode recursive(int inStart, int inEnd, int[] inorder, int[] postorder) {
        if (inStart > inEnd) {
            return null;
        }

        int element = postorder[postIndex--];

        TreeNode root = new TreeNode(element);

        int inorderIndex = getInorderIndex(element);

        root.right = recursive(inorderIndex+1, inEnd, inorder, postorder);
        root.left = recursive(inStart, inorderIndex-1, inorder, postorder);
        return root;
    }

    private int getInorderIndex(int element) {
        return map.get(element);
    }

    public static void main(String[] args) {
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        TreeNode result = new ConstructBinaryTreeFromPostorderAndInorder().buildTree(inorder, postorder);

    }
}

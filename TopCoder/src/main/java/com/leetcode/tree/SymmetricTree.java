package com.leetcode.tree;

import java.util.Stack;

//https://leetcode.com/problems/symmetric-tree/description/
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean result = iterative(root.left, root.right);
        return result;
    }

    private boolean recursiveUtil(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if ( root1 == null || root2 == null ) {
            return false;
        }

        return root1.val == root2.val
               && (recursiveUtil(root1.left, root2.right))
               && (recursiveUtil(root1.right, root2.left));
    }

    private boolean iterative(TreeNode root1, TreeNode root2) {
        Stack<TreeNode> leftToRightStack = new Stack<>();
        Stack<TreeNode> rightToLeftStack = new Stack<>();

        leftToRightStack.push(root1);
        rightToLeftStack.push(root2);
        while (!leftToRightStack.isEmpty() && !rightToLeftStack.isEmpty()) {
            TreeNode node1 = leftToRightStack.pop();
            TreeNode node2 = rightToLeftStack.pop();
            if ( (node1 == null && node2 != null) || (node2 == null && node1 != null) ) {
                return false;
            }
            if (node1 == null) {
                continue;
            }
            if (node1.val != node2.val) {
                return false;
            }
            leftToRightStack.push(node1.left);
            leftToRightStack.push(node1.right);

            rightToLeftStack.push(node2.right);
            rightToLeftStack.push(node2.left);
        }

        return leftToRightStack.isEmpty() && rightToLeftStack.isEmpty();
    }


    public static void main(String[] args) {
        /*
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);

        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(3);
        boolean result = new SymmetricTree().isSymmetric(root);
        System.out.println(result);
    }
}

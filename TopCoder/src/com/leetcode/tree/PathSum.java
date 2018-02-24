package com.leetcode.tree;

import java.util.Stack;

//https://leetcode.com/problems/path-sum/description/
public class PathSum {

    private boolean found = false;
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        boolean result = iterative(root, sum);
        return result;
    }

    private boolean iterative(TreeNode root, int sum) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> values = new Stack<>();
        stack.push(root);
        values.push(root.val);
        while (!stack.isEmpty()) {
            TreeNode topNode = stack.pop();
            int currentSum = values.pop();
            if (topNode.left == null && topNode.right == null && currentSum == sum) {
                return true;
            }

            if (topNode.left != null) {
                stack.push(topNode.left);
                values.push(currentSum + topNode.left.val);
            }

            if (topNode.right != null) {
                stack.push(topNode.right);
                values.push(currentSum + topNode.right.val);
            }
        }
        return false;
    }


    private boolean recursive(TreeNode root, int sum) {
        if (found) {
            return true;
        }
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && sum - root.val == 0) {
            found = true;
            return true;
        }

        boolean result = recursive(root.left, sum - root.val)
                         || recursive(root.right, sum - root.val);
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-3);
        root.right = new TreeNode(-2);
        boolean result = new PathSum().hasPathSum(root, -5);
        System.out.println(result);

    }

}

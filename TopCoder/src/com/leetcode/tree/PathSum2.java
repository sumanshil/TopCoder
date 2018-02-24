package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PathSum2 {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> retVal = new ArrayList<>();
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();
        Stack<List<Integer>> pathStack = new Stack<>();
        nodeStack.push(root);
        sumStack.push(root.val);
        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        pathStack.push(list);

        while (!nodeStack.isEmpty()) {
            TreeNode currentTop = nodeStack.pop();
            int currentSum = sumStack.pop();
            List<Integer> currentPath = pathStack.pop();
            if (currentTop.left == null && currentTop.right == null && currentSum == sum) {
                retVal.add(currentPath);
            }
            if (currentTop.left != null) {
                nodeStack.push(currentTop.left);
                sumStack.push(currentSum + currentTop.left.val);
                List<Integer> newPath = new ArrayList<>(currentPath);
                newPath.add(0, currentTop.left.val);
                pathStack.push(newPath);
            }

            if (currentTop.right != null) {
                nodeStack.push(currentTop.right);
                sumStack.push(currentSum + currentTop.right.val);
                List<Integer> newPath = new ArrayList<>(currentPath);
                newPath.add(0, currentTop.right.val);
                pathStack.push(newPath);
            }
        }
        return retVal;
    }





    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);

        root.left.left = new TreeNode(11);
        root.right.right = new TreeNode(4);
        root.right.left = new TreeNode(13);

        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);

        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);
        List<List<Integer>> list = new PathSum2().pathSum(root, 22);
        System.out.println(list);
    }

}

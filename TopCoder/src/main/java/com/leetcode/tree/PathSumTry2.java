package com.leetcode.tree;

import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/path-sum-ii/
public class PathSumTry2 {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> results = new LinkedList<>();
        List<Integer> result = new LinkedList<>();
        helper(root, sum, results, result, 0);
        return results;
    }

    private void helper(TreeNode root, int sum,
                        List<List<Integer>> results,
                        List<Integer> result, int currentSum) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            result.add(root.val);

            if (currentSum  + root.val == sum) {
                results.add(new LinkedList<>(result));
            }
            result.remove(result.size() - 1);
            return;
        }
        result.add(root.val);
        helper(root.left, sum, results, result, currentSum + root.val);
        helper(root.right, sum, results, result, currentSum + root.val);
        result.remove(result.size()-1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);

        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);

        root.right = new TreeNode(8);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        root.right.left = new TreeNode(13);
        root.right.right.left = new TreeNode(5);
        List<List<Integer>> result = new PathSumTry2().pathSum(root, 22);
        System.out.println(result);
    }
}

package com.leetcode.tree;

import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/sum-root-to-leaf-numbers/
public class SumRootToLeafNumbers {

    public int sumNumbers(TreeNode root) {
        List<String> numbers = recursive(root);
        return numbers.stream().mapToInt(Integer::parseInt).sum();
    }

    private List<String> recursive(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }

        if (root.left == null && root.right == null) {
            List<String> list = new LinkedList<>();
            list.add(root.val+"");
            return list;
        }

        List<String> lList = recursive(root.left);
        List<String> rList = recursive(root.right);

        List<String> result = new LinkedList<>();

        for (String str : lList) {
            result.add(root.val + str);
        }

        for (String str : rList) {
            result.add(root.val + str);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(9);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(1);

        root.right = new TreeNode(0);
        int res = new SumRootToLeafNumbers().sumNumbers(root);
        System.out.println(res);
    }
}

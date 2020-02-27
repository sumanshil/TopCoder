package com.leetcode;

import java.util.LinkedList;
import java.util.List;

import com.leetcode.tree.TreeNode;

//https://leetcode.com/problems/binary-tree-level-order-traversal/
public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();

        List<TreeNode> parentList = new LinkedList<>();
        List<TreeNode> childList = new LinkedList<>();

        parentList.add(root);

        while (parentList.size() > 0) {
            List<Integer> newList = new LinkedList<>();
            for (TreeNode node : parentList) {
                newList.add(node.val);
                if (node.left != null) {
                    childList.add(node.left);
                }

                if (node.right != null) {
                    childList.add(node.right);
                }

            }
            res.add(newList);
            parentList = childList;
            childList = new LinkedList<>();
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);

        root.right.left  = new TreeNode(15);
        root.right.right = new TreeNode(7);
        List<List<Integer>> results  = new BinaryTreeLevelOrderTraversal().levelOrder(root);

        for (List<Integer> list : results) {
            System.out.println(list);
        }
    }
}

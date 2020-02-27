package com.leetcode;

import java.util.LinkedList;
import java.util.List;

import com.geeksforgeeks.tree.niary.LowestCommonAncestorInBinaryTree;
import com.leetcode.tree.TreeNode;

//https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
public class LowestCommonAncestorOfBinarySearchTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pathA = new LinkedList<>();
        recursive(root, pathA, p);

        List<TreeNode> pathB = new LinkedList<>();
        recursive(root, pathB, q);

        int index1 = 0;
        int index2 = 0;

        TreeNode parent = null;
        while (index1 < pathA.size() && index2 < pathB.size()) {
            if (pathA.get(index1).val == pathB.get(index2).val) {
                parent = pathA.get(index1);
            } else {
                break;
            }
            index1++;
            index2++;
        }
        return parent;


    }

    private void recursive(TreeNode root, List<TreeNode> path, TreeNode p) {
        if (root == null) {
            return;
        }
        if (root.val == p.val) {
            path.add(p);
            return;
        }
        path.add(root);
        if (p.val > root.val) {
            recursive(root.right, path, p);
        } else {
            recursive(root.left, path, p);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);

        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);

        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        TreeNode result = new LowestCommonAncestorOfBinarySearchTree().lowestCommonAncestor(root, root.left, root.left.left);
        System.out.println(result.val);

    }
}

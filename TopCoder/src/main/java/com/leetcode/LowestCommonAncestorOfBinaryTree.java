package com.leetcode;

import com.leetcode.tree.TreeNode;

//https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
public class LowestCommonAncestorOfBinaryTree {

    private static boolean v1 = false;
    private static boolean v2 = false;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        TreeNode lca = recursive(root, p, q);

        if ((v1 && v2) || (v1 && find(lca, q)) || (v1 && find(lca, p))) {
            return lca;
        }
        return null;
    }

    private boolean find(TreeNode root, TreeNode q) {
        if (root == null) {
            return false;
        }

        if (root.val == q.val) {
            return true;
        }
        return find(root.left, q) || find(root.right, q);
    }

    private TreeNode recursive(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        TreeNode tempNode = null;
        if (root.val == p.val) {
            tempNode = root;
            v1 = true;
        }

        if (root.val == q.val) {
            tempNode = root;
            v2 = true;
        }

        TreeNode lNode = recursive(root.left, p, q);
        TreeNode rNode = recursive(root.right, p, q);

        if (tempNode != null) {
            return tempNode;
        }

        if (lNode != null && rNode != null) {
            return root;
        }

        return  lNode != null ? lNode : rNode;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);

        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);

        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        TreeNode result = new LowestCommonAncestorOfBinaryTree().lowestCommonAncestor(root, root.left, root.right);
        System.out.println(result.val);

    }
}

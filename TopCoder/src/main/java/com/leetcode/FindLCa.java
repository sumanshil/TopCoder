package com.leetcode;

import com.leetcode.tree.TreeNode;

public class FindLCa {


    private static boolean v1 = false;
    private static boolean v2 = false;

    public TreeNode findLca(int n1, int n2, TreeNode root) {
        TreeNode lca = findRecursive(n1, n2, root);

        if (v1 && v2 || v1 && find(lca, n2) || v2 && find(lca, n1)) {
            return lca;
        }
        return null;
    }

    private boolean find(TreeNode root, int n1) {
        if (root == null) {
            return false;
        }

        if (root.val == n1) {
            return true;
        }

        boolean lFound = find(root.left, n1);
        boolean rFound = find(root.right, n1);
        return lFound && rFound;
    }

    private TreeNode findRecursive(int n1, int n2, TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tempNode = null;
        if (root.val == n1) {
            tempNode = root;
            v1 = true;
        }

        if (root.val == n2) {
            tempNode = root;
            v2 = true;
        }

        TreeNode lcaLeft = findRecursive(n1, n2, root.left);
        TreeNode lcaRight = findRecursive(n1, n2, root.right);

        if (tempNode != null) {
            return tempNode;
        }

        if (lcaLeft != null && lcaRight != null) {
            return root;
        }
        return lcaLeft != null ? lcaLeft : lcaRight;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        TreeNode result = new FindLCa().findLca(4, 7, root);
        System.out.println(result.val);

    }

}

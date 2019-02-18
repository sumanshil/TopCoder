package com.leetcode.tree;

//https://leetcode.com/problems/trim-a-binary-search-tree/
public class TrimABinarySearchTree1 {

    /*
    public TreeNode trimBST(TreeNode root, int L, int R) {
        TreeNode newRoot = recur(root, L, R);
        return newRoot;
    }
    */

    /*
    private TreeNode recur(TreeNode root, int l, int r) {

        if (root == null) {
            return null;
        }


        TreeNode lNode = recur(root.left, l, r);
        TreeNode rNode = recur(root.right, l, r);

        if (root.val >= l && root.val <= r) {
            root.left = lNode;
            root.right = rNode;
            return root;
        } else {

            if (lNode != null && rNode != null) {
                lNode.right = rNode;
                return lNode;
            } else if (lNode != null) {
                return lNode;
            }
            return rNode;
        }
    }
    */

    public TreeNode trimBST(TreeNode root, int L, int R) {
        if(root == null) return null;

        TreeNode left =  trimBST(root.left, L, R);
        TreeNode right = trimBST(root.right, L, R);

        if(root.val < L) return right;
        if(root.val > R) return left;

        root.left = left;
        root.right = right;
        return root;

    }

    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(3);
        //TreeNode node0 = new TreeNode(0);
        TreeNode node4 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);

        node3.left = node1;
        node3.right = node4;

        node1.right = node2;

        TreeNode result = new TrimABinarySearchTree1().trimBST(node3, 1, 2);
        System.out.println(result);
    }

}

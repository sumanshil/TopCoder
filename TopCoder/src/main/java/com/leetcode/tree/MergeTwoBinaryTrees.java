package com.leetcode.tree;

//https://leetcode.com/problems/merge-two-binary-trees/description/
public class MergeTwoBinaryTrees {

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        TreeNode merged = recursive(t1, t2);
        return merged;
    }

    private TreeNode recursive(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }

        int r1 = t1 == null? 0 : t1.val;
        int r2 = t2 == null? 0 : t2.val;
        TreeNode root = new TreeNode(r1 + r2);
        root.left = recursive(t1 == null ? null : t1.left, t2 == null ? null : t2.left);
        root.right = recursive(t1 == null? null : t1.right, t2 == null ? null :t2.right);
        return root;
    }

    public static void main(String[] args) {

    }

}

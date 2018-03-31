package com.leetcode.tree;

import java.util.Stack;

public class SameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
       boolean result = iterative(p, q);
       return result;
    }

    private boolean iterative(TreeNode p, TreeNode q) {
        Stack<TreeNode> stackP = new Stack<>();
        Stack<TreeNode> stackQ = new Stack<>();
        while ((p != null || !stackP.isEmpty()) || (q != null || !stackQ.isEmpty())  ) {
            if ((p == null && q != null) || (p != null && q == null)) {
                return false;
            }
            while (p != null) {
                stackP.push(p);
                p = p.left;
            }

            while (q != null) {
                stackQ.push(q);
                q = q.left;
            }
            TreeNode pTop = stackP.pop();
            TreeNode qTop = stackQ.pop();
            if (pTop.val != qTop.val) {
                return false;
            }
            p = pTop.right;
            q = qTop.right;
        }
        return true;
    }

    private String serialize(TreeNode p) {
        if ( p == null ) {
            return "";
        }

        String lStr = serialize(p.left);
        String rStr = serialize(p.right);
        return "("+lStr+")"+p.val+"("+rStr+")";
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        boolean result = new SameTree().isSameTree(root1, root2);
        System.out.println(result);
    }

}

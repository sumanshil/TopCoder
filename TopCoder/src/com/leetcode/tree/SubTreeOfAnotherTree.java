package com.leetcode.tree;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/subtree-of-another-tree/description/
public class SubTreeOfAnotherTree {

    private Set<String> set = new HashSet<>();
    public boolean isSubtree(TreeNode s, TreeNode t) {
        recursive(s, true);
        String str = recursive(t, false);
        return set.contains(str);
    }

    private String recursive(TreeNode s, boolean storeInMap) {
        if (s == null){
            return "";
        }
        String leftStr = recursive(s.left, storeInMap);
        String rightStr = recursive(s.right, storeInMap);
        String finalStr = "("+leftStr+")"+s.val+"("+rightStr+")";
        if (storeInMap) {
            set.add(finalStr);
        }
        return finalStr;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode( 1);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(0);

        TreeNode root1  = new TreeNode(4);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(2);

        boolean result = new SubTreeOfAnotherTree().isSubtree(root, root1);
        System.out.println(result);
    }

}

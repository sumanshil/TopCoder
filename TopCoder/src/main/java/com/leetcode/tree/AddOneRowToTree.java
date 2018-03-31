package com.leetcode.tree;

//https://leetcode.com/problems/add-one-row-to-tree/description/
public class AddOneRowToTree {


    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode newNode = new TreeNode(v);
            newNode.left = root;
            return newNode;
        } else {
            recursive(root, v, 0, d-1);
        }
        return root;
    }

    private TreeNode recursive(TreeNode root,
                           int v,
                           int currentLevel,
                           int maxLevel) {
        if (root == null) {
            return null;
        }
        if (currentLevel == maxLevel){
            TreeNode lTemp = root.left;
            TreeNode rTemp = root.right;

            TreeNode newLeft = new TreeNode(v);
            TreeNode newRight = new TreeNode(v);

            newLeft.left = lTemp;
            newRight.right = rTemp;

            root.left = newLeft;
            root.right = newRight;

            return root;

        }
        root.left = recursive(root.left, v, currentLevel + 1, maxLevel);
        root.right = recursive(root.right, v, currentLevel + 1, maxLevel);
        return root;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(1);

        root.right.left = new TreeNode(5);
        new AddOneRowToTree().addOneRow(root, 1, 2);
    }

}

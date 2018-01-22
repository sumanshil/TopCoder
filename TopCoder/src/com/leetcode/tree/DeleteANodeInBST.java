package com.leetcode.tree;

//https://leetcode.com/problems/delete-node-in-a-bst/description/
public class DeleteANodeInBST {

    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode root1 = recursiveDelete(root, key);
        return root1;
    }

    private TreeNode recursiveDelete(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            if (root.left != null && root.right == null) {
                return root.left;
            } else if (root.right != null && root.left == null) {
                return root.right;
            } else if (root.left == null && root.right == null) {
                return  null;
            } else {
                TreeNode leftRightNode = root.left.right;
                TreeNode rightLeftNode = root.right;
                while (rightLeftNode.left != null) {
                    rightLeftNode = rightLeftNode.left;
                }
                rightLeftNode.left = leftRightNode;
                root.left.right = root.right;
                return root.left;
            }
        }
        if (key > root.val) {
            root.right = recursiveDelete(root.right, key);
        } else {
            root.left = recursiveDelete(root.left, key);
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);

        root.right.right = new TreeNode(7);
        new DeleteANodeInBST().deleteNode(root, 3);
    }

}

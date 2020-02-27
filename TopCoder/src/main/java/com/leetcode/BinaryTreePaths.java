package com.leetcode;

import java.util.LinkedList;
import java.util.List;

import com.leetcode.tree.TreeNode;

//https://leetcode.com/problems/binary-tree-paths/
public class BinaryTreePaths {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new LinkedList<>();
        recursive(result, root, new LinkedList<>());
        return result;
    }

    private void recursive(List<String> result, TreeNode root, List<String> path) {

        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            path.add(root.val+"");
            result.add(String.join("=>", path));
            path.remove(path.size() - 1);
            return;
        }
        path.add(root.val+"");
        recursive(result, root.left, path);
        recursive(result, root.right, path);
        path.remove(path.size()-1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        List<String> result = new BinaryTreePaths().binaryTreePaths(root);
        System.out.println(result);
    }
}

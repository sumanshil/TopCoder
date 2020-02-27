package com.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/unique-binary-search-trees-ii/discuss/310010/Java-Recursive-Solution-Explained
public class GenerateBinarySearchTrees {

    public List<TreeNode> generateTrees(int n) {
        if ( n == 0) {
            return new ArrayList<>();
        }
        return recursive(1, n);
    }

    private List<TreeNode> recursive(int start, int end) {
        if (start > end) {
            List<TreeNode> list = new LinkedList<>();
            list.add(null);
            return list;
        }

        if (start == end) {
            List<TreeNode> list = new LinkedList<>();
            list.add(new TreeNode(start));
            return list;
        }
        List<TreeNode> currentList = new LinkedList<>();
        for (int i = start ; i <= end ; i++) {
            List<TreeNode> leftList = recursive(start, i-1);
            List<TreeNode> rightList = recursive(i+1, end);

            for (TreeNode lNode : leftList) {
                for (TreeNode rNode : rightList) {
                    TreeNode root = new TreeNode(i);
                    root.left = lNode;
                    root.right = rNode;
                    currentList.add(root);
                }
            }
        }
        return currentList;
    }

    public static void main(String[] args) {
        List<TreeNode> list = new GenerateBinarySearchTrees().generateTrees(3);
        for (TreeNode node : list) {
            System.out.println(node);
        }
    }

}

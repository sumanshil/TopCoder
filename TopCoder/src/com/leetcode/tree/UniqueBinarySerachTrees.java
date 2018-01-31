package com.leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UniqueBinarySerachTrees {
    /*
    public List<TreeNode> generateTrees(int n) {

        List<TreeNode> retVal = new ArrayList<>();
        for ( int i = 1 ; i <= n ; i++){
            List<TreeNode> lList = recursive(1, i-1);
            List<TreeNode> rList = recursive(i+1, n);
            if (lList.size() == 0 && rList.size() == 0){
                retVal.add(new TreeNode(i));
            } else if (lList.size() == 0){
                for (TreeNode r : rList){
                    TreeNode root = new TreeNode(i);
                    root.right = r;
                    retVal.add(root);
                }
            } else if (rList.size() == 0){
                for (TreeNode l : lList){
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    retVal.add(root);
                }
            } else {
                for (TreeNode l : lList){
                    for (TreeNode r : rList) {
                        TreeNode root = new TreeNode(i);
                        root.left = l;
                        root.right = r;
                        retVal.add(root);
                    }
                }
            }
        }

        return retVal;
    }


    private List<TreeNode> recursive(int low, int high){
        if (high < low){
            return Collections.EMPTY_LIST;
        }
        if (high == low){
            TreeNode root = new TreeNode(low);
            List<TreeNode> list = new ArrayList<>();
            list.add(root);
            return list;
        }
        List<TreeNode> retVal = new ArrayList<>();
        for (int i = low ; i <= high ; i++ ){
            List<TreeNode> leftNodes = recursive(low, i-1);
            List<TreeNode> rightNodes = recursive(i+1, high);
            if (leftNodes.size() == 0){
                for (TreeNode node : rightNodes){
                    TreeNode root = new TreeNode(i);
                    root.right = node;
                    retVal.add(root);
                }
            } else if (rightNodes.size() == 0){
                for (TreeNode node : leftNodes){
                    TreeNode root = new TreeNode(i);
                    root.left = node;
                    retVal.add(root);
                }
            } else {
                for (TreeNode l : leftNodes) {
                    for (TreeNode r : rightNodes) {
                        TreeNode root = new TreeNode(i);
                        root.right = r;
                        root.left = l;
                        retVal.add(root);
                    }
                }
            }
        }
        return retVal;
    }

    */
    public static List<TreeNode> generateTrees(int n) {
        List<TreeNode>[] result = new List[n + 1];
        result[0] = new ArrayList<>();
        if (n == 0) {
            return result[0];
        }

        result[0].add(null);
        for (int len = 1; len <= n; len++) {
            result[len] = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                for (TreeNode nodeL : result[j]) {
                    for (TreeNode nodeR : result[len - j - 1]) {
                        TreeNode node = new TreeNode(j + 1);
                        node.left = nodeL;
                        node.right = clone(nodeR, j + 1);
                        result[len].add(node);
                    }
                }
            }
        }
        return result[n];
    }

    private static TreeNode clone(TreeNode n, int offset) {
        if (n == null) {
            return null;
        }
        TreeNode node = new TreeNode(n.val + offset);
        node.left = clone(n.left, offset);
        node.right = clone(n.right, offset);
        return node;
    }

    public static void main(String[] args) {
        List<TreeNode> list = new UniqueBinarySerachTrees()
                .generateTrees(3);
        for (TreeNode node : list) {
            System.out.println(node);
        }
    }
}

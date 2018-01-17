package com.leetcode.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


//https://leetcode.com/problems/find-duplicate-subtrees/description/
public class FindDuplicateSubTrees {

    private Map<String, List<TreeNode>> map = new HashMap<>();
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        recursive(root);
        List<TreeNode> list = map.entrySet().stream()
                .filter( e -> e.getValue().size() > 1)
                .map(e -> e.getValue().get(0))
                .collect(Collectors.toList());
        return list;
    }

    private String recursive(TreeNode root) {
        if (root == null) {
            return  "";
        }
        String str = "("+recursive(root.left) + root.val + recursive(root.right)+")";
        if (map.containsKey(str)) {
           map.get(str).add(root);
        } else {
            List<TreeNode> list = new LinkedList<>();
            list.add(root);
            map.put(str, list);
        }
        return str;
    }
    /*
    private Map<Integer, List<TreeNode>> map = new HashMap<>();
    private List<TreeNode> list = new LinkedList<>();
    private Set<TreeNode> set ;


    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        set = new TreeSet<>((e1, e2) -> isSame(e1, e2) ? 0 : 1);
        recursive(root);
        return list;
    }

    private void recursive(TreeNode root) {
        if (root == null) {
            return;
        }
        if (map.containsKey(root.val) && !setContains(root)) {
        //if (map.containsKey(root.val)) {
            List<TreeNode> list = map.get(root.val);
            boolean isFound = false;
            for (TreeNode treeNode : list) {
                if (isSame(root, treeNode)) {
                    isFound = true;
                    set.add(root);
                    break;
                }
            }
            if (isFound) {
                this.list.add(root);
            } else {
                map.get(root.val).add(root);
            }
            recursive(root.left);
            recursive(root.right);
        } else {
            List<TreeNode> list = new LinkedList<>();
            list.add(root);
            map.put(root.val, list);
            recursive(root.left);
            recursive(root.right);
        }

    }

    private boolean setContains(TreeNode root) {
        for (TreeNode tNode : this.set) {
            if (isSame(tNode, root)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSame(TreeNode root1, TreeNode root2){
        if (root1 != null && root2 == null) {
            return false;
        }
        if (root1 == null && root2 != null) {
            return false;
        }

        if (root1 == null && root2 == null) {
            return true;
        }

        return root1.val == root2.val &&
               (isSame(root1.left, root2.left)
               && isSame(root1.right, root2.right));
    }

    */

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//
//        root.left.left = new TreeNode(4);
//
//        root.right.left = new TreeNode(2);
//        root.right.right = new TreeNode(4);
//
//        root.right.left.left = new TreeNode(4);
//        TreeNode root = new TreeNode(2);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(2);
//
//        root.left.left = new TreeNode(3);
//        root.right.left = new TreeNode(3);

        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.right = new TreeNode(0);

        root.left.left = new TreeNode(0);
        root.right.left = new TreeNode(0);

        root.right.left.left = new TreeNode(0);
        root.right.left.right = new TreeNode(0);

        root.left.left.left = new TreeNode(0);
        root.left.left.right = new TreeNode(0);

        List<TreeNode> list = new FindDuplicateSubTrees().findDuplicateSubtrees(root);
        System.out.println(list);
    }

}

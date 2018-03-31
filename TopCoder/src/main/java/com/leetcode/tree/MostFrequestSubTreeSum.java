package com.leetcode.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//https://leetcode.com/problems/most-frequent-subtree-sum/description/
public class MostFrequestSubTreeSum {

    private Map<Integer, Integer> map = new HashMap<>();
    private int max = Integer.MIN_VALUE;

    public int[] findFrequentTreeSum(TreeNode root) {
        recursive(root);
        Integer[] arr = map.entrySet().stream()
                .filter(e -> e.getValue() == max)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList())
                .toArray(new Integer[0]);
        int[] arr1 = new int[arr.length];
        int index = 0;
        for (Integer i : arr){
            arr1[index++] = i;
        }
        return  arr1;
    }

    private int recursive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lSum = recursive(root.left);
        int rSum = recursive(root.right);
        int total = lSum + root.val + rSum;
        if (!map.containsKey(total)) {
            map.put(total, 1);
            max =  Math.max(max,1);
        } else {
            int count = map.get(total);
            map.put(total, count +1 );
            max = Math.max(max, count + 1);
        }
        return total;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(6);
        new MostFrequestSubTreeSum().findFrequentTreeSum(root);
    }

}

package com.leetcode.tree;


import java.util.Map;

//https://leetcode.com/problems/house-robber-iii/description/
public class HouseRobber {

    public int rob(TreeNode root) {
//        Map<TreeNode, Integer> map = new HashMap<>();
//        int result = recursiveSum(root, map);
//        return result;
        int[] arr = findSumDP(root);
        return Math.max(arr[0], arr[1]);
    }

    public int[] findSumDP(TreeNode root){
        if (root == null) {
            return new int[2];
        }
        int lArr[] = findSumDP(root.left);
        int rArr[] = findSumDP(root.right);
        int sum1 = Math.max(lArr[0], lArr[1]) + Math.max(rArr[0], rArr[1]);
        int sum2 = root.val + lArr[0] + rArr[0];
        int[] retArr = {sum1, sum2};
        return retArr;
    }

    private int recursiveSum(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null) {
            return 0;
        }
        if (map.containsKey(root)){
            return map.get(root);
        }
        int grandChildSum = 0;
        int greatGrandChildSum = 0;
        if (root.left != null) {
            grandChildSum += recursiveSum(root.left.left, map)
                            + recursiveSum(root.left.right, map);
        }
        if (root.right != null) {
            grandChildSum += recursiveSum(root.right.left, map)
                             + recursiveSum(root.right.right, map);
        }

        if (root.left != null && root.left.left != null) {
            greatGrandChildSum = recursiveSum(root.left.left.left, map)
                                 + recursiveSum(root.left.left.right, map);
        }

        if (root.left != null &&  root.left.right != null) {
            greatGrandChildSum = recursiveSum(root.left.right.left, map)
                                 + recursiveSum(root.left.right.right, map);
        }

        if (root.right != null && root.right.left != null) {
            greatGrandChildSum = recursiveSum(root.right.left.left, map)
                                 + recursiveSum(root.right.left.right, map);
        }

        if (root.right != null &&  root.right.right != null) {
            greatGrandChildSum = recursiveSum(root.right.right.left, map)
                                 + recursiveSum(root.right.right.right, map);
        }

        int max = root.val+Math.max(grandChildSum, greatGrandChildSum);
        int result =  Math.max(max, recursiveSum(root.left, map) + recursiveSum(root.right, map));
        map.put(root, result);
        return result;
    }


    public static void main(String[] args) {
//        TreeNode root = new TreeNode(3);
//        root.left = new TreeNode(4);
//        root.right = new TreeNode(5);
//
//        root.left.left = new TreeNode(1);
//        root.left.right = new TreeNode(3);
//
//        root.right.right = new TreeNode(1);
//        int res = new HouseRobber().rob(root);
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        int res = new HouseRobber().rob(root);
        System.out.println(res);
    }

}

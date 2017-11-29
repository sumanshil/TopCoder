package com.geeksforgeeks.tree;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    public List<TreeNode> generateTrees(int n) {
        if (n == 0){
            return new ArrayList<>();
        }
        return generateTrees(1,n);

    }
    private List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> result=new ArrayList<>();
        if(start>end)
        {
            result.add(null);
            return result;
        }
        if(start == end){
            result.add(new TreeNode(start));
            return result;
        }
        List<TreeNode> left,right;
        for(int i=start;i<=end;i++)
        {

            left = generateTrees(start, i-1);
            right = generateTrees(i+1,end);
            for(TreeNode lnode: left)
            {
                for(TreeNode rnode: right)
                {
                    TreeNode root = new TreeNode(i);
                    root.left = lnode;
                    root.right = rnode;
                    result.add(root);

                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<TreeNode> list =  new Solution().generateTrees(3);
        System.out.println(list.size());
    }
}
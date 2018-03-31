package com.leetcode.tree;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

//https://leetcode.com/problems/construct-string-from-binary-tree/description/
public class ConstructStringFromBinaryTree {

    public String tree2str(TreeNode t) {
        String str = iterative(t);
        return str.substring(1, str.length()-1);
    }

    private String recursive(TreeNode t) {
        if (t == null) {
            return "";
        }

        String str = "("+recursive(t.left)+ ")" + "("+recursive(t.right)+")";
        if ("()()".equals(str)){
            return t.val+"";
        } else if (str.endsWith("()")){
            String subStr = str.substring(0, str.length()-2);
            return t.val+subStr;
        } else {
            return t.val + str;
        }
    }

    private String iterative(TreeNode treeNode) {
        Set<TreeNode> visited = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(treeNode);
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node == null){
                stringBuilder.append("()");
                stack.pop();
            } else if (!visited.contains(node)) {
                stringBuilder.append("("+node.val);
                visited.add(node);
                if (node.right != null) {
                    stack.push(node.right);
                    stack.push(node.left);
                }
            } else {
                stack.pop();
                stringBuilder.append(")");
            }
        }
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }


    /*
    public String tree2str(TreeNode t) {
        if (t == null)
            return "";
        Stack < TreeNode > stack = new Stack < > ();
        stack.push(t);
        Set < TreeNode > visited = new HashSet < > ();
        StringBuilder s = new StringBuilder();
        while (!stack.isEmpty()) {
            t = stack.peek();
            if (visited.contains(t)) {
                stack.pop();
                s.append(")");
            } else {
                visited.add(t);
                s.append("(" + t.val);
                if (t.left == null && t.right != null)
                    s.append("()");
                if (t.right != null)
                    stack.push(t.right);
                if (t.left != null)
                    stack.push(t.left);
            }
        }
        return s.substring(1, s.length() - 1);
    }

*/
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        String str = new ConstructStringFromBinaryTree().tree2str(root);
        System.out.println(str);

    }

}

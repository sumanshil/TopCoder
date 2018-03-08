package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/
public class SerializeAndDeserializeIterative {

    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        String str = convertToString(root);
        return str;
    }

    private String convertToString(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            if (node != null) {
                stringBuilder.append(node.val+",");
                queue.add(node.left);
                queue.add(node.right);
            } else {
                stringBuilder.append("NULL,");
            }
        }
        String string = stringBuilder.toString();
        return string.substring(0, string.length()-1);
    }

    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        String[] arr = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        queue.add(root);
        int arrIndex = 1;
        while (!queue.isEmpty()) {
            TreeNode current = queue.remove();
            if (current != null) {
                if (arrIndex < arr.length) {
                    if (!arr[arrIndex].equals("NULL")) {
                        current.left = new TreeNode(Integer.parseInt(arr[arrIndex]));
                        queue.add(current.left);
                    } else {
                        queue.add(null);
                    }
                    arrIndex++;
                    if (arrIndex < arr.length) {
                        if (!arr[arrIndex].equals("NULL")) {
                            current.right = new TreeNode(Integer.parseInt(arr[arrIndex]));
                            queue.add(current.right);
                        } else {
                            queue.add(null);
                        }
                    }
                    arrIndex++;
                }
            }
        }
        return root;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        String str = new SerializeAndDeserializeIterative().serialize(root);
        TreeNode node1 = new SerializeAndDeserializeIterative().deserialize(str);
        System.out.println(node1);
    }
}
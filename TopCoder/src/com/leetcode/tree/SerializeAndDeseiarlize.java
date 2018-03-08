package com.leetcode.tree;

//https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/
public class SerializeAndDeseiarlize {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        String str = recursiveSerialize(root);
        return str;
    }

    private String recursiveSerialize(TreeNode root) {
        if (root == null) {
            return null;
        }

        String leftStr = recursiveSerialize(root.left);
        String rightStr = recursiveSerialize(root.right);

        return root.val + "("+leftStr+")("+rightStr+")";
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0 || data.startsWith("(null)")) {
            return null;
        }
        ParserInfo rootData ;
        if (data.startsWith("(")) {
            data = data.substring(1, data.length()-1);
        }
        rootData = parseData(data);

        int leftStrEnds = parseLeft(rootData.leftStrStart , data);
        String left = data.substring(rootData.leftStrStart , leftStrEnds + 1);
        String right = data.substring(leftStrEnds+1);
        TreeNode root = new TreeNode(Integer.parseInt(rootData.data));
        root.left = deserialize(left);
        root.right = deserialize(right);
        return root;
    }

    private int parseLeft(int index, String str) {
        int count = 0;
        for (int i = index ; i < str.length() ; i++) {
            if (str.charAt(i) == '(') {
                count++;
            }
            if (str.charAt(i) == ')') {
                count--;
            }
            if (count == 0) {
                return i;
            }
        }
        return -1;
    }

    private ParserInfo parseData(String data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0 ; i < data.length() ; i++) {
            if (data.charAt(i) == '(') {
                if (i == 0) {
                    continue;
                }
                return new ParserInfo(stringBuilder.toString(), i);
            }
            stringBuilder.append(data.charAt(i)+"");
        }
        return null;
    }

    class ParserInfo {
        String data;
        int leftStrStart;
        public ParserInfo(String data, int index) {
            this.data = data;
            this.leftStrStart = index;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        String str = new SerializeAndDeseiarlize().serialize(root);
        TreeNode treeNode = new SerializeAndDeseiarlize().deserialize(str);
        System.out.println(treeNode);
    }
}

package com.leetcode.tree;

//https://leetcode.com/problems/serialize-and-deserialize-bst/description/
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        String serialized = recursive(root);
        return "("+serialized+")";
    }

    private String recursive(TreeNode root) {
        if (root == null) {
            return "";
        }
        String leftStr = recursive(root.left);
        String rightStr = recursive(root.right);
        return "("+leftStr+")"+root.val+"("+rightStr+")";
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        TreeNode deserialized = recursiveDeserialize(data);
        return deserialized;
    }

    private TreeNode recursiveDeserialize(String data) {
        if (data == null || data.length() == 0 || "()".equals(data)) {
            return null;
        }
        String trimmed = data.substring(1, data.length()-1);
        int leftStrIndex = getIndex(trimmed);
        String leftStr = trimmed.substring(0,leftStrIndex+1);
        int nextOpenBraceIndex = getNextOpenBrace(trimmed, leftStrIndex+1);
        String rightStr = trimmed.substring(nextOpenBraceIndex);
        String rootData = trimmed.substring(leftStrIndex+1, nextOpenBraceIndex)+"";
        TreeNode root = new TreeNode(Integer.parseInt(rootData));
        root.left = recursiveDeserialize(leftStr);
        root.right = recursiveDeserialize(rightStr);
        return root;
    }

    private int getNextOpenBrace(String trimmed, int index) {
        for ( int i = index ; i < trimmed.length() ; i++) {
            if (trimmed.charAt(i) == '(') {
                return i;
            }
        }
        return -1;
    }

    private int getIndex(String data) {
        int count = 0;

        for (int i = 0 ; i < data.length() ; i++) {
            if (data.charAt(i) == ')'){
                count--;
                if (count == 0) {
                    return i;
                }
            } else if (data.charAt(i) == '('){
                count++;
            }
        }
        return -1;
    }
    private static String dnsStr ="{\n"
                           + "    \"dns\": {\n"
                           + "        \"data\": [\n"
                           + "            \"Server:\",\n"
                           + "            \"127.0.0.1\",\n"
                           + "            \"Address:\",\n"
                           + "            \"127.0.0.1#53\",\n"
                           + "            \"Non-authoritative answer:\",\n"
                           + "            \"Name:\",\n"
                           + "            \"sc2-rdops-vm07-dhcp-250-102.eng.vmware.com\",\n"
                           + "            \"Address: 10.192.250.102\"\n"
                           + "        ],\n"
                           + "        \"resolved_address\": \"10.192.250.102\",\n"
                           + "        \"status\": \"SUCCESS\"\n"
                           + "    }\n";
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        Codec codec = new Codec();
        TreeNode treeNode = codec.deserialize(codec.serialize(root));
        System.out.println(treeNode);
        System.out.println(dnsStr);
    }
}

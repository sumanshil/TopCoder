package com.leetcode.tree;

import com.leetcode.array.leetcode.RotateList;

//https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
public class ConvertSortedListToBst {

    public TreeNode sortedListToBST(RotateList.ListNode head) {
        TreeNode node = helper(head);
        return node;
    }

    private TreeNode helper(RotateList.ListNode head) {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return new TreeNode(head.val);
        }

        RotateList.ListNode midNode =  getMid(head);
        TreeNode root = new TreeNode(midNode.val);
        root.left = helper(head);
        root.right = helper(midNode.next);
        return root;
    }

    private RotateList.ListNode getMid(RotateList.ListNode head) {
        RotateList.ListNode slow = head;
        RotateList.ListNode fast = head;
        RotateList.ListNode pre = null;

        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        return slow;
    }

    public static void main(String[] args) {
        RotateList.ListNode head = new RotateList.ListNode(-10);
        head.next = new RotateList.ListNode(-3);
        head.next.next = new RotateList.ListNode(0);
        head.next.next.next = new RotateList.ListNode(5);
        head.next.next.next.next = new RotateList.ListNode(9);
        TreeNode rootNode = new ConvertSortedListToBst().sortedListToBST(head);
        System.out.println(rootNode);
    }
}

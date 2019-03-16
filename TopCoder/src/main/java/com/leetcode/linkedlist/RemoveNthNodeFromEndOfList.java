package com.leetcode.linkedlist;

//https://leetcode.com/problems/remove-nth-node-from-end-of-list/
public class RemoveNthNodeFromEndOfList {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode start = head;
        ListNode end = head;
        int count = 0;

        while (count < n) {
            end = end.next;
            count++;
            if (end == null) {
                break;
            }
        }

        if (count < n) {
            return start;
        }


        while (end != null && end.next != null) {
            start = start.next;
            end = end.next;
        }

        start.next = start.next.next;
        return head;

    }

    public static void main(String[] args) {
        ListNode start = new ListNode(1);
        start.next = new ListNode(2);
        start.next.next = new ListNode(3);
        start.next.next.next = new ListNode(4);
        start.next.next.next.next = new ListNode(5);
        ListNode result = new RemoveNthNodeFromEndOfList().removeNthFromEnd(start, 2);
        System.out.println(result.val);
    }
}

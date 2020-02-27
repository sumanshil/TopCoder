package com.leetcode;


public class ReverseList {

    public SwapNodesInPair.ListNode reverseList(SwapNodesInPair.ListNode head) {

        SwapNodesInPair.ListNode prev = null;

        SwapNodesInPair.ListNode current = head;


        while (current != null) {
            SwapNodesInPair.ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }


    public SwapNodesInPair.ListNode reverse(SwapNodesInPair.ListNode head) {
        if (head.next == null) {
            return head;
        }

        SwapNodesInPair.ListNode next = head.next;
        SwapNodesInPair.ListNode current = head;

        SwapNodesInPair.ListNode head1 = reverse(head.next);

        next.next = current;
        current.next = null;
        return head1;

    }

    public static void main(String[] args) {
        SwapNodesInPair.ListNode head = new SwapNodesInPair.ListNode(1);
        head.next = new SwapNodesInPair.ListNode(2);
        head.next.next = new SwapNodesInPair.ListNode(3);
        head.next.next.next = new SwapNodesInPair.ListNode(4);
        head.next.next.next.next = new SwapNodesInPair.ListNode(5);
        SwapNodesInPair.ListNode newNode = new ReverseList().reverse(head);
        System.out.println(newNode);
    }
}

package com.leetcode;

//https://leetcode.com/problems/reverse-nodes-in-k-group/
public class ReverseNodesInKgroup {

    SwapNodesInPair.ListNode nextStart = null;
    public SwapNodesInPair.ListNode reverseKGroup(SwapNodesInPair.ListNode head, int k) {
        SwapNodesInPair.ListNode tail = head;

        for ( int i = 0 ; i < k - 1 ; i++) {
            tail = tail.next;
            if (tail == null) {
                return head;
            }
        }

        SwapNodesInPair.ListNode next = tail.next;
        tail.next = null;
        reverse(head);
        head.next = reverseKGroup(next, k);
        return tail;
    }

    private SwapNodesInPair.ListNode reverse(SwapNodesInPair.ListNode head) {
        SwapNodesInPair.ListNode prev = null;
        SwapNodesInPair.ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;

    }



    public static void main(String[] args) {
        SwapNodesInPair.ListNode node0 = new SwapNodesInPair.ListNode(1);
        node0.next = new SwapNodesInPair.ListNode(2);
        node0.next.next = new SwapNodesInPair.ListNode(3);
        node0.next.next.next = new SwapNodesInPair.ListNode(4);
//
        node0.next.next.next.next = new SwapNodesInPair.ListNode(5);
//        node0.next.next.next.next.next = new SwapNodesInPair.ListNode(6);
//        node0.next.next.next.next.next.next = new SwapNodesInPair.ListNode(7);
//        node0.next.next.next.next.next.next.next = new SwapNodesInPair.ListNode(8);
//        node0.next.next.next.next.next.next.next.next = new SwapNodesInPair.ListNode(9);

        SwapNodesInPair.ListNode newHead = new ReverseNodesInKgroup().reverseKGroup(node0, 2);
        while(newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }

    }
}

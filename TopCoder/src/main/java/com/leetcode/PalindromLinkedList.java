package com.leetcode;


//https://leetcode.com/problems/palindrome-linked-list/
public class PalindromLinkedList {

    public boolean isPalindrome(SwapNodesInPair.ListNode head) {
        if (head.next == null) {
            return true;
        }
        SwapNodesInPair.ListNode slow = head;
        SwapNodesInPair.ListNode fast = head;

        SwapNodesInPair.ListNode secondHalfEnds = slow;
        while (slow != null && fast != null) {
            secondHalfEnds = slow;
            if (fast.next == null){
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        SwapNodesInPair.ListNode secondPartStarts = reverseList(secondHalfEnds);

        while (head != null && secondPartStarts != null) {
            if (head.val  != secondPartStarts.val ) {
                return false;
            }
            if (head.next == secondPartStarts || secondPartStarts.next == head) {
                return true;
            }
            head = head.next;
            secondPartStarts = secondPartStarts.next;
        }
        return true;
    }

    private SwapNodesInPair.ListNode reverseList(SwapNodesInPair.ListNode node) {
        if (node.next == null) {
            return node;
        }

        SwapNodesInPair.ListNode current = node;
        SwapNodesInPair.ListNode next = node.next;
        SwapNodesInPair.ListNode head = reverseList(next);

        next.next = current;
        current.next = null;
        return head;
    }

    public static void main(String[] args) {
        SwapNodesInPair.ListNode head = new SwapNodesInPair.ListNode(1);
        head.next = new SwapNodesInPair.ListNode(2);
        head.next.next = new SwapNodesInPair.ListNode(3);
        head.next.next.next = new SwapNodesInPair.ListNode(2);
        head.next.next.next.next = new SwapNodesInPair.ListNode(1);

        boolean result = new PalindromLinkedList().isPalindrome(head);
        System.out.println(result);

    }
}

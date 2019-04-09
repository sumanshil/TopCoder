package com.leetcode;

//https://leetcode.com/problems/swap-nodes-in-pairs/
public class SwapNodesInPair {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode swapPairs(ListNode head) {

        ListNode newHead = null;
        ListNode current = head;
        ListNode prevNode = null;
        while (current != null) {
            if (current.next == null) {
                if (newHead == null) {
                    newHead = current;
                }
                break;
            }

            ListNode currNext = current.next;
            current.next = currNext.next;
            currNext.next = current;
            if (newHead == null) {
                newHead = currNext;
            }
            if (prevNode != null) {
                prevNode.next = currNext;
            }
            prevNode = current;
            current = current.next;

        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(4);

        ListNode result = new SwapNodesInPair().swapPairs(head);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

}

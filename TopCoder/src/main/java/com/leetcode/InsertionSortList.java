package com.leetcode;

//https://leetcode.com/problems/insertion-sort-list/
public class InsertionSortList {

    static public class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
    }

    public ListNode insertionSortList(ListNode head) {
        ListNode headDummy = new ListNode(Integer.MIN_VALUE);

        headDummy.next = head;

        ListNode prev = headDummy;
        ListNode current = headDummy.next;

        while (current != null) {
            if (current.val < prev.val) {
                prev.next = current.next;
                current.next = null;
                appendInRightPosition(headDummy, current);
                current = prev.next;
            } else {
                prev = current;
                current = current.next;
            }
        }
        return headDummy.next;
    }

    private void appendInRightPosition(ListNode headDummy, ListNode current) {
        ListNode currentHead = headDummy;
        while (currentHead != null) {
            if (currentHead.next != null && currentHead.next.val > current.val) {
                current.next = currentHead.next;
                currentHead.next = current;
                return;
            }
            currentHead = currentHead.next;

        }
    }

    public static void main(String[] args) {
        /*
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        */
        ListNode head = new ListNode(-1);
        head.next = new ListNode(5);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(0);

        ListNode head1 = new InsertionSortList().insertionSortList(head);
        System.out.println(head1);
    }
}

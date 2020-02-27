package com.leetcode.linkedlist;

//https://leetcode.com/problems/sort-list/discuss/409256/Clean-and-organized-python-code
public class SortedList {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode mid = split(head);
        ListNode firstHalf = sortList(head);
        ListNode secondHalf = sortList(mid);
        return merge(firstHalf, secondHalf);
    }

    private ListNode merge(ListNode firstHalf, ListNode secondHalf) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        while (firstHalf != null && secondHalf != null) {
            if (firstHalf.val < secondHalf.val) {
               current.next = firstHalf;
               firstHalf = firstHalf.next;
               current = current.next;
            } else {
                current.next = secondHalf;
                secondHalf = secondHalf.next;
                current = current.next;
            }
            current.next = null;
        }

        if (firstHalf != null) {
            current.next = firstHalf;

        }

        if (secondHalf != null) {
            current.next = secondHalf;
        }
        return dummy.next;
    }

    private ListNode split(ListNode head) {
        ListNode slow = head;
        ListNode fast = slow;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            if (fast == null || fast.next == null) {
                ListNode temp = slow.next;
                slow.next = null;
                return temp;
            } else {
                slow = slow.next;
            }
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        ListNode sorted = new SortedList().sortList(head);

        while (sorted != null) {
            System.out.println(sorted.val);
            sorted = sorted.next;
        }
    }
}

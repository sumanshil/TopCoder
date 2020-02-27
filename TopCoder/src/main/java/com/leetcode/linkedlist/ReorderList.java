package com.leetcode.linkedlist;

//https://leetcode.com/problems/reorder-list/
public class ReorderList {
    public void reorderList(ListNode head) {
        ListNode start = head;
        ListNode slow = start;
        ListNode fast = start;

        while (slow != null) {
            if (fast.next == null) {
                break;
            }
            if (fast.next.next == null) {
                break;
            }
            slow = slow.next;
            fast = fast.next.next;

        }
        if (slow == null) {
            return;
        }
        if (fast.next != null) {
            fast = fast.next;
        }
        ListNode mid = slow;
        ListNode curr = mid;
        ListNode next = curr.next;
        curr.next = null;

        while (next != null) {
            ListNode nextNext = next.next;
            next.next = curr;
            curr = next;
            next = nextNext;
        }
        ListNode last = fast;
        curr = start;

        while(curr != null && (curr != last || curr.next != last)) {
            ListNode nextLast = last.next;
            ListNode currNext = curr.next;
            curr.next = last;
            last.next = currNext;
            last = nextLast;
            if (curr.next != null) {
                curr = curr.next.next;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        ListNode head =new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        new ReorderList().reorderList(head);
    }
}

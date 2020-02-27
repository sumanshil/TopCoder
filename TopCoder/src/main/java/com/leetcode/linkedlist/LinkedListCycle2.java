package com.leetcode.linkedlist;

//https://leetcode.com/problems/linked-list-cycle-ii/
public class LinkedListCycle2 {

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (slow != null && fast != null) {
            slow = slow.next;
            fast = fast.next;

            if (fast == null) {
                return null;
            }
            fast = fast.next;

            if (slow == fast) {
                ListNode slow2 = head;
                while (slow2 != slow) {
                    slow2 = slow2.next;
                    slow = slow.next;
                }
                return slow;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(3);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(0);
        node1.next.next.next = new ListNode(-4);
        node1.next.next.next.next = node1.next;
        ListNode res = new LinkedListCycle2().detectCycle(node1);
        System.out.println(res);

    }
}

